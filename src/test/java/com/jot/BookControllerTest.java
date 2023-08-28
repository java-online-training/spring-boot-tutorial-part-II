package com.jot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.jot.rest.Book;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class BookControllerTest {


    private static final String BASE_URL = "http://localhost:8080/books";

    private RestTemplate template = new RestTemplate();

    @Test
    public void testGetBooks(){
        Book[] books = template.getForObject(BASE_URL,Book[].class);
        assertEquals(3, books.length);
    }

    @Test
    public void testGetBooksWithFilter(){
        Book[] books = template.getForObject(BASE_URL+"?author=Tolkien",Book[].class);
        assertEquals(1, books.length);
    }

    @Test
    public void testAddDeleteBook(){
        ResponseEntity<Integer> response = template.postForEntity(BASE_URL, new Book("The Alchemist", " Paulo Coelho"), Integer.class);
        
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

        template.delete(BASE_URL+"/"+response.getBody());
    }

    @Test
    public void testDeleteNotFound(){
        Assertions.assertThrows(
            HttpClientErrorException.class,
            () -> template.delete(BASE_URL+"/42"),
            "Not found exception missing.");
    }

    @Test
    public void testUpdateBook(){

        String url = BASE_URL+"/2";

         template.put(url, new Book("The Alchemist", " Paulo Coelho"));

         Book response = template.getForObject(url, Book.class);
        
         assertEquals("The Alchemist", response.name() );
    }
}
