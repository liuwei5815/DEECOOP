package com.yvan.platform;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

public class HttpJsonPayloadParser {

    private final String payLoad;

    public String getPayLoad() {
        return this.payLoad;
    }

    public JsonWapper getJsonWapper() {
        return new JsonWapper(this.payLoad);
    }

    public HttpJsonPayloadParser(HttpServletRequest request) throws IOException {
        // request.setCharacterEncoding("UTF-8");
        int size = request.getContentLength();
        InputStream is = request.getInputStream();
        byte[] reqBodyBytes = readBytes(is, size);
        this.payLoad = new String(reqBodyBytes, "utf-8");
    }

    public static final byte[] readBytes(InputStream is, int contentLen) {
        if (contentLen > 0) {
            int readLen = 0;
            int readLengthThisTime = 0;
            byte[] message = new byte[contentLen];
            try {
                while (readLen != contentLen) {
                    readLengthThisTime = is.read(message, readLen, contentLen - readLen);
                    if (readLengthThisTime == -1) {// Should not happen.
                        break;
                    }
                    readLen += readLengthThisTime;
                }
                return message;
            } catch (IOException e) {
            }
        }
        return new byte[]{};
    }
}
