package com.example.dependencyinjection.services;

import com.example.dependencyinjection.exceptions.ResourceNotFoundException;
import com.example.dependencyinjection.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dependencyinjection.model.Person;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll () {
        logger.info("Finding all people in Datatbase *mock*");
        return repository.findAll();
    }

    public Person findById (Long id) {
        logger.info("Finding a person!");
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Guilherme");
        person.setLastName("Quadros");
        person.setAddressName("Guaianases - São Paulo");
        person.setGenderName("Male");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
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
        return repository.save(person);
    }

    public Person update (Person person) {
        logger.info("Updating a person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddressName(person.getAddressName());
        entity.setGenderName(person.getGenderName());

        return repository.save(person);
    }

    public void delete (Long id) {
        logger.info("Deleting a person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);

    }
}