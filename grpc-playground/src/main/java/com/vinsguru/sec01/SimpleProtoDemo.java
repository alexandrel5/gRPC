package com.vinsguru.sec01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vinsguru.models.PersonOuterClass;


public class SimpleProtoDemo {
    private static final Logger log = LoggerFactory.getLogger(SimpleProtoDemo.class);

    public static void main(String[] args) {

        var person =  PersonOuterClass.Person.newBuilder()
                .setName("Sam")
                .setAge(12)
                .build();
        log.info("Person: {}", person);
    }
}
