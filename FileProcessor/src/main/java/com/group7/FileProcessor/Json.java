/*
 * Group 7
 * April 2, 2023
 */
package com.group7.FileProcessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;


/**
 *
 * @author keric
 */
public class Json {

    //create an instance of Object mapper
    private static final ObjectMapper objectMapper = getDefaultObjectMapper();
    
    //configuration settings for objectmapper
    private static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    //create method for turning string into json node
    public static JsonNode parse(String src) throws IOException{
        return objectMapper.readTree(src);
    }

    //Method for creating object from parsed data
    public static <A> A fromJson(JsonNode node , Class<A> clazz) throws JsonProcessingException{
        return objectMapper.treeToValue(node, clazz);
    }
}
