package com.example.demo56.controller;

import static org.junit.jupiter.api.Assertions.*;




import com.example.demo56.user.UserJPAEntity;
import org.aspectj.lang.annotation.Before;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class AuthControllerTest extends AbstractTest {
    @Override
    @Before("")
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getUserAddress() throws Exception {
        String uri = "/auth/api";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        UserJPAEntity user = super.mapFromJson(content, UserJPAEntity.class);
        assertTrue(user.length > 0);
    }
    @Test
    public void createProduct() throws Exception {
        String uri = "/auth/api/signup";
        UserJPAEntity user = new UserJPAEntity();
        user.setPassword("3");
        user.setUsername("Ginger");
        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "User is created successfully");
    }
//    @Test
//    public void updateProduct() throws Exception {
//        String uri = "/products/2";
//        UserJPAEntity user = new UserJPAEntity();
//        user.setUsername("Lemon");
//        String inputJson = super.mapToJson(user);
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(inputJson)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Product is updated successsfully");
//    }
//    @Test
//    public void deleteProduct() throws Exception {
//        String uri = "/products/2";
//        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        assertEquals(content, "Product is deleted successsfully");
//    }
}