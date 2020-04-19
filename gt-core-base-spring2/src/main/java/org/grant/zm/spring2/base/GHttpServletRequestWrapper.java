package org.grant.zm.spring2.base;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * grant
 * 14/4/2020 7:38 PM
 * 描述：
 */
public class GHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] body; // 报文


    public GHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = InputStreamToByte(request.getInputStream());
    }

    private byte[] InputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int ch;
        while ((ch = is.read(buffer)) != -1)
            bytestream.write(buffer, 0, ch);
        byte[] data = bytestream.toByteArray();
        bytestream.close();
        return data;
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() { return false; }

            @Override
            public boolean isReady() { return false; }

            @Override
            public void setReadListener(ReadListener readListener) { }

            @Override
            public int read() { return bais.read(); }
        };
    }
}
