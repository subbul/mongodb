package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by Subramanianl on 1/18/2015.
 */
public class insertTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("insertTest");
        DBObject doc =  new BasicDBObject().append("x",1);
        System.out.println(doc);
        collection.insert(doc);
        System.out.println(doc);

    }
}
