package com.example.dependencyinjection;

import com.example.dependencyinjection.model.Person;
import com.example.dependencyinjection.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

        // O @Autowired em conjunto com o @Service, Deixa com que o spring boot faça essa parte de instanciar
        // o "new PersonServices()", assim deixando o código mais versatil.
        @Autowired
        private PersonServices service;
        // private PersonServices service = new PersonServices();

        // Esse endpoint vai receber uma variavel, vai usar o método GET e vai
        // produzir  um JSON
        @RequestMapping(value = "/{id}",
                method = RequestMethod.GET,
                produces = MediaType.APPLICATION_JSON_VALUE)

        public Person findById (
                @PathVariable(value = "id") String id) throws Exception{
            return service.findById(id);
        }


        //Retorna uma lista de pessoas criadas
        @RequestMapping(method = RequestMethod.GET,
                produces = MediaType.APPLICATION_JSON_VALUE)
        public List<Person> findAll(){
                return service.findAll();
        }

        // Cria uma pessoa nova
        @RequestMapping(method = RequestMethod.POST,
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

        public Person create(@RequestBody Person person){
                return service.create(person);
        }

        //Atualiza um dado de uma pessoa nova
        @RequestMapping(method = RequestMethod.PUT,
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

        public Person update(@RequestBody Person person){
                return service.update(person);
        }


        // Deleta uma pessoa
        @RequestMapping(value="/{id}",
                method = RequestMethod.DELETE,
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)

        public void delete (@PathVariable (value = "id") String id){
                service.delete(id);
        }
}


