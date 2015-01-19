package com.tengen;

import com.mongodb.BasicDBObject;

import java.sql.Array;
import java.util.Arrays;

/**
 * Created by Subramanianl on 1/18/2015.
 */
public class DocumentRepresetationTest {
    public static void main(String[] args) {
        BasicDBObject doc = new BasicDBObject();
        doc.put("username","Alice");
        doc.put("programmer","true");
        doc.put("age",30);
        doc.put("languages", Arrays.asList("Java","C","python"));
        doc.put("address",new BasicDBObject("street","first main").append("city","bangalore").append("pin","560001"));

    }
}
