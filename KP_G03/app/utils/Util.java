package utils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class Util {
	
	public static ObjectNode createResponse(Object response, boolean success) {
        ObjectNode result = Json.newObject();
        result.put("isSuccessful", success);
        result.putPOJO("data", response);
        return result;
    }
}
