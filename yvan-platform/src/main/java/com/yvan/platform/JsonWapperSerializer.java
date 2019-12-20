package com.yvan.platform;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class JsonWapperSerializer extends JsonSerializer<JsonWapper> {

    public static final SimpleModule MODEL;

    static {
        MODEL = new SimpleModule();
        MODEL.addSerializer(JsonWapper.class, new JsonWapperSerializer());
    }


    @Override
    public void serialize(JsonWapper value,
                          JsonGenerator gen,
                          SerializerProvider serializers) throws IOException, JsonProcessingException {

        gen.writeObject(value.getInnerMap());
    }
}
