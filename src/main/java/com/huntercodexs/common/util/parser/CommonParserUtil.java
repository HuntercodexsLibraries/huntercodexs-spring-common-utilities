package com.huntercodexs.common.util.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

public class CommonParserUtil {

    private CommonParserUtil() {
    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        T valueConverted = null;
        if (Objects.nonNull(fromValue)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
            mapper.setTimeZone(TimeZone.getTimeZone("PST"));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.registerModule(new JodaModule());
            mapper.registerModule(new JavaTimeModule());
            valueConverted = (T) mapper.convertValue(fromValue, toValueType);
        }
        return valueConverted;
    }

    public static <T> List<T> convertValues(final Object fromValues, final Class<T> toValueType) {
        List<T> convertedValues = null;
        if (Objects.nonNull(fromValues)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
            mapper.setTimeZone(TimeZone.getTimeZone("PST"));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.registerModule(new JodaModule());
            mapper.registerModule(new JavaTimeModule());
            convertedValues = mapper.convertValue(fromValues, TypeFactory.defaultInstance().constructCollectionType(List.class, toValueType));

        }
        return convertedValues;
    }

    public static String convertToJSON(final Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new JavaTimeModule());

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> T convertJSONToObject(final String json, final Class<T> toValueType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonParser jsonParser = (new JsonFactory()).createParser(json);
            return (T) mapper.readValue(jsonParser, toValueType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
