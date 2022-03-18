package com.navigatingcancer.codingexercise.controller;

import com.navigatingcancer.codingexercise.model.BasicMathResult;
import com.navigatingcancer.codingexercise.model.MathOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathsController {

    @GetMapping("/math/add")
    public BasicMathResult addTwoIntegers(@RequestParam int parameter1,
                                          @RequestParam int parameter2){
        return new BasicMathResult(parameter1, parameter2, MathOperation.ADDITION, parameter1 + parameter1);
    }
}
