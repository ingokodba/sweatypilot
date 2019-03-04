package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL30;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public class GL30Profiler extends GLProfiler implements GL30 {
    public GL30 gl30;

    protected GL30Profiler(GL30 gl30) {
        this.gl30 = gl30;
    }

    public void glActiveTexture(int texture) {
        calls++;
        this.gl30.glActiveTexture(texture);
    }

    public void glBindTexture(int target, int texture) {
        textureBindings++;
        calls++;
        this.gl30.glBindTexture(target, texture);
    }

    public void glBlendFunc(int sfactor, int dfactor) {
        calls++;
        this.gl30.glBlendFunc(sfactor, dfactor);
    }

    public void glClear(int mask) {
        calls++;
        this.gl30.glClear(mask);
    }

    public void glClearColor(float red, float green, float blue, float alpha) {
        calls++;
        this.gl30.glClearColor(red, green, blue, alpha);
    }

    public void glClearDepthf(float depth) {
        calls++;
        this.gl30.glClearDepthf(depth);
    }

    public void glClearStencil(int s) {
        calls++;
        this.gl30.glClearStencil(s);
    }

    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        calls++;
        this.gl30.glColorMask(red, green, blue, alpha);
    }

    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer data) {
        calls++;
        this.gl30.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
    }

    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, Buffer data) {
        calls++;
        this.gl30.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
    }

    public void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border) {
        calls++;
        this.gl30.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
    }

    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        calls++;
        this.gl30.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
    }

    public void glCullFace(int mode) {
        calls++;
        this.gl30.glCullFace(mode);
    }

    public void glDeleteTextures(int n, IntBuffer textures) {
        calls++;
        this.gl30.glDeleteTextures(n, textures);
    }

    public void glDepthFunc(int func) {
        calls++;
        this.gl30.glDepthFunc(func);
    }

    public void glDepthMask(boolean flag) {
        calls++;
        this.gl30.glDepthMask(flag);
    }

    public void glDepthRangef(float zNear, float zFar) {
        calls++;
        this.gl30.glDepthRangef(zNear, zFar);
    }

    public void glDisable(int cap) {
        calls++;
        this.gl30.glDisable(cap);
    }

    public void glDrawArrays(int mode, int first, int count) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl30.glDrawArrays(mode, first, count);
    }

    public void glDrawElements(int mode, int count, int type, Buffer indices) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl30.glDrawElements(mode, count, type, indices);
    }

    public void glEnable(int cap) {
        calls++;
        this.gl30.glEnable(cap);
    }

    public void glFinish() {
        calls++;
        this.gl30.glFinish();
    }

    public void glFlush() {
        calls++;
        this.gl30.glFlush();
    }

    public void glFrontFace(int mode) {
        calls++;
        this.gl30.glFrontFace(mode);
    }

    public void glGenTextures(int n, IntBuffer textures) {
        calls++;
        this.gl30.glGenTextures(n, textures);
    }

    public int glGetError() {
        calls++;
        return this.gl30.glGetError();
    }

    public void glGetIntegerv(int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetIntegerv(pname, params);
    }

    public String glGetString(int name) {
        calls++;
        return this.gl30.glGetString(name);
    }

    public void glHint(int target, int mode) {
        calls++;
        this.gl30.glHint(target, mode);
    }

    public void glLineWidth(float width) {
        calls++;
        this.gl30.glLineWidth(width);
    }

    public void glPixelStorei(int pname, int param) {
        calls++;
        this.gl30.glPixelStorei(pname, param);
    }

    public void glPolygonOffset(float factor, float units) {
        calls++;
        this.gl30.glPolygonOffset(factor, units);
    }

    public void glReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels) {
        calls++;
        this.gl30.glReadPixels(x, y, width, height, format, type, pixels);
    }

    public void glScissor(int x, int y, int width, int height) {
        calls++;
        this.gl30.glScissor(x, y, width, height);
    }

    public void glStencilFunc(int func, int ref, int mask) {
        calls++;
        this.gl30.glStencilFunc(func, ref, mask);
    }

    public void glStencilMask(int mask) {
        calls++;
        this.gl30.glStencilMask(mask);
    }

    public void glStencilOp(int fail, int zfail, int zpass) {
        calls++;
        this.gl30.glStencilOp(fail, zfail, zpass);
    }

    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels) {
        calls++;
        this.gl30.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    public void glTexParameterf(int target, int pname, float param) {
        calls++;
        this.gl30.glTexParameterf(target, pname, param);
    }

    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels) {
        calls++;
        this.gl30.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    public void glViewport(int x, int y, int width, int height) {
        calls++;
        this.gl30.glViewport(x, y, width, height);
    }

    public void glAttachShader(int program, int shader) {
        calls++;
        this.gl30.glAttachShader(program, shader);
    }

    public void glBindAttribLocation(int program, int index, String name) {
        calls++;
        this.gl30.glBindAttribLocation(program, index, name);
    }

    public void glBindBuffer(int target, int buffer) {
        calls++;
        this.gl30.glBindBuffer(target, buffer);
    }

    public void glBindFramebuffer(int target, int framebuffer) {
        calls++;
        this.gl30.glBindFramebuffer(target, framebuffer);
    }

    public void glBindRenderbuffer(int target, int renderbuffer) {
        calls++;
        this.gl30.glBindRenderbuffer(target, renderbuffer);
    }

    public void glBlendColor(float red, float green, float blue, float alpha) {
        calls++;
        this.gl30.glBlendColor(red, green, blue, alpha);
    }

    public void glBlendEquation(int mode) {
        calls++;
        this.gl30.glBlendEquation(mode);
    }

    public void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
        calls++;
        this.gl30.glBlendEquationSeparate(modeRGB, modeAlpha);
    }

    public void glBlendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
        calls++;
        this.gl30.glBlendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
    }

    public void glBufferData(int target, int size, Buffer data, int usage) {
        calls++;
        this.gl30.glBufferData(target, size, data, usage);
    }

    public void glBufferSubData(int target, int offset, int size, Buffer data) {
        calls++;
        this.gl30.glBufferSubData(target, offset, size, data);
    }

    public int glCheckFramebufferStatus(int target) {
        calls++;
        return this.gl30.glCheckFramebufferStatus(target);
    }

    public void glCompileShader(int shader) {
        calls++;
        this.gl30.glCompileShader(shader);
    }

    public int glCreateProgram() {
        calls++;
        return this.gl30.glCreateProgram();
    }

    public int glCreateShader(int type) {
        calls++;
        return this.gl30.glCreateShader(type);
    }

    public void glDeleteBuffers(int n, IntBuffer buffers) {
        calls++;
        this.gl30.glDeleteBuffers(n, buffers);
    }

    public void glDeleteFramebuffers(int n, IntBuffer framebuffers) {
        calls++;
        this.gl30.glDeleteFramebuffers(n, framebuffers);
    }

    public void glDeleteProgram(int program) {
        calls++;
        this.gl30.glDeleteProgram(program);
    }

    public void glDeleteRenderbuffers(int n, IntBuffer renderbuffers) {
        calls++;
        this.gl30.glDeleteRenderbuffers(n, renderbuffers);
    }

    public void glDeleteShader(int shader) {
        calls++;
        this.gl30.glDeleteShader(shader);
    }

    public void glDetachShader(int program, int shader) {
        calls++;
        this.gl30.glDetachShader(program, shader);
    }

    public void glDisableVertexAttribArray(int index) {
        calls++;
        this.gl30.glDisableVertexAttribArray(index);
    }

    public void glDrawElements(int mode, int count, int type, int indices) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl30.glDrawElements(mode, count, type, indices);
    }

    public void glEnableVertexAttribArray(int index) {
        calls++;
        this.gl30.glEnableVertexAttribArray(index);
    }

    public void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
        calls++;
        this.gl30.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
    }

    public void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
        calls++;
        this.gl30.glFramebufferTexture2D(target, attachment, textarget, texture, level);
    }

    public void glGenBuffers(int n, IntBuffer buffers) {
        calls++;
        this.gl30.glGenBuffers(n, buffers);
    }

    public void glGenerateMipmap(int target) {
        calls++;
        this.gl30.glGenerateMipmap(target);
    }

    public void glGenFramebuffers(int n, IntBuffer framebuffers) {
        calls++;
        this.gl30.glGenFramebuffers(n, framebuffers);
    }

    public void glGenRenderbuffers(int n, IntBuffer renderbuffers) {
        calls++;
        this.gl30.glGenRenderbuffers(n, renderbuffers);
    }

    public String glGetActiveAttrib(int program, int index, IntBuffer size, Buffer type) {
        calls++;
        return this.gl30.glGetActiveAttrib(program, index, size, type);
    }

    public String glGetActiveUniform(int program, int index, IntBuffer size, Buffer type) {
        calls++;
        return this.gl30.glGetActiveUniform(program, index, size, type);
    }

    public void glGetAttachedShaders(int program, int maxcount, Buffer count, IntBuffer shaders) {
        calls++;
        this.gl30.glGetAttachedShaders(program, maxcount, count, shaders);
    }

    public int glGetAttribLocation(int program, String name) {
        calls++;
        return this.gl30.glGetAttribLocation(program, name);
    }

    public void glGetBooleanv(int pname, Buffer params) {
        calls++;
        this.gl30.glGetBooleanv(pname, params);
    }

    public void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetBufferParameteriv(target, pname, params);
    }

    public void glGetFloatv(int pname, FloatBuffer params) {
        calls++;
        this.gl30.glGetFloatv(pname, params);
    }

    public void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetFramebufferAttachmentParameteriv(target, attachment, pname, params);
    }

    public void glGetProgramiv(int program, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetProgramiv(program, pname, params);
    }

    public String glGetProgramInfoLog(int program) {
        calls++;
        return this.gl30.glGetProgramInfoLog(program);
    }

    public void glGetRenderbufferParameteriv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetRenderbufferParameteriv(target, pname, params);
    }

    public void glGetShaderiv(int shader, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetShaderiv(shader, pname, params);
    }

    public String glGetShaderInfoLog(int shader) {
        calls++;
        return this.gl30.glGetShaderInfoLog(shader);
    }

    public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) {
        calls++;
        this.gl30.glGetShaderPrecisionFormat(shadertype, precisiontype, range, precision);
    }

    public void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
        calls++;
        this.gl30.glGetTexParameterfv(target, pname, params);
    }

    public void glGetTexParameteriv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetTexParameteriv(target, pname, params);
    }

    public void glGetUniformfv(int program, int location, FloatBuffer params) {
        calls++;
        this.gl30.glGetUniformfv(program, location, params);
    }

    public void glGetUniformiv(int program, int location, IntBuffer params) {
        calls++;
        this.gl30.glGetUniformiv(program, location, params);
    }

    public int glGetUniformLocation(int program, String name) {
        calls++;
        return this.gl30.glGetUniformLocation(program, name);
    }

    public void glGetVertexAttribfv(int index, int pname, FloatBuffer params) {
        calls++;
        this.gl30.glGetVertexAttribfv(index, pname, params);
    }

    public void glGetVertexAttribiv(int index, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetVertexAttribiv(index, pname, params);
    }

    public void glGetVertexAttribPointerv(int index, int pname, Buffer pointer) {
        calls++;
        this.gl30.glGetVertexAttribPointerv(index, pname, pointer);
    }

    public boolean glIsBuffer(int buffer) {
        calls++;
        return this.gl30.glIsBuffer(buffer);
    }

    public boolean glIsEnabled(int cap) {
        calls++;
        return this.gl30.glIsEnabled(cap);
    }

    public boolean glIsFramebuffer(int framebuffer) {
        calls++;
        return this.gl30.glIsFramebuffer(framebuffer);
    }

    public boolean glIsProgram(int program) {
        calls++;
        return this.gl30.glIsProgram(program);
    }

    public boolean glIsRenderbuffer(int renderbuffer) {
        calls++;
        return this.gl30.glIsRenderbuffer(renderbuffer);
    }

    public boolean glIsShader(int shader) {
        calls++;
        return this.gl30.glIsShader(shader);
    }

    public boolean glIsTexture(int texture) {
        calls++;
        return this.gl30.glIsTexture(texture);
    }

    public void glLinkProgram(int program) {
        calls++;
        this.gl30.glLinkProgram(program);
    }

    public void glReleaseShaderCompiler() {
        calls++;
        this.gl30.glReleaseShaderCompiler();
    }

    public void glRenderbufferStorage(int target, int internalformat, int width, int height) {
        calls++;
        this.gl30.glRenderbufferStorage(target, internalformat, width, height);
    }

    public void glSampleCoverage(float value, boolean invert) {
        calls++;
        this.gl30.glSampleCoverage(value, invert);
    }

    public void glShaderBinary(int n, IntBuffer shaders, int binaryformat, Buffer binary, int length) {
        calls++;
        this.gl30.glShaderBinary(n, shaders, binaryformat, binary, length);
    }

    public void glShaderSource(int shader, String string) {
        calls++;
        this.gl30.glShaderSource(shader, string);
    }

    public void glStencilFuncSeparate(int face, int func, int ref, int mask) {
        calls++;
        this.gl30.glStencilFuncSeparate(face, func, ref, mask);
    }

    public void glStencilMaskSeparate(int face, int mask) {
        calls++;
        this.gl30.glStencilMaskSeparate(face, mask);
    }

    public void glStencilOpSeparate(int face, int fail, int zfail, int zpass) {
        calls++;
        this.gl30.glStencilOpSeparate(face, fail, zfail, zpass);
    }

    public void glTexParameterfv(int target, int pname, FloatBuffer params) {
        calls++;
        this.gl30.glTexParameterfv(target, pname, params);
    }

    public void glTexParameteri(int target, int pname, int param) {
        calls++;
        this.gl30.glTexParameteri(target, pname, param);
    }

    public void glTexParameteriv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl30.glTexParameteriv(target, pname, params);
    }

    public void glUniform1f(int location, float x) {
        calls++;
        this.gl30.glUniform1f(location, x);
    }

    public void glUniform1fv(int location, int count, FloatBuffer v) {
        calls++;
        this.gl30.glUniform1fv(location, count, v);
    }

    public void glUniform1i(int location, int x) {
        calls++;
        this.gl30.glUniform1i(location, x);
    }

    public void glUniform1iv(int location, int count, IntBuffer v) {
        calls++;
        this.gl30.glUniform1iv(location, count, v);
    }

    public void glUniform2f(int location, float x, float y) {
        calls++;
        this.gl30.glUniform2f(location, x, y);
    }

    public void glUniform2fv(int location, int count, FloatBuffer v) {
        calls++;
        this.gl30.glUniform2fv(location, count, v);
    }

    public void glUniform2i(int location, int x, int y) {
        calls++;
        this.gl30.glUniform2i(location, x, y);
    }

    public void glUniform2iv(int location, int count, IntBuffer v) {
        calls++;
        this.gl30.glUniform2iv(location, count, v);
    }

    public void glUniform3f(int location, float x, float y, float z) {
        calls++;
        this.gl30.glUniform3f(location, x, y, z);
    }

    public void glUniform3fv(int location, int count, FloatBuffer v) {
        calls++;
        this.gl30.glUniform3fv(location, count, v);
    }

    public void glUniform3i(int location, int x, int y, int z) {
        calls++;
        this.gl30.glUniform3i(location, x, y, z);
    }

    public void glUniform3iv(int location, int count, IntBuffer v) {
        calls++;
        this.gl30.glUniform3iv(location, count, v);
    }

    public void glUniform4f(int location, float x, float y, float z, float w) {
        calls++;
        this.gl30.glUniform4f(location, x, y, z, w);
    }

    public void glUniform4fv(int location, int count, FloatBuffer v) {
        calls++;
        this.gl30.glUniform4fv(location, count, v);
    }

    public void glUniform4i(int location, int x, int y, int z, int w) {
        calls++;
        this.gl30.glUniform4i(location, x, y, z, w);
    }

    public void glUniform4iv(int location, int count, IntBuffer v) {
        calls++;
        this.gl30.glUniform4iv(location, count, v);
    }

    public void glUniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix2fv(location, count, transpose, value);
    }

    public void glUniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix3fv(location, count, transpose, value);
    }

    public void glUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix4fv(location, count, transpose, value);
    }

    public void glUseProgram(int program) {
        shaderSwitches++;
        calls++;
        this.gl30.glUseProgram(program);
    }

    public void glValidateProgram(int program) {
        calls++;
        this.gl30.glValidateProgram(program);
    }

    public void glVertexAttrib1f(int indx, float x) {
        calls++;
        this.gl30.glVertexAttrib1f(indx, x);
    }

    public void glVertexAttrib1fv(int indx, FloatBuffer values) {
        calls++;
        this.gl30.glVertexAttrib1fv(indx, values);
    }

    public void glVertexAttrib2f(int indx, float x, float y) {
        calls++;
        this.gl30.glVertexAttrib2f(indx, x, y);
    }

    public void glVertexAttrib2fv(int indx, FloatBuffer values) {
        calls++;
        this.gl30.glVertexAttrib2fv(indx, values);
    }

    public void glVertexAttrib3f(int indx, float x, float y, float z) {
        calls++;
        this.gl30.glVertexAttrib3f(indx, x, y, z);
    }

    public void glVertexAttrib3fv(int indx, FloatBuffer values) {
        calls++;
        this.gl30.glVertexAttrib3fv(indx, values);
    }

    public void glVertexAttrib4f(int indx, float x, float y, float z, float w) {
        calls++;
        this.gl30.glVertexAttrib4f(indx, x, y, z, w);
    }

    public void glVertexAttrib4fv(int indx, FloatBuffer values) {
        calls++;
        this.gl30.glVertexAttrib4fv(indx, values);
    }

    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer ptr) {
        calls++;
        this.gl30.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
    }

    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, int ptr) {
        calls++;
        this.gl30.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
    }

    public void glReadBuffer(int mode) {
        calls++;
        this.gl30.glReadBuffer(mode);
    }

    public void glDrawRangeElements(int mode, int start, int end, int count, int type, Buffer indices) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl30.glDrawRangeElements(mode, start, end, count, type, indices);
    }

    public void glDrawRangeElements(int mode, int start, int end, int count, int type, int offset) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl30.glDrawRangeElements(mode, start, end, count, type, offset);
    }

    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, Buffer pixels) {
        calls++;
        this.gl30.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, pixels);
    }

    public void glTexImage3D(int target, int level, int internalformat, int width, int height, int depth, int border, int format, int type, int offset) {
        calls++;
        this.gl30.glTexImage3D(target, level, internalformat, width, height, depth, border, format, type, offset);
    }

    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, Buffer pixels) {
        calls++;
        this.gl30.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, pixels);
    }

    public void glTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int format, int type, int offset) {
        calls++;
        this.gl30.glTexSubImage3D(target, level, xoffset, yoffset, zoffset, width, height, depth, format, type, offset);
    }

    public void glCopyTexSubImage3D(int target, int level, int xoffset, int yoffset, int zoffset, int x, int y, int width, int height) {
        calls++;
        this.gl30.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset, x, y, width, height);
    }

    public void glGenQueries(int n, int[] ids, int offset) {
        calls++;
        this.gl30.glGenQueries(n, ids, offset);
    }

    public void glGenQueries(int n, IntBuffer ids) {
        calls++;
        this.gl30.glGenQueries(n, ids);
    }

    public void glDeleteQueries(int n, int[] ids, int offset) {
        calls++;
        this.gl30.glDeleteQueries(n, ids, offset);
    }

    public void glDeleteQueries(int n, IntBuffer ids) {
        calls++;
        this.gl30.glDeleteQueries(n, ids);
    }

    public boolean glIsQuery(int id) {
        calls++;
        return this.gl30.glIsQuery(id);
    }

    public void glBeginQuery(int target, int id) {
        calls++;
        this.gl30.glBeginQuery(target, id);
    }

    public void glEndQuery(int target) {
        calls++;
        this.gl30.glEndQuery(target);
    }

    public void glGetQueryiv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetQueryiv(target, pname, params);
    }

    public void glGetQueryObjectuiv(int id, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetQueryObjectuiv(id, pname, params);
    }

    public boolean glUnmapBuffer(int target) {
        calls++;
        return this.gl30.glUnmapBuffer(target);
    }

    public Buffer glGetBufferPointerv(int target, int pname) {
        calls++;
        return this.gl30.glGetBufferPointerv(target, pname);
    }

    public void glDrawBuffers(int n, IntBuffer bufs) {
        drawCalls++;
        calls++;
        this.gl30.glDrawBuffers(n, bufs);
    }

    public void glUniformMatrix2x3fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix2x3fv(location, count, transpose, value);
    }

    public void glUniformMatrix3x2fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix3x2fv(location, count, transpose, value);
    }

    public void glUniformMatrix2x4fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix2x4fv(location, count, transpose, value);
    }

    public void glUniformMatrix4x2fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix4x2fv(location, count, transpose, value);
    }

    public void glUniformMatrix3x4fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix3x4fv(location, count, transpose, value);
    }

    public void glUniformMatrix4x3fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl30.glUniformMatrix4x3fv(location, count, transpose, value);
    }

    public void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
        calls++;
        this.gl30.glBlitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
    }

    public void glRenderbufferStorageMultisample(int target, int samples, int internalformat, int width, int height) {
        calls++;
        this.gl30.glRenderbufferStorageMultisample(target, samples, internalformat, width, height);
    }

    public void glFramebufferTextureLayer(int target, int attachment, int texture, int level, int layer) {
        calls++;
        this.gl30.glFramebufferTextureLayer(target, attachment, texture, level, layer);
    }

    public void glFlushMappedBufferRange(int target, int offset, int length) {
        calls++;
        this.gl30.glFlushMappedBufferRange(target, offset, length);
    }

    public void glBindVertexArray(int array) {
        calls++;
        this.gl30.glBindVertexArray(array);
    }

    public void glDeleteVertexArrays(int n, int[] arrays, int offset) {
        calls++;
        this.gl30.glDeleteVertexArrays(n, arrays, offset);
    }

    public void glDeleteVertexArrays(int n, IntBuffer arrays) {
        calls++;
        this.gl30.glDeleteVertexArrays(n, arrays);
    }

    public void glGenVertexArrays(int n, int[] arrays, int offset) {
        calls++;
        this.gl30.glGenVertexArrays(n, arrays, offset);
    }

    public void glGenVertexArrays(int n, IntBuffer arrays) {
        calls++;
        this.gl30.glGenVertexArrays(n, arrays);
    }

    public boolean glIsVertexArray(int array) {
        calls++;
        return this.gl30.glIsVertexArray(array);
    }

    public void glBeginTransformFeedback(int primitiveMode) {
        calls++;
        this.gl30.glBeginTransformFeedback(primitiveMode);
    }

    public void glEndTransformFeedback() {
        calls++;
        this.gl30.glEndTransformFeedback();
    }

    public void glBindBufferRange(int target, int index, int buffer, int offset, int size) {
        calls++;
        this.gl30.glBindBufferRange(target, index, buffer, offset, size);
    }

    public void glBindBufferBase(int target, int index, int buffer) {
        calls++;
        this.gl30.glBindBufferBase(target, index, buffer);
    }

    public void glTransformFeedbackVaryings(int program, String[] varyings, int bufferMode) {
        calls++;
        this.gl30.glTransformFeedbackVaryings(program, varyings, bufferMode);
    }

    public void glVertexAttribIPointer(int index, int size, int type, int stride, int offset) {
        calls++;
        this.gl30.glVertexAttribIPointer(index, size, type, stride, offset);
    }

    public void glGetVertexAttribIiv(int index, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetVertexAttribIiv(index, pname, params);
    }

    public void glGetVertexAttribIuiv(int index, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetVertexAttribIuiv(index, pname, params);
    }

    public void glVertexAttribI4i(int index, int x, int y, int z, int w) {
        calls++;
        this.gl30.glVertexAttribI4i(index, x, y, z, w);
    }

    public void glVertexAttribI4ui(int index, int x, int y, int z, int w) {
        calls++;
        this.gl30.glVertexAttribI4ui(index, x, y, z, w);
    }

    public void glGetUniformuiv(int program, int location, IntBuffer params) {
        calls++;
        this.gl30.glGetUniformuiv(program, location, params);
    }

    public int glGetFragDataLocation(int program, String name) {
        calls++;
        return this.gl30.glGetFragDataLocation(program, name);
    }

    public void glUniform1uiv(int location, int count, IntBuffer value) {
        calls++;
        this.gl30.glUniform1uiv(location, count, value);
    }

    public void glUniform3uiv(int location, int count, IntBuffer value) {
        calls++;
        this.gl30.glUniform3uiv(location, count, value);
    }

    public void glUniform4uiv(int location, int count, IntBuffer value) {
        calls++;
        this.gl30.glUniform4uiv(location, count, value);
    }

    public void glClearBufferiv(int buffer, int drawbuffer, IntBuffer value) {
        calls++;
        this.gl30.glClearBufferiv(buffer, drawbuffer, value);
    }

    public void glClearBufferuiv(int buffer, int drawbuffer, IntBuffer value) {
        calls++;
        this.gl30.glClearBufferuiv(buffer, drawbuffer, value);
    }

    public void glClearBufferfv(int buffer, int drawbuffer, FloatBuffer value) {
        calls++;
        this.gl30.glClearBufferfv(buffer, drawbuffer, value);
    }

    public void glClearBufferfi(int buffer, int drawbuffer, float depth, int stencil) {
        calls++;
        this.gl30.glClearBufferfi(buffer, drawbuffer, depth, stencil);
    }

    public String glGetStringi(int name, int index) {
        calls++;
        return this.gl30.glGetStringi(name, index);
    }

    public void glCopyBufferSubData(int readTarget, int writeTarget, int readOffset, int writeOffset, int size) {
        calls++;
        this.gl30.glCopyBufferSubData(readTarget, writeTarget, readOffset, writeOffset, size);
    }

    public void glGetUniformIndices(int program, String[] uniformNames, IntBuffer uniformIndices) {
        calls++;
        this.gl30.glGetUniformIndices(program, uniformNames, uniformIndices);
    }

    public void glGetActiveUniformsiv(int program, int uniformCount, IntBuffer uniformIndices, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetActiveUniformsiv(program, uniformCount, uniformIndices, pname, params);
    }

    public int glGetUniformBlockIndex(int program, String uniformBlockName) {
        calls++;
        return this.gl30.glGetUniformBlockIndex(program, uniformBlockName);
    }

    public void glGetActiveUniformBlockiv(int program, int uniformBlockIndex, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetActiveUniformBlockiv(program, uniformBlockIndex, pname, params);
    }

    public void glGetActiveUniformBlockName(int program, int uniformBlockIndex, Buffer length, Buffer uniformBlockName) {
        calls++;
        this.gl30.glGetActiveUniformBlockName(program, uniformBlockIndex, length, uniformBlockName);
    }

    public String glGetActiveUniformBlockName(int program, int uniformBlockIndex) {
        calls++;
        return this.gl30.glGetActiveUniformBlockName(program, uniformBlockIndex);
    }

    public void glUniformBlockBinding(int program, int uniformBlockIndex, int uniformBlockBinding) {
        calls++;
        this.gl30.glUniformBlockBinding(program, uniformBlockIndex, uniformBlockBinding);
    }

    public void glDrawArraysInstanced(int mode, int first, int count, int instanceCount) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl30.glDrawArraysInstanced(mode, first, count, instanceCount);
    }

    public void glDrawElementsInstanced(int mode, int count, int type, int indicesOffset, int instanceCount) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl30.glDrawElementsInstanced(mode, count, type, indicesOffset, instanceCount);
    }

    public void glGetInteger64v(int pname, LongBuffer params) {
        calls++;
        this.gl30.glGetInteger64v(pname, params);
    }

    public void glGetBufferParameteri64v(int target, int pname, LongBuffer params) {
        calls++;
        this.gl30.glGetBufferParameteri64v(target, pname, params);
    }

    public void glGenSamplers(int count, int[] samplers, int offset) {
        calls++;
        this.gl30.glGenSamplers(count, samplers, offset);
    }

    public void glGenSamplers(int count, IntBuffer samplers) {
        calls++;
        this.gl30.glGenSamplers(count, samplers);
    }

    public void glDeleteSamplers(int count, int[] samplers, int offset) {
        calls++;
        this.gl30.glDeleteSamplers(count, samplers, offset);
    }

    public void glDeleteSamplers(int count, IntBuffer samplers) {
        calls++;
        this.gl30.glDeleteSamplers(count, samplers);
    }

    public boolean glIsSampler(int sampler) {
        calls++;
        return this.gl30.glIsSampler(sampler);
    }

    public void glBindSampler(int unit, int sampler) {
        calls++;
        this.gl30.glBindSampler(unit, sampler);
    }

    public void glSamplerParameteri(int sampler, int pname, int param) {
        calls++;
        this.gl30.glSamplerParameteri(sampler, pname, param);
    }

    public void glSamplerParameteriv(int sampler, int pname, IntBuffer param) {
        calls++;
        this.gl30.glSamplerParameteriv(sampler, pname, param);
    }

    public void glSamplerParameterf(int sampler, int pname, float param) {
        calls++;
        this.gl30.glSamplerParameterf(sampler, pname, param);
    }

    public void glSamplerParameterfv(int sampler, int pname, FloatBuffer param) {
        calls++;
        this.gl30.glSamplerParameterfv(sampler, pname, param);
    }

    public void glGetSamplerParameteriv(int sampler, int pname, IntBuffer params) {
        calls++;
        this.gl30.glGetSamplerParameteriv(sampler, pname, params);
    }

    public void glGetSamplerParameterfv(int sampler, int pname, FloatBuffer params) {
        calls++;
        this.gl30.glGetSamplerParameterfv(sampler, pname, params);
    }

    public void glVertexAttribDivisor(int index, int divisor) {
        calls++;
        this.gl30.glVertexAttribDivisor(index, divisor);
    }

    public void glBindTransformFeedback(int target, int id) {
        calls++;
        this.gl30.glBindTransformFeedback(target, id);
    }

    public void glDeleteTransformFeedbacks(int n, int[] ids, int offset) {
        calls++;
        this.gl30.glDeleteTransformFeedbacks(n, ids, offset);
    }

    public void glDeleteTransformFeedbacks(int n, IntBuffer ids) {
        calls++;
        this.gl30.glDeleteTransformFeedbacks(n, ids);
    }

    public void glGenTransformFeedbacks(int n, int[] ids, int offset) {
        calls++;
        this.gl30.glGenTransformFeedbacks(n, ids, offset);
    }

    public void glGenTransformFeedbacks(int n, IntBuffer ids) {
        calls++;
        this.gl30.glGenTransformFeedbacks(n, ids);
    }

    public boolean glIsTransformFeedback(int id) {
        calls++;
        return this.gl30.glIsTransformFeedback(id);
    }

    public void glPauseTransformFeedback() {
        calls++;
        this.gl30.glPauseTransformFeedback();
    }

    public void glResumeTransformFeedback() {
        calls++;
        this.gl30.glResumeTransformFeedback();
    }

    public void glProgramParameteri(int program, int pname, int value) {
        calls++;
        this.gl30.glProgramParameteri(program, pname, value);
    }

    public void glInvalidateFramebuffer(int target, int numAttachments, IntBuffer attachments) {
        calls++;
        this.gl30.glInvalidateFramebuffer(target, numAttachments, attachments);
    }

    public void glInvalidateSubFramebuffer(int target, int numAttachments, IntBuffer attachments, int x, int y, int width, int height) {
        calls++;
        this.gl30.glInvalidateSubFramebuffer(target, numAttachments, attachments, x, y, width, height);
    }

    public void glDeleteTexture(int texture) {
        calls++;
        this.gl30.glDeleteTexture(texture);
    }

    public int glGenTexture() {
        calls++;
        return this.gl30.glGenTexture();
    }

    public void glDeleteBuffer(int buffer) {
        calls++;
        this.gl30.glDeleteBuffer(buffer);
    }

    public void glDeleteFramebuffer(int framebuffer) {
        calls++;
        this.gl30.glDeleteFramebuffer(framebuffer);
    }

    public void glDeleteRenderbuffer(int renderbuffer) {
        calls++;
        this.gl30.glDeleteRenderbuffer(renderbuffer);
    }

    public int glGenBuffer() {
        calls++;
        return this.gl30.glGenBuffer();
    }

    public int glGenFramebuffer() {
        calls++;
        return this.gl30.glGenFramebuffer();
    }

    public int glGenRenderbuffer() {
        calls++;
        return this.gl30.glGenRenderbuffer();
    }

    public void glUniform1fv(int location, int count, float[] v, int offset) {
        calls++;
        this.gl30.glUniform1fv(location, count, v, offset);
    }

    public void glUniform1iv(int location, int count, int[] v, int offset) {
        calls++;
        this.gl30.glUniform1iv(location, count, v, offset);
    }

    public void glUniform2fv(int location, int count, float[] v, int offset) {
        calls++;
        this.gl30.glUniform2fv(location, count, v, offset);
    }

    public void glUniform2iv(int location, int count, int[] v, int offset) {
        calls++;
        this.gl30.glUniform2iv(location, count, v, offset);
    }

    public void glUniform3fv(int location, int count, float[] v, int offset) {
        calls++;
        this.gl30.glUniform3fv(location, count, v, offset);
    }

    public void glUniform3iv(int location, int count, int[] v, int offset) {
        calls++;
        this.gl30.glUniform3iv(location, count, v, offset);
    }

    public void glUniform4fv(int location, int count, float[] v, int offset) {
        calls++;
        this.gl30.glUniform4fv(location, count, v, offset);
    }

    public void glUniform4iv(int location, int count, int[] v, int offset) {
        calls++;
        this.gl30.glUniform4iv(location, count, v, offset);
    }

    public void glUniformMatrix2fv(int location, int count, boolean transpose, float[] value, int offset) {
        calls++;
        this.gl30.glUniformMatrix2fv(location, count, transpose, value, offset);
    }

    public void glUniformMatrix3fv(int location, int count, boolean transpose, float[] value, int offset) {
        calls++;
        this.gl30.glUniformMatrix3fv(location, count, transpose, value, offset);
    }

    public void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int offset) {
        calls++;
        this.gl30.glUniformMatrix4fv(location, count, transpose, value, offset);
    }
}
