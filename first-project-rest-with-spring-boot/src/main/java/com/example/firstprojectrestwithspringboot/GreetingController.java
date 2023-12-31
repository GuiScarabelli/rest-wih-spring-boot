package com.example.firstprojectrestwithspringboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello %s!";
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting") // cria o caminho
    public Greeting greeting (@RequestParam(value = "name", defaultValue = "Word")
                              String name){

        return new Greeting(counter.incrementAndGet(), String.format(template, name));

    }

    /*
        @RequestParam vai receber os valores que o metodo vai retornar  do endpoint
         Value: valor que ele vai receber como parametro na request
         defaultValue: valor default caso nada seja declarado no value
         Depois vem o atributto ue ele vai receber como parametro
    */

    /*
        Quando acessamos a pasta raiz, ele vai procurar o Controller (nesse caso o GreetingController)
        ele vai acessar esse controller pelo @RequestMapping que eu criei ali em cima.
     */
}
