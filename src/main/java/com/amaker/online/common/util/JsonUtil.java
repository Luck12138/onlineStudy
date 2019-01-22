package com.amaker.online.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;

public class JsonUtil {
	
    private static ObjectMapper mapper;
    static{
        mapper=new ObjectMapper();
    }
    public static String toJson(Object obj) throws IOException {
        String json = mapper.writeValueAsString(obj);
        return json;
    }
}
