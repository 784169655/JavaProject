package com.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

//    @Test
//    public void contextLoads() {
//    }

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test   //当查询成功时，测试案例
    public void whenQuerySuccess() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8))

                //执行完上面请求后期望    返回的状态码是 200
            .andExpect(MockMvcResultMatchers.status().isOk())

                //期望返回的是集合，集合的长度是3
            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }
}



