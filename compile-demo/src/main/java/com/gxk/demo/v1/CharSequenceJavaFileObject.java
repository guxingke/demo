package com.gxk.demo.v1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import javax.tools.SimpleJavaFileObject;

public class CharSequenceJavaFileObject extends SimpleJavaFileObject {

    public static final String CLASS_EXTENSION = ".class";

    public static final String JAVA_EXTENSION = ".java";

    private static URI fromClassName(String className) {
        try {
            return new URI(className);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(className, e);
        }
    }

    private ByteArrayOutputStream byteCode;
    private final CharSequence sourceCode;

    public CharSequenceJavaFileObject(String className, CharSequence sourceCode) {
        super(fromClassName(className + JAVA_EXTENSION), Kind.SOURCE);
        this.sourceCode = sourceCode;
    }

    public CharSequenceJavaFileObject(String fullClassName, Kind kind) {
        super(fromClassName(fullClassName), kind);
        this.sourceCode = null;
    }

    public CharSequenceJavaFileObject(URI uri, Kind kind) {
        super(uri, kind);
        this.sourceCode = null;
    }
    
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return sourceCode;
    }

    @Override
    public InputStream openInputStream() {
        return new ByteArrayInputStream(getByteCode());
    }
    
    // 注意这个方法是编译结果回调的OutputStream，回调成功后就能通过下面的getByteCode()方法获取目标类编译后的字节码字节数组
    @Override
    public OutputStream openOutputStream() {
        return byteCode = new ByteArrayOutputStream();
    }

    public byte[] getByteCode() {
        return byteCode.toByteArray();
    }
}