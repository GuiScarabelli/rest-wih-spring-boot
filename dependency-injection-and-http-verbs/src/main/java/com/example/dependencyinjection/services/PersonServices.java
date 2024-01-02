package com.example.dependencyinjection.services;

import org.springframework.stereotype.Service;
import com.example.dependencyinjection.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    public List<Person> findAll () {
        logger.info("Finding all people in Datatbase *mock*");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person findById (String Id) {
        logger.info("Finding a person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Guilherme");
        person.setLastName("Quadros");
        person.setAddressName("Guaianases - São Paulo");
        person.setGenderName("Male");

        return person;
    }


    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name: "+i);
        person.setLastName("Last Name: "+i);
        person.setAddressName("Address: "+i);
        person.setGenderName("Male");

        return person;
    }

    public Person create(Person person) {
        logger.info("Creating a person!");
        return person;
    }

    public Person update (Person person) {
        logger.info("Updating a person!");
        return person;
    }

    public void delete (String id) {
        logger.info("Deleting a person!");
    }
}