package com.vinsguru.sec02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vinsguru.models.sec02.Person;

public class ProtoDemo {

    private static final Logger log = LoggerFactory.getLogger(ProtoDemo.class);
    public static void main(String[] args) {

        var person1 = createPerson();
        var person2 = createPerson();

        log.info("equals {}", person1.equals(person2));
        log.info("== {}", person1 == person2);

        //mutable? no

        //create another instance with diff values
        var person3 = person1.toBuilder().setName("mike").build();
        log.info("person3 {}", person3);


        //compare
        log.info("equals {}", person1.equals(person3));
        log.info("== {}", person1 == person3);

        //null?
        var person4 = person1.toBuilder().clearName().build();
        log.info("person4 {}", person4);




    }
    private static Person createPerson() {
        return Person.newBuilder()
                .setName("sam")
                .setAge(12)
                .build();
    }
}
