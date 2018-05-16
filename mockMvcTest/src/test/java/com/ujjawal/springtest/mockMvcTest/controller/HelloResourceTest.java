package com.ujjawal.springtest.mockMvcTest.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloResourceTest  {

    private MockMvc mockMvc;
    @Mock
    private HelloService helloService;
    @InjectMocks
    private HelloResource helloResource;
    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(helloResource).build();

    }
    @Test
    public void testGetHello() throws Exception{
        Mockito.when(helloService.hello()).thenReturn("Hi");
        mockMvc.perform(MockMvcRequestBuilders.get("/helloservice/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hi"));
        Mockito.verify(helloService).hello();
    }

    @Test
    public void getHelloJson() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/helloservice/hello/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg", Matchers.is("this is india")));

    }

    @Test
    public void postHelloJson() throws Exception {
        String json = "{\n" +
                    "   \"id\": \"23\",\n" +
                    " \"msg\": \"Hello World\"\n" +
                    "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/helloservice/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(23)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.msg", Matchers.is("Hello World")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*",Matchers.hasSize(2)));


    }
}