package com.navigatingcancer.codingexercise.controller;

import com.navigatingcancer.codingexercise.model.BasicMathResult;
import com.navigatingcancer.codingexercise.model.MathOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class MathsController {

    @GetMapping("/math/add")
    public BasicMathResult addTwoIntegers(@RequestParam int parameter1,
                                          @RequestParam int parameter2) throws Exception {
        int result;
        try {
            // this new java 8 addExact method will catch any potential integer overflow errors
            // Could have also casted the parameters into longs and then checked if the result > Integer.MAX_VALUE
            result = Math.addExact(parameter1,parameter2);
        } catch (ArithmeticException e) {
            throw new Exception("Integer overflow has occurred. Please choose different numbers that will produce " +
                    "a result that is within range.");
        }
        return new BasicMathResult(parameter1, parameter2, MathOperation.ADDITION, result);
    }

    @GetMapping("/math/subtract")
    public BasicMathResult subtractTwoIntegers(@RequestParam int parameter1,
                                          @RequestParam int parameter2) throws Exception {
        int result;
        try {
            // this new java 8 subtractExact method will catch any potential integer overflow errors
            // Could have also casted the parameters into longs and then checked if the result < Integer.MIN_VALUE
            result = Math.subtractExact(parameter1,parameter2);
        } catch (ArithmeticException e) {
            throw new Exception("Integer overflow has occurred. Please choose different numbers that will produce " +
                    "a result that is within range.");
        }
        return new BasicMathResult(parameter1, parameter2, MathOperation.SUBTRACTION, result);
    }
}
