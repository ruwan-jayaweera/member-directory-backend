package lk.rcu.rcg2000.memberdirectory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtils() {

    }

    public static <T> T parseJson(final String content, final Class<T> clazz) throws IOException {
        logger.debug("Got response: [{}]", content);
        return OBJECT_MAPPER.readValue(content, clazz);
    }

    public static <T> List<T> parseListJson(final String content, final Class<T> clazz) throws IOException {
        logger.debug("Got response: [{}]", content);
        return OBJECT_MAPPER.readValue(content,
                                      TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
    }

    public static String writeJson(final Object object) throws JsonProcessingException {
        return Objects.nonNull(object) ? OBJECT_MAPPER.writeValueAsString(object) : "{}";
    }

}
