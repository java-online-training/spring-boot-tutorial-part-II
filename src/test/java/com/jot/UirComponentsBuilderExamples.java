package com.jot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UirComponentsBuilderExamples {

    @Test
    public void simpleUrlExample(){
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

        Integer postId = 1;

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("jsonplaceholder.typicode.com")
            .path("/posts")
            .path("/{postid}")
            .buildAndExpand(postId)
            .encode();
        Assertions.assertEquals(uriComponents.toString(), "http://jsonplaceholder.typicode.com/posts/1");
    }

    @Test
    public void queryParamExample(){

        String filterParam = "John";

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
            .scheme("http")
            .host("jsonplaceholder.typicode.com")
            .path("/posts")
            .queryParam("filterByName", filterParam)
            .build()
            .encode();
        Assertions.assertEquals(uriComponents.toString(), "http://jsonplaceholder.typicode.com/posts?filterByName=John");
    }
}
