package com.ujjawal.springtest.mockMvcTest.controller;

import com.ujjawal.springtest.mockMvcTest.model.HelloMsg;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/helloservice")
public class HelloResource {

    private HelloService helloService;
    @GetMapping(path = "/hello")
    public String getHello(){
        return helloService.hello();
    }
    @GetMapping(path = "/hello/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloMsg getHelloJson(){
        HelloMsg helloMsg = new HelloMsg();
        helloMsg.setId(10);
        helloMsg.setMsg("this is india");
        return helloMsg;
    }
    @PostMapping(path="/hello",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public HelloMsg postHelloJson(@RequestBody HelloMsg helloMsg){
        return helloMsg;
    }
}
