package io.advantageous.qbit.example;


import io.advantageous.qbit.annotation.PathVariable;
import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.annotation.RequestParam;

@RequestMapping("/my/service")
public class SimpleService {

    @RequestMapping("/add")
    public int add(@RequestParam("a") int a,
                   @RequestParam("b") int b) {

        return a + b;
    }

    @RequestMapping("/add2/{a}/{b}")
    public int add2( @PathVariable("a") int a,
                     @PathVariable("b") int b) {

        return a + b;
    }

    @RequestMapping("/add3/{a}/")
    public int add3( @PathVariable("a") int a,
                     @RequestParam("b") int b) {

        return a + b;
    }


}