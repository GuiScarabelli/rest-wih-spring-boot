package com.example.dependencyinjection.services;

import com.example.dependencyinjection.exceptions.ResourceNotFoundException;
import com.example.dependencyinjection.mapper.DozerMapper;
import com.example.dependencyinjection.model.Person;
import com.example.dependencyinjection.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dependencyinjection.data.vo.v1.PersonVO;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll () {
        return DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById (Long id) {
        logger.info("Finding a person!");

        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating a person!");
        var entity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update (PersonVO personVO) {
        logger.info("Updating a person!");

        var entity = repository.findById(personVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete (Long id) {
        logger.info("Deleting a person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);

    }
}