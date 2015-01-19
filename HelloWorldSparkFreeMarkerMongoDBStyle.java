package com.tengen;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * Created by Subramanianl on 1/7/2015.
 */
public class HelloWorldSparkFreeMarkerMongoDBStyle {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration configuration =  new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreeMakerStyle.class,"/");
        MongoClient client = new MongoClient("localhost",27017);
        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");
        Spark.get(new Route("/") {
            @Override
            public Object handle(final Request request, final Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    DBObject document = collection.findOne();
                   helloTemplate.process(document, writer);
                    //System.out.println(writer);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
