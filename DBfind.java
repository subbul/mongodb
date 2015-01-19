package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by Subramanianl on 1/18/2015.
 */
public class DBfind {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("findTest");
        collection.drop();//essential to have a clean slate
        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", new Random().nextInt(2)).append("y",new Random().nextInt(100)));
        }
        DBObject query = new BasicDBObject("x",0).append("y",new BasicDBObject("$gt",0).append("$lt",90));
        //other way to create above is using query builder
        QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(90);
        System.out.println("Find one");
        DBObject one = collection.findOne();
        System.out.println(one);
        System.out.println("\nFind all");
        DBCursor cursor =   collection.find(query); // or builder.get() to pass query
        try{
            while(cursor.hasNext()) {
            DBObject cur = cursor.next();
            System.out.println(cur);
            }
        }finally {
            cursor.close();
        }
        System.out.println("\n count");
        long count = collection.count(query);
        System.out.println(count);
    }
}
