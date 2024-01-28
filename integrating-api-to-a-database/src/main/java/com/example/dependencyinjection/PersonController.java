package com.example.dependencyinjection;

import com.example.dependencyinjection.model.Person;
import com.example.dependencyinjection.services.PersonServices;
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

        public Person findById (
                @PathVariable(value = "id") Long id){
            return service.findById(id);
        }


        //Retorna uma lista de pessoas criadas
        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public List<Person> findAll(){
                return service.findAll();
        }

        // Cria uma pessoa nova
        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

        public Person create(@RequestBody Person person){
                return service.create(person);
        }

        //Atualiza um dado de uma pessoa nova
        @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

        public Person update(@RequestBody Person person){
                return service.update(person);
        }


        // Deleta uma pessoa

        @DeleteMapping(value="/{id}")
        public ResponseEntity<?> delete (@PathVariable (value = "id") Long id){
                service.delete(id);
        return ResponseEntity.noContent().build();
        }
}


