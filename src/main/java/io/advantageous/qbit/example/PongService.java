package io.advantageous.qbit.example;


import io.advantageous.qbit.annotation.RequestMapping;

@RequestMapping("/pongservice")
public class PongService {


    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }

}