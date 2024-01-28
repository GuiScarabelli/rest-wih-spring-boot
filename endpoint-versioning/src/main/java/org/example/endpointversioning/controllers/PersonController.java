package org.example.endpointversioning.controllers;

import org.example.endpointversioning.data.vo.v1.PersonVO;
import org.example.endpointversioning.data.vo.v2.PersonVOV2;
import org.example.endpointversioning.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

        // O @Autowired em conjunto com o @Service, Deixa com que o spring boot faça essa parte de instanciar
        // o "new PersonServices()", assim deixando o código mais versatil.
        @Autowired
        private PersonServices service;
       //  private PersonServices service = new PersonServices();


        @GetMapping(value = "/{id}",
                produces = MediaType.APPLICATION_JSON_VALUE)

        public PersonVO findById (
                @PathVariable(value = "id") Long id){
            return service.findById(id);
        }


        //Retorna uma lista de pessoas criadas
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public List<PersonVO> findAll(){
                return service.findAll();
        }

        // Cria uma pessoa nova
        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

        public PersonVO create(@RequestBody PersonVO personVO){
                return service.create(personVO);
        }

        //Atualiza um dado de uma pessoa nova
        @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

        public PersonVO update(@RequestBody PersonVO personVO){
                return service.update(personVO);
        }


        // Deleta uma pessoa
        @DeleteMapping(value="/{id}")
        public ResponseEntity<?> delete (@PathVariable (value = "id") Long id){
                service.delete(id);
        return ResponseEntity.noContent().build();
        }

        @PostMapping(value="/v2", produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

        public PersonVOV2 createV2(@RequestBody PersonVOV2 person){
                return service.createV2(person);
        }

}


