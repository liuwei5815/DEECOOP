package com.yvan.springmvc;

import java.io.*;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Response 输出工具 Created by luoyifan on 2016/6/4.
 */
public class ResponseUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ResponseUtils.class);

    public static final String DEFAULT_ENCODING = "utf-8";

    private static final int SUPPORT_MIN_SIZE = 2048;

    public static final boolean isSupportGzip(HttpServletRequest request) {
        String value = request.getHeader("Accept-Encoding");
        value = (value == null || value.trim().equals("")) ? null : value;
        return value == null ? false : value.indexOf("gzip") >= 0;
    }

    public static void writeJSONToResponse(HttpServletResponse response, String encoding, String serializedJSON,
                                           boolean gzip, boolean noCache, String contentType) throws IOException {
        // response.setContentType("application/json;charset=utf-8");
        // response.setContentType("text/html;charset=" + encoding);
        response.setContentType(contentType);
        if (noCache) {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "No-cache");
        }

        if (gzip && serializedJSON.length() > SUPPORT_MIN_SIZE) {
            response.addHeader("Content-Encoding", "gzip");
            GZIPOutputStream out = null;
            InputStream in = null;
            try {
                out = new GZIPOutputStream(response.getOutputStream());
                in = new ByteArrayInputStream(serializedJSON.getBytes(encoding));
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                if (in != null) in.close();
                if (out != null) {
                    out.finish();
                    out.close();
                }
            }
        } else {
            response.setContentLength(serializedJSON.getBytes(encoding).length);
            PrintWriter out = response.getWriter();
            if (out != null) {
                out.print(serializedJSON);
                out.flush();
                out.close();
            }
        }
    }

    /**
     * @param request
     * @param response
     * @param content
     * @param head     text/xml text/html application/json
     */
    public static void writeToResponse(HttpServletRequest request, HttpServletResponse response, String content, String head) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "No-cache");
        if (StringUtils.isEmpty(head)) {
            head = "text/html";
        }
        response.setContentType(head + ";charset=UTF-8");
        response.getWriter().write(content);
        /*
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "No-cache");
        if (StringUtils.isEmpty(head)) {
            response.setContentType(head + ";charset=UTF-8");
        }

        boolean useGzip = ResponseUtils.isSupportGzip(request);
        if (useGzip) {
            response.addHeader("Content-Encoding", "gzip");
            GZIPOutputStream out = null;
            InputStream in = null;
            try {
                out = new GZIPOutputStream(response.getOutputStream());
                in = new ByteArrayInputStream(content.getBytes("utf-8"));
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.finish();
                out.flush();
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
            }
        } else {
            OutputStream out = null;
            InputStream in = null;
            try {
                out = new GZIPOutputStream(response.getOutputStream());
                in = new ByteArrayInputStream(content.getBytes("utf-8"));
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.flush();
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
            }

        }
        */
    }

    public static void writeXml(HttpServletResponse response, String xml) {
        response.setContentType("text/xml;charset=utf-8");
        try {
            response.getWriter().write(xml);
            response.flushBuffer();
        } catch (IOException e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException e1) {
                throw new RuntimeException("system error.", e1);
            }
        }
    }

}
