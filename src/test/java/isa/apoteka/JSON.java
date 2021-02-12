package isa.apoteka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON {
	
	public static String stringify(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "";
		}
    }

    public static <T> T parse(String string, Class<T> c) {
        ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
			return mapper.readValue(string, c);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
    }

}
