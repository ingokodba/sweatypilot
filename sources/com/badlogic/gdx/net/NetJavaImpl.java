package com.badlogic.gdx.net;

import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetJavaImpl {
    final ObjectMap<HttpRequest, HttpURLConnection> connections = new ObjectMap();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    final ObjectMap<HttpRequest, HttpResponseListener> listeners = new ObjectMap();

    static class HttpClientResponse implements HttpResponse {
        private final HttpURLConnection connection;
        private HttpStatus status;

        public HttpClientResponse(HttpURLConnection connection) throws IOException {
            this.connection = connection;
            try {
                this.status = new HttpStatus(connection.getResponseCode());
            } catch (IOException e) {
                this.status = new HttpStatus(-1);
            }
        }

        public byte[] getResult() {
            byte[] copyStreamToByteArray;
            InputStream input = getInputStream();
            try {
                copyStreamToByteArray = StreamUtils.copyStreamToByteArray(input, this.connection.getContentLength());
            } catch (IOException e) {
                copyStreamToByteArray = StreamUtils.EMPTY_BYTES;
            } finally {
                StreamUtils.closeQuietly(input);
            }
            return copyStreamToByteArray;
        }

        public String getResultAsString() {
            InputStream input = getInputStream();
            if (input == null) {
                return "";
            }
            String copyStreamToString;
            try {
                copyStreamToString = StreamUtils.copyStreamToString(input, this.connection.getContentLength());
                return copyStreamToString;
            } catch (IOException e) {
                copyStreamToString = "";
                return copyStreamToString;
            } finally {
                StreamUtils.closeQuietly(input);
            }
        }

        public InputStream getResultAsStream() {
            return getInputStream();
        }

        public HttpStatus getStatus() {
            return this.status;
        }

        public String getHeader(String name) {
            return this.connection.getHeaderField(name);
        }

        public Map<String, List<String>> getHeaders() {
            return this.connection.getHeaderFields();
        }

        private InputStream getInputStream() {
            try {
                return this.connection.getInputStream();
            } catch (IOException e) {
                return this.connection.getErrorStream();
            }
        }
    }

    public void sendHttpRequest(HttpRequest httpRequest, HttpResponseListener httpResponseListener) {
        if (httpRequest.getUrl() == null) {
            httpResponseListener.failed(new GdxRuntimeException("can't process a HTTP request without URL set"));
            return;
        }
        try {
            URL url;
            String method = httpRequest.getMethod();
            if (method.equalsIgnoreCase(HttpMethods.GET)) {
                String queryString = "";
                String value = httpRequest.getContent();
                if (!(value == null || "".equals(value))) {
                    queryString = "?" + value;
                }
                url = new URL(httpRequest.getUrl() + queryString);
            } else {
                url = new URL(httpRequest.getUrl());
            }
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            final boolean doingOutPut = method.equalsIgnoreCase(HttpMethods.POST) || method.equalsIgnoreCase(HttpMethods.PUT);
            connection.setDoOutput(doingOutPut);
            connection.setDoInput(true);
            connection.setRequestMethod(method);
            HttpURLConnection.setFollowRedirects(httpRequest.getFollowRedirects());
            putIntoConnectionsAndListeners(httpRequest, httpResponseListener, connection);
            for (Entry<String, String> header : httpRequest.getHeaders().entrySet()) {
                connection.addRequestProperty((String) header.getKey(), (String) header.getValue());
            }
            connection.setConnectTimeout(httpRequest.getTimeOut());
            connection.setReadTimeout(httpRequest.getTimeOut());
            final HttpRequest httpRequest2 = httpRequest;
            final HttpResponseListener httpResponseListener2 = httpResponseListener;
            this.executorService.submit(new Runnable() {
                public void run() {
                    OutputStream os;
                    OutputStreamWriter writer;
                    try {
                        if (doingOutPut) {
                            String contentAsString = httpRequest2.getContent();
                            if (contentAsString != null) {
                                writer = new OutputStreamWriter(connection.getOutputStream());
                                writer.write(contentAsString);
                                StreamUtils.closeQuietly(writer);
                            } else {
                                InputStream contentAsStream = httpRequest2.getContentStream();
                                if (contentAsStream != null) {
                                    os = connection.getOutputStream();
                                    StreamUtils.copyStream(contentAsStream, os);
                                    StreamUtils.closeQuietly(os);
                                }
                            }
                        }
                        connection.connect();
                        HttpClientResponse clientResponse = new HttpClientResponse(connection);
                        HttpResponseListener listener = NetJavaImpl.this.getFromListeners(httpRequest2);
                        if (listener != null) {
                            listener.handleHttpResponse(clientResponse);
                        }
                        NetJavaImpl.this.removeFromConnectionsAndListeners(httpRequest2);
                        connection.disconnect();
                    } catch (Exception e) {
                        connection.disconnect();
                        try {
                            httpResponseListener2.failed(e);
                        } finally {
                            NetJavaImpl.this.removeFromConnectionsAndListeners(httpRequest2);
                        }
                    } catch (Throwable th) {
                        StreamUtils.closeQuietly(writer);
                    }
                }
            });
        } catch (Exception e) {
            httpResponseListener.failed(e);
        } finally {
            removeFromConnectionsAndListeners(httpRequest);
        }
    }

    public void cancelHttpRequest(HttpRequest httpRequest) {
        HttpResponseListener httpResponseListener = getFromListeners(httpRequest);
        if (httpResponseListener != null) {
            httpResponseListener.cancelled();
            removeFromConnectionsAndListeners(httpRequest);
        }
    }

    synchronized void removeFromConnectionsAndListeners(HttpRequest httpRequest) {
        this.connections.remove(httpRequest);
        this.listeners.remove(httpRequest);
    }

    synchronized void putIntoConnectionsAndListeners(HttpRequest httpRequest, HttpResponseListener httpResponseListener, HttpURLConnection connection) {
        this.connections.put(httpRequest, connection);
        this.listeners.put(httpRequest, httpResponseListener);
    }

    synchronized HttpResponseListener getFromListeners(HttpRequest httpRequest) {
        return (HttpResponseListener) this.listeners.get(httpRequest);
    }
}
