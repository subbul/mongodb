package com.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Subramanianl on 1/18/2015.
 */


public class updateRemoveTest {
    public static void main(String[] args) throws UnknownHostException {

        MongoClient client = new MongoClient();
            DB courseDB = client.getDB("course");
            DBCollection collection = courseDB.getCollection("peopleUpdateRemove");
            collection.drop();//essential to have a clean slate
        List<String> names = Arrays.asList("alice", "bob", "charlie", "david", "emily");
        for (String name: names) {
            collection.insert(new BasicDBObject("_id",name));
        }

        DBCursor cursor =   collection.find();
        try{
            while(cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }

        collection.update(new BasicDBObject("_id","alice"),
                new BasicDBObject("age",24));
        System.out.println("Age Update");
        cursor =   collection.find();
        try{
            while(cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }
        collection.update(new BasicDBObject("_id","alice"),
                new BasicDBObject("gender","females"));
        System.out.println("Gender Update Update");
        // check the OUTPUT... AGE dissappers and only gender is appended
        cursor =   collection.find();
        try{
            while(cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }
        collection.update(new BasicDBObject("_id","alice"),
                new BasicDBObject("$set",new BasicDBObject("age","24")));
        System.out.println("Again Age Update with Set");
        // check the OUTPUT...
        cursor =   collection.find();
        try{
            while(cursor.hasNext()) {
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }

    }
}
