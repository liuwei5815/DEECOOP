package com.yvan.springmvc;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

public class StringView extends AbstractView {

    private static final Log LOG = LogFactory.getLog(StringView.class);
    protected String         jsonString;
    private String           contentType;

    public StringView(String jsonString){
        this(jsonString, "text/html;charset=utf-8");
    }

    public StringView(String jsonString, String contentType){
        this.jsonString = jsonString;
        this.contentType = contentType;
    }

    @Override
    protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        boolean useGzip = ResponseUtils.isSupportGzip(request);
        try {
            ResponseUtils.writeJSONToResponse(response, ResponseUtils.DEFAULT_ENCODING, jsonString, useGzip, false,
                                              this.contentType);
        } catch (IOException e) {
            try {
                LOG.error(e);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (IOException e1) {
                throw new RuntimeException("system error.", e1);
            }
        }
    }

}
