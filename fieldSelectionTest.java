package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by Subramanianl on 1/18/2015.
 */
public class fieldSelectionTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection collection = courseDB.getCollection("findTest");
        collection.drop();//essential to have a clean slate
        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            collection.insert(new BasicDBObject("x", ran.nextInt(2)).append("y",ran.nextInt(100)).append("z",ran.nextInt(1000)));
        }
        //DBObject query = new BasicDBObject("x",0).append("y",new BasicDBObject("$gt",0).append("$lt",90));
        //other way to create above is using query builder
        QueryBuilder builder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(70);
        System.out.println("Find one");
        DBObject one = collection.findOne();
        System.out.println(one);
        System.out.println("\nFind all");
        DBCursor cursor =   collection.find(builder.get(),new BasicDBObject("y",true).append("_id",false)); // select everything except "x" field
        try{
            while(cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }
        System.out.println("\n count");
        long count = collection.count(builder.get());
        System.out.println(count);
    }
}
