package io.advantageous.qbit.example;


import io.advantageous.qbit.annotation.RequestMapping;

@RequestMapping
public class PongService {


    @RequestMapping
    public String ping() {
        return "pong";
    }

}