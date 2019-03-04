package com.badlogic.gdx.graphics.profiling;

import com.badlogic.gdx.graphics.GL20;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GL20Profiler extends GLProfiler implements GL20 {
    public GL20 gl20;

    protected GL20Profiler(GL20 gl20) {
        this.gl20 = gl20;
    }

    public void glActiveTexture(int texture) {
        calls++;
        this.gl20.glActiveTexture(texture);
    }

    public void glBindTexture(int target, int texture) {
        textureBindings++;
        calls++;
        this.gl20.glBindTexture(target, texture);
    }

    public void glBlendFunc(int sfactor, int dfactor) {
        calls++;
        this.gl20.glBlendFunc(sfactor, dfactor);
    }

    public void glClear(int mask) {
        calls++;
        this.gl20.glClear(mask);
    }

    public void glClearColor(float red, float green, float blue, float alpha) {
        calls++;
        this.gl20.glClearColor(red, green, blue, alpha);
    }

    public void glClearDepthf(float depth) {
        calls++;
        this.gl20.glClearDepthf(depth);
    }

    public void glClearStencil(int s) {
        calls++;
        this.gl20.glClearStencil(s);
    }

    public void glColorMask(boolean red, boolean green, boolean blue, boolean alpha) {
        calls++;
        this.gl20.glColorMask(red, green, blue, alpha);
    }

    public void glCompressedTexImage2D(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer data) {
        calls++;
        this.gl20.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data);
    }

    public void glCompressedTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int imageSize, Buffer data) {
        calls++;
        this.gl20.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data);
    }

    public void glCopyTexImage2D(int target, int level, int internalformat, int x, int y, int width, int height, int border) {
        calls++;
        this.gl20.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border);
    }

    public void glCopyTexSubImage2D(int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
        calls++;
        this.gl20.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
    }

    public void glCullFace(int mode) {
        calls++;
        this.gl20.glCullFace(mode);
    }

    public void glDeleteTextures(int n, IntBuffer textures) {
        calls++;
        this.gl20.glDeleteTextures(n, textures);
    }

    public void glDepthFunc(int func) {
        calls++;
        this.gl20.glDepthFunc(func);
    }

    public void glDepthMask(boolean flag) {
        calls++;
        this.gl20.glDepthMask(flag);
    }

    public void glDepthRangef(float zNear, float zFar) {
        calls++;
        this.gl20.glDepthRangef(zNear, zFar);
    }

    public void glDisable(int cap) {
        calls++;
        this.gl20.glDisable(cap);
    }

    public void glDrawArrays(int mode, int first, int count) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl20.glDrawArrays(mode, first, count);
    }

    public void glDrawElements(int mode, int count, int type, Buffer indices) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl20.glDrawElements(mode, count, type, indices);
    }

    public void glEnable(int cap) {
        calls++;
        this.gl20.glEnable(cap);
    }

    public void glFinish() {
        calls++;
        this.gl20.glFinish();
    }

    public void glFlush() {
        calls++;
        this.gl20.glFlush();
    }

    public void glFrontFace(int mode) {
        calls++;
        this.gl20.glFrontFace(mode);
    }

    public void glGenTextures(int n, IntBuffer textures) {
        calls++;
        this.gl20.glGenTextures(n, textures);
    }

    public int glGetError() {
        calls++;
        return this.gl20.glGetError();
    }

    public void glGetIntegerv(int pname, IntBuffer params) {
        calls++;
        this.gl20.glGetIntegerv(pname, params);
    }

    public String glGetString(int name) {
        calls++;
        return this.gl20.glGetString(name);
    }

    public void glHint(int target, int mode) {
        calls++;
        this.gl20.glHint(target, mode);
    }

    public void glLineWidth(float width) {
        calls++;
        this.gl20.glLineWidth(width);
    }

    public void glPixelStorei(int pname, int param) {
        calls++;
        this.gl20.glPixelStorei(pname, param);
    }

    public void glPolygonOffset(float factor, float units) {
        calls++;
        this.gl20.glPolygonOffset(factor, units);
    }

    public void glReadPixels(int x, int y, int width, int height, int format, int type, Buffer pixels) {
        calls++;
        this.gl20.glReadPixels(x, y, width, height, format, type, pixels);
    }

    public void glScissor(int x, int y, int width, int height) {
        calls++;
        this.gl20.glScissor(x, y, width, height);
    }

    public void glStencilFunc(int func, int ref, int mask) {
        calls++;
        this.gl20.glStencilFunc(func, ref, mask);
    }

    public void glStencilMask(int mask) {
        calls++;
        this.gl20.glStencilMask(mask);
    }

    public void glStencilOp(int fail, int zfail, int zpass) {
        calls++;
        this.gl20.glStencilOp(fail, zfail, zpass);
    }

    public void glTexImage2D(int target, int level, int internalformat, int width, int height, int border, int format, int type, Buffer pixels) {
        calls++;
        this.gl20.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels);
    }

    public void glTexParameterf(int target, int pname, float param) {
        calls++;
        this.gl20.glTexParameterf(target, pname, param);
    }

    public void glTexSubImage2D(int target, int level, int xoffset, int yoffset, int width, int height, int format, int type, Buffer pixels) {
        calls++;
        this.gl20.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels);
    }

    public void glViewport(int x, int y, int width, int height) {
        calls++;
        this.gl20.glViewport(x, y, width, height);
    }

    public void glAttachShader(int program, int shader) {
        calls++;
        this.gl20.glAttachShader(program, shader);
    }

    public void glBindAttribLocation(int program, int index, String name) {
        calls++;
        this.gl20.glBindAttribLocation(program, index, name);
    }

    public void glBindBuffer(int target, int buffer) {
        calls++;
        this.gl20.glBindBuffer(target, buffer);
    }

    public void glBindFramebuffer(int target, int framebuffer) {
        calls++;
        this.gl20.glBindFramebuffer(target, framebuffer);
    }

    public void glBindRenderbuffer(int target, int renderbuffer) {
        calls++;
        this.gl20.glBindRenderbuffer(target, renderbuffer);
    }

    public void glBlendColor(float red, float green, float blue, float alpha) {
        calls++;
        this.gl20.glBlendColor(red, green, blue, alpha);
    }

    public void glBlendEquation(int mode) {
        calls++;
        this.gl20.glBlendEquation(mode);
    }

    public void glBlendEquationSeparate(int modeRGB, int modeAlpha) {
        calls++;
        this.gl20.glBlendEquationSeparate(modeRGB, modeAlpha);
    }

    public void glBlendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
        calls++;
        this.gl20.glBlendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
    }

    public void glBufferData(int target, int size, Buffer data, int usage) {
        calls++;
        this.gl20.glBufferData(target, size, data, usage);
    }

    public void glBufferSubData(int target, int offset, int size, Buffer data) {
        calls++;
        this.gl20.glBufferSubData(target, offset, size, data);
    }

    public int glCheckFramebufferStatus(int target) {
        calls++;
        return this.gl20.glCheckFramebufferStatus(target);
    }

    public void glCompileShader(int shader) {
        calls++;
        this.gl20.glCompileShader(shader);
    }

    public int glCreateProgram() {
        calls++;
        return this.gl20.glCreateProgram();
    }

    public int glCreateShader(int type) {
        calls++;
        return this.gl20.glCreateShader(type);
    }

    public void glDeleteBuffers(int n, IntBuffer buffers) {
        calls++;
        this.gl20.glDeleteBuffers(n, buffers);
    }

    public void glDeleteFramebuffers(int n, IntBuffer framebuffers) {
        calls++;
        this.gl20.glDeleteFramebuffers(n, framebuffers);
    }

    public void glDeleteProgram(int program) {
        calls++;
        this.gl20.glDeleteProgram(program);
    }

    public void glDeleteRenderbuffers(int n, IntBuffer renderbuffers) {
        calls++;
        this.gl20.glDeleteRenderbuffers(n, renderbuffers);
    }

    public void glDeleteShader(int shader) {
        calls++;
        this.gl20.glDeleteShader(shader);
    }

    public void glDetachShader(int program, int shader) {
        calls++;
        this.gl20.glDetachShader(program, shader);
    }

    public void glDisableVertexAttribArray(int index) {
        calls++;
        this.gl20.glDisableVertexAttribArray(index);
    }

    public void glDrawElements(int mode, int count, int type, int indices) {
        vertexCount.put((float) count);
        drawCalls++;
        calls++;
        this.gl20.glDrawElements(mode, count, type, indices);
    }

    public void glEnableVertexAttribArray(int index) {
        calls++;
        this.gl20.glEnableVertexAttribArray(index);
    }

    public void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
        calls++;
        this.gl20.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer);
    }

    public void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
        calls++;
        this.gl20.glFramebufferTexture2D(target, attachment, textarget, texture, level);
    }

    public void glGenBuffers(int n, IntBuffer buffers) {
        calls++;
        this.gl20.glGenBuffers(n, buffers);
    }

    public void glGenerateMipmap(int target) {
        calls++;
        this.gl20.glGenerateMipmap(target);
    }

    public void glGenFramebuffers(int n, IntBuffer framebuffers) {
        calls++;
        this.gl20.glGenFramebuffers(n, framebuffers);
    }

    public void glGenRenderbuffers(int n, IntBuffer renderbuffers) {
        calls++;
        this.gl20.glGenRenderbuffers(n, renderbuffers);
    }

    public String glGetActiveAttrib(int program, int index, IntBuffer size, Buffer type) {
        calls++;
        return this.gl20.glGetActiveAttrib(program, index, size, type);
    }

    public String glGetActiveUniform(int program, int index, IntBuffer size, Buffer type) {
        calls++;
        return this.gl20.glGetActiveUniform(program, index, size, type);
    }

    public void glGetAttachedShaders(int program, int maxcount, Buffer count, IntBuffer shaders) {
        calls++;
        this.gl20.glGetAttachedShaders(program, maxcount, count, shaders);
    }

    public int glGetAttribLocation(int program, String name) {
        calls++;
        return this.gl20.glGetAttribLocation(program, name);
    }

    public void glGetBooleanv(int pname, Buffer params) {
        calls++;
        this.gl20.glGetBooleanv(pname, params);
    }

    public void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl20.glGetBufferParameteriv(target, pname, params);
    }

    public void glGetFloatv(int pname, FloatBuffer params) {
        calls++;
        this.gl20.glGetFloatv(pname, params);
    }

    public void glGetFramebufferAttachmentParameteriv(int target, int attachment, int pname, IntBuffer params) {
        calls++;
        this.gl20.glGetFramebufferAttachmentParameteriv(target, attachment, pname, params);
    }

    public void glGetProgramiv(int program, int pname, IntBuffer params) {
        calls++;
        this.gl20.glGetProgramiv(program, pname, params);
    }

    public String glGetProgramInfoLog(int program) {
        calls++;
        return this.gl20.glGetProgramInfoLog(program);
    }

    public void glGetRenderbufferParameteriv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl20.glGetRenderbufferParameteriv(target, pname, params);
    }

    public void glGetShaderiv(int shader, int pname, IntBuffer params) {
        calls++;
        this.gl20.glGetShaderiv(shader, pname, params);
    }

    public String glGetShaderInfoLog(int shader) {
        calls++;
        return this.gl20.glGetShaderInfoLog(shader);
    }

    public void glGetShaderPrecisionFormat(int shadertype, int precisiontype, IntBuffer range, IntBuffer precision) {
        calls++;
        this.gl20.glGetShaderPrecisionFormat(shadertype, precisiontype, range, precision);
    }

    public void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
        calls++;
        this.gl20.glGetTexParameterfv(target, pname, params);
    }

    public void glGetTexParameteriv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl20.glGetTexParameteriv(target, pname, params);
    }

    public void glGetUniformfv(int program, int location, FloatBuffer params) {
        calls++;
        this.gl20.glGetUniformfv(program, location, params);
    }

    public void glGetUniformiv(int program, int location, IntBuffer params) {
        calls++;
        this.gl20.glGetUniformiv(program, location, params);
    }

    public int glGetUniformLocation(int program, String name) {
        calls++;
        return this.gl20.glGetUniformLocation(program, name);
    }

    public void glGetVertexAttribfv(int index, int pname, FloatBuffer params) {
        calls++;
        this.gl20.glGetVertexAttribfv(index, pname, params);
    }

    public void glGetVertexAttribiv(int index, int pname, IntBuffer params) {
        calls++;
        this.gl20.glGetVertexAttribiv(index, pname, params);
    }

    public void glGetVertexAttribPointerv(int index, int pname, Buffer pointer) {
        calls++;
        this.gl20.glGetVertexAttribPointerv(index, pname, pointer);
    }

    public boolean glIsBuffer(int buffer) {
        calls++;
        return this.gl20.glIsBuffer(buffer);
    }

    public boolean glIsEnabled(int cap) {
        calls++;
        return this.gl20.glIsEnabled(cap);
    }

    public boolean glIsFramebuffer(int framebuffer) {
        calls++;
        return this.gl20.glIsFramebuffer(framebuffer);
    }

    public boolean glIsProgram(int program) {
        calls++;
        return this.gl20.glIsProgram(program);
    }

    public boolean glIsRenderbuffer(int renderbuffer) {
        calls++;
        return this.gl20.glIsRenderbuffer(renderbuffer);
    }

    public boolean glIsShader(int shader) {
        calls++;
        return this.gl20.glIsShader(shader);
    }

    public boolean glIsTexture(int texture) {
        calls++;
        return this.gl20.glIsTexture(texture);
    }

    public void glLinkProgram(int program) {
        calls++;
        this.gl20.glLinkProgram(program);
    }

    public void glReleaseShaderCompiler() {
        calls++;
        this.gl20.glReleaseShaderCompiler();
    }

    public void glRenderbufferStorage(int target, int internalformat, int width, int height) {
        calls++;
        this.gl20.glRenderbufferStorage(target, internalformat, width, height);
    }

    public void glSampleCoverage(float value, boolean invert) {
        calls++;
        this.gl20.glSampleCoverage(value, invert);
    }

    public void glShaderBinary(int n, IntBuffer shaders, int binaryformat, Buffer binary, int length) {
        calls++;
        this.gl20.glShaderBinary(n, shaders, binaryformat, binary, length);
    }

    public void glShaderSource(int shader, String string) {
        calls++;
        this.gl20.glShaderSource(shader, string);
    }

    public void glStencilFuncSeparate(int face, int func, int ref, int mask) {
        calls++;
        this.gl20.glStencilFuncSeparate(face, func, ref, mask);
    }

    public void glStencilMaskSeparate(int face, int mask) {
        calls++;
        this.gl20.glStencilMaskSeparate(face, mask);
    }

    public void glStencilOpSeparate(int face, int fail, int zfail, int zpass) {
        calls++;
        this.gl20.glStencilOpSeparate(face, fail, zfail, zpass);
    }

    public void glTexParameterfv(int target, int pname, FloatBuffer params) {
        calls++;
        this.gl20.glTexParameterfv(target, pname, params);
    }

    public void glTexParameteri(int target, int pname, int param) {
        calls++;
        this.gl20.glTexParameteri(target, pname, param);
    }

    public void glTexParameteriv(int target, int pname, IntBuffer params) {
        calls++;
        this.gl20.glTexParameteriv(target, pname, params);
    }

    public void glUniform1f(int location, float x) {
        calls++;
        this.gl20.glUniform1f(location, x);
    }

    public void glUniform1fv(int location, int count, FloatBuffer v) {
        calls++;
        this.gl20.glUniform1fv(location, count, v);
    }

    public void glUniform1i(int location, int x) {
        calls++;
        this.gl20.glUniform1i(location, x);
    }

    public void glUniform1iv(int location, int count, IntBuffer v) {
        calls++;
        this.gl20.glUniform1iv(location, count, v);
    }

    public void glUniform2f(int location, float x, float y) {
        calls++;
        this.gl20.glUniform2f(location, x, y);
    }

    public void glUniform2fv(int location, int count, FloatBuffer v) {
        calls++;
        this.gl20.glUniform2fv(location, count, v);
    }

    public void glUniform2i(int location, int x, int y) {
        calls++;
        this.gl20.glUniform2i(location, x, y);
    }

    public void glUniform2iv(int location, int count, IntBuffer v) {
        calls++;
        this.gl20.glUniform2iv(location, count, v);
    }

    public void glUniform3f(int location, float x, float y, float z) {
        calls++;
        this.gl20.glUniform3f(location, x, y, z);
    }

    public void glUniform3fv(int location, int count, FloatBuffer v) {
        calls++;
        this.gl20.glUniform3fv(location, count, v);
    }

    public void glUniform3i(int location, int x, int y, int z) {
        calls++;
        this.gl20.glUniform3i(location, x, y, z);
    }

    public void glUniform3iv(int location, int count, IntBuffer v) {
        calls++;
        this.gl20.glUniform3iv(location, count, v);
    }

    public void glUniform4f(int location, float x, float y, float z, float w) {
        calls++;
        this.gl20.glUniform4f(location, x, y, z, w);
    }

    public void glUniform4fv(int location, int count, FloatBuffer v) {
        calls++;
        this.gl20.glUniform4fv(location, count, v);
    }

    public void glUniform4i(int location, int x, int y, int z, int w) {
        calls++;
        this.gl20.glUniform4i(location, x, y, z, w);
    }

    public void glUniform4iv(int location, int count, IntBuffer v) {
        calls++;
        this.gl20.glUniform4iv(location, count, v);
    }

    public void glUniformMatrix2fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl20.glUniformMatrix2fv(location, count, transpose, value);
    }

    public void glUniformMatrix3fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl20.glUniformMatrix3fv(location, count, transpose, value);
    }

    public void glUniformMatrix4fv(int location, int count, boolean transpose, FloatBuffer value) {
        calls++;
        this.gl20.glUniformMatrix4fv(location, count, transpose, value);
    }

    public void glUseProgram(int program) {
        shaderSwitches++;
        calls++;
        this.gl20.glUseProgram(program);
    }

    public void glValidateProgram(int program) {
        calls++;
        this.gl20.glValidateProgram(program);
    }

    public void glVertexAttrib1f(int indx, float x) {
        calls++;
        this.gl20.glVertexAttrib1f(indx, x);
    }

    public void glVertexAttrib1fv(int indx, FloatBuffer values) {
        calls++;
        this.gl20.glVertexAttrib1fv(indx, values);
    }

    public void glVertexAttrib2f(int indx, float x, float y) {
        calls++;
        this.gl20.glVertexAttrib2f(indx, x, y);
    }

    public void glVertexAttrib2fv(int indx, FloatBuffer values) {
        calls++;
        this.gl20.glVertexAttrib2fv(indx, values);
    }

    public void glVertexAttrib3f(int indx, float x, float y, float z) {
        calls++;
        this.gl20.glVertexAttrib3f(indx, x, y, z);
    }

    public void glVertexAttrib3fv(int indx, FloatBuffer values) {
        calls++;
        this.gl20.glVertexAttrib3fv(indx, values);
    }

    public void glVertexAttrib4f(int indx, float x, float y, float z, float w) {
        calls++;
        this.gl20.glVertexAttrib4f(indx, x, y, z, w);
    }

    public void glVertexAttrib4fv(int indx, FloatBuffer values) {
        calls++;
        this.gl20.glVertexAttrib4fv(indx, values);
    }

    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, Buffer ptr) {
        calls++;
        this.gl20.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
    }

    public void glVertexAttribPointer(int indx, int size, int type, boolean normalized, int stride, int ptr) {
        calls++;
        this.gl20.glVertexAttribPointer(indx, size, type, normalized, stride, ptr);
    }

    public void glDeleteTexture(int texture) {
        calls++;
        this.gl20.glDeleteTexture(texture);
    }

    public int glGenTexture() {
        calls++;
        return this.gl20.glGenTexture();
    }

    public void glDeleteBuffer(int buffer) {
        calls++;
        this.gl20.glDeleteBuffer(buffer);
    }

    public void glDeleteFramebuffer(int framebuffer) {
        calls++;
        this.gl20.glDeleteFramebuffer(framebuffer);
    }

    public void glDeleteRenderbuffer(int renderbuffer) {
        calls++;
        this.gl20.glDeleteRenderbuffer(renderbuffer);
    }

    public int glGenBuffer() {
        calls++;
        return this.gl20.glGenBuffer();
    }

    public int glGenFramebuffer() {
        calls++;
        return this.gl20.glGenFramebuffer();
    }

    public int glGenRenderbuffer() {
        calls++;
        return this.gl20.glGenRenderbuffer();
    }

    public void glUniform1fv(int location, int count, float[] v, int offset) {
        calls++;
        this.gl20.glUniform1fv(location, count, v, offset);
    }

    public void glUniform1iv(int location, int count, int[] v, int offset) {
        calls++;
        this.gl20.glUniform1iv(location, count, v, offset);
    }

    public void glUniform2fv(int location, int count, float[] v, int offset) {
        calls++;
        this.gl20.glUniform2fv(location, count, v, offset);
    }

    public void glUniform2iv(int location, int count, int[] v, int offset) {
        calls++;
        this.gl20.glUniform2iv(location, count, v, offset);
    }

    public void glUniform3fv(int location, int count, float[] v, int offset) {
        calls++;
        this.gl20.glUniform3fv(location, count, v, offset);
    }

    public void glUniform3iv(int location, int count, int[] v, int offset) {
        calls++;
        this.gl20.glUniform3iv(location, count, v, offset);
    }

    public void glUniform4fv(int location, int count, float[] v, int offset) {
        calls++;
        this.gl20.glUniform4fv(location, count, v, offset);
    }

    public void glUniform4iv(int location, int count, int[] v, int offset) {
        calls++;
        this.gl20.glUniform4iv(location, count, v, offset);
    }

    public void glUniformMatrix2fv(int location, int count, boolean transpose, float[] value, int offset) {
        calls++;
        this.gl20.glUniformMatrix2fv(location, count, transpose, value, offset);
    }

    public void glUniformMatrix3fv(int location, int count, boolean transpose, float[] value, int offset) {
        calls++;
        this.gl20.glUniformMatrix3fv(location, count, transpose, value, offset);
    }

    public void glUniformMatrix4fv(int location, int count, boolean transpose, float[] value, int offset) {
        calls++;
        this.gl20.glUniformMatrix4fv(location, count, transpose, value, offset);
    }
}
