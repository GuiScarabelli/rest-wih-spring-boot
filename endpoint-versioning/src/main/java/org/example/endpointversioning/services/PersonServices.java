package org.example.endpointversioning.services;

import org.example.endpointversioning.data.vo.v1.PersonVO;
import org.example.endpointversioning.data.vo.v2.PersonVOV2;
import org.example.endpointversioning.exceptions.ResourceNotFoundException;
import org.example.endpointversioning.mapper.DozerMapper;
import org.example.endpointversioning.mapper.custom.PersonMapper;
import org.example.endpointversioning.model.Person;
import org.example.endpointversioning.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

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

    // V2
    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating a person with V2!");
        var entity = mapper.converterVoToEntity(person);
        return mapper.converterEntityToVo(repository.save(entity));
    }
}