package cu.edu.cujae.touristpacks.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ApiRestMapper<T> {

    public List<T> mapList(Object response, Class<? extends T> cls)
            throws java.io.IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return mapper.readValue(
                String.valueOf(response),
                mapper.getTypeFactory().constructParametricType(List.class, cls));

    }

    public T mapOne(Object response, Class<? extends T> cls) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return mapper.readValue(String.valueOf(response), cls);
    }
}
