package com.example.parametersandexceptionhandler;

import com.example.parametersandexceptionhandler.converters.NumberConverter;
import com.example.parametersandexceptionhandler.exceptions.UnsupportedMathOperationException;
import com.example.parametersandexceptionhandler.operations.Operations;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {
        private static final AtomicLong counter = new AtomicLong();

        private Operations operations  = new Operations();

        // Quando utilizamos o Path Param, temos que especificar o metodo da Request.
        // Os valores entre as chaves são as variaveis que vao ser declaradas com a notação PathVariable

        @RequestMapping(value = "/sum/{number1}/{number2}",
                            method = RequestMethod.GET)


        public Double sum (
                @PathVariable(value = "number1") String number1,
                @PathVariable(value = "number2") String number2
                )throws Exception{

            if(!NumberConverter.isNumeric(number1) || !NumberConverter.isNumeric(number2)){
                throw new UnsupportedMathOperationException("Please set a numeric value");
            }
            return operations.sum(NumberConverter.convertToDouble(number1), NumberConverter.convertToDouble(number2));

        }

        @RequestMapping(value = "/subtraction/{number1}/{number2}",
                            method = RequestMethod.GET)
        public Double subtraction (
                @PathVariable(value = "number1") String number1,
                @PathVariable(value = "number2") String number2
                )throws Exception{

            if(!NumberConverter.isNumeric(number1) || !NumberConverter.isNumeric(number2)){
                throw new UnsupportedMathOperationException("Please set a numeric value");
            }
            return operations.subtraction(NumberConverter.convertToDouble(number1), NumberConverter.convertToDouble(number2));
        }

        @RequestMapping(value = "/multiplication/{number1}/{number2}",
                            method = RequestMethod.GET)
        public Double multiplication (
                @PathVariable(value = "number1") String number1,
                @PathVariable(value = "number2") String number2
                )throws Exception{

            if(!NumberConverter.isNumeric(number1) || !NumberConverter.isNumeric(number2)){
                throw new UnsupportedMathOperationException("Please set a numeric value");
            }
            return operations.multiplication(NumberConverter.convertToDouble(number1), NumberConverter.convertToDouble(number2));
        }

        @RequestMapping(value = "/division/{number1}/{number2}",
                            method = RequestMethod.GET)
        public Double division (
                @PathVariable(value = "number1") String number1,
                @PathVariable(value = "number2") String number2
                )throws Exception{

            if(!NumberConverter.isNumeric(number1) || !NumberConverter.isNumeric(number2)){
                throw new UnsupportedMathOperationException("Please set a numeric value");
            }
            return operations.division(NumberConverter.convertToDouble(number1), NumberConverter.convertToDouble(number2));
        }

        @RequestMapping(value = "/mean/{number1}/{number2}",
                            method = RequestMethod.GET)
        public Double mean (
                @PathVariable(value = "number1") String number1,
                @PathVariable(value = "number2") String number2
                )throws Exception{

            if(!NumberConverter.isNumeric(number1) || !NumberConverter.isNumeric(number2)){
                throw new UnsupportedMathOperationException("Please set a numeric value");
            }
            return operations.mean(NumberConverter.convertToDouble(number1), NumberConverter.convertToDouble(number2));
        }

        @RequestMapping(value = "/sqrt/{number}",
                            method = RequestMethod.GET)
        public Double sqrt (
                @PathVariable(value = "number") String number
                )throws Exception{

            if(!NumberConverter.isNumeric(number)){
                throw new UnsupportedMathOperationException("Please set a numeric value");
            }
            return operations.squareRoot(NumberConverter.convertToDouble(number));
        }
}


