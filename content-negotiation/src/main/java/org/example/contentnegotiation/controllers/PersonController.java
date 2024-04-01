package org.example.contentnegotiation.controllers;

import org.example.contentnegotiation.data.vo.v1.PersonVO;
import org.example.contentnegotiation.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.contentnegotiation.util.MediaType;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

        @Autowired
        private PersonServices service;

        @GetMapping(value = "/{id}",
                produces = {
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.APPLICATION_YML})

        public PersonVO findById (
                @PathVariable(value = "id") Long id){
            return service.findById(id);
        }


        //Retorna uma lista de pessoas criadas
        @GetMapping(produces  = {
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_XML,
                MediaType.APPLICATION_YML})
        public List<PersonVO> findAll(){
                return service.findAll();
        }

        // Cria uma pessoa nova
        @PostMapping(
                produces  = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                consumes  = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})

        public PersonVO create(@RequestBody PersonVO personVO){
                return service.create(personVO);
        }

        //Atualiza um dado de uma pessoa nova
        @PutMapping(
                produces  = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                consumes  = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})

        public PersonVO update(@RequestBody PersonVO personVO){
                return service.update(personVO);
        }

        // Deleta uma pessoa
        @DeleteMapping(value="/{id}")
        public ResponseEntity<?> delete (@PathVariable (value = "id") Long id){
                service.delete(id);
        return ResponseEntity.noContent().build();
        }
}


