package com.jot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UirComponentsBuilderExample {

    @Test
    public void simplebuildUrlExample(){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("jsonplaceholder.typicode.com")
            .path("/posts")
            .build()
            .encode();

        Assertions.assertEquals(uriComponents.toString(), "http://jsonplaceholder.typicode.com/posts");

    }

    @Test
    public void pathParamExample(){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("jsonplaceholder.typicode.com")
            .path("/posts")
            .path("/{postid}")
            .buildAndExpand("1")
            .encode();
        Assertions.assertEquals(uriComponents.toString(), "http://jsonplaceholder.typicode.com/posts/1");
    }

    @Test
    public void queryParamExample(){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("jsonplaceholder.typicode.com")
            .path("/posts")
            .queryParam("filterByName", "John")
            .build()
            .encode();
        Assertions.assertEquals(uriComponents.toString(), "http://jsonplaceholder.typicode.com/posts?filterByName=John");
    }


    
}
