package com.tt.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class JsonUtils {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true) ;
//        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String writeValueAsString(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static byte[] writeValueAsBytes(Object value) {
        try {
            return mapper.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static boolean writeValue(Object value, OutputStream stream) {
        try {
            mapper.writeValue(stream, value);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static boolean writeValue(Object value, File file, boolean format) {
        try {
            if (format) {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, value);
            } else {
                mapper.writeValue(file, value);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean writeValue(Object value, File file) {
        return writeValue(value, file, false);
    }





    public static <T> T readValue(URL url, Class<T> type) {
        try {
            return mapper.readValue(url, type);
        } catch (IOException e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static <T> T readValue(InputStream stream, Class<T> type) {
        try {
            return mapper.readValue(stream, type);
        } catch (IOException e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static <T> T readValue(String string,TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(string, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static <T> T readValue(String string, Class<T> type) {
        try {
            return mapper.readValue(string, type);
        } catch (IOException e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static <T> T readValue(byte[] bytes, Class<T> type) {
        try {
            return mapper.readValue(bytes, type);
        } catch (IOException e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static <T> T readValue(File file, Class<T> type) {
        try {
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new RuntimeException("格式错误");
        }
    }

    public static <T> T convert(Object value, Class<T> type) {
        byte[] bytes = writeValueAsBytes(value);
        return bytes != null ? readValue(bytes, type) : null;
    }
}