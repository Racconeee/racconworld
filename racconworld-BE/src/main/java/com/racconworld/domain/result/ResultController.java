package com.racconworld.domain.result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class ResultController {
    //quiz -> 퀴즈
    @GetMapping("/result/{quiz}/{value}")
    public String Result(@PathVariable Long quiz , @PathVariable Long id){
        return "hello";
    }
}
