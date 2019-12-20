package com.yvan.springmvc;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

import com.yvan.YvanUtil;
import com.yvan.platform.JsonWapper;

public class JsonView extends AbstractView {

    private static final Log LOG = LogFactory.getLog(JsonView.class);
    protected Object         object;

    public JsonView(JsonWapper jw){
        this.object = jw.getInnerMap();
    }

    public JsonView(Object object){
        this.object = object;
    }

    @Override
    protected void renderMergedOutputModel(@SuppressWarnings("rawtypes") Map model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String jsonString = (this.object == null ? "{}" : YvanUtil.toJson(this.object));

        boolean useGzip = ResponseUtils.isSupportGzip(request);
        try {
            ResponseUtils.writeJSONToResponse(response, ResponseUtils.DEFAULT_ENCODING, jsonString, useGzip, false,
                                              "application/json;charset=utf-8");
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
