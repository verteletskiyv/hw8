package ua.profitsoft.hw8;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MvcResult;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class TestUtil {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T>T parseJson(String json, Class<T> c) {
        try {
            return OBJECT_MAPPER.readValue(json, c);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }

    public static <T>T parseJson(String json, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(json, valueTypeRef);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }

    public static <T> T parseJsonArray(MvcResult mvcResult) {
        try {
            return OBJECT_MAPPER.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), new TypeReference<>() {});
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            throw new RuntimeException("Error parsing json", e);

        }
    }

}
