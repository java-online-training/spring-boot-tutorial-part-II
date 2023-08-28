package com.jot.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private Map<Integer,Book> bookStore = new HashMap<>();

    @PostConstruct
    public void initBookStore(){
        bookStore.put(1, new Book("Lord Of the Rings", "J.R.R. Tolkien"));
        bookStore.put(2,new Book("Lord of Flies", "William Golding"));
        bookStore.put(3,new Book("1984", "George Orwell"));
    }

    @GetMapping(value = "/{bookId}")
    public Book getBook(@PathVariable Integer bookId){
        return bookStore.get(bookId);
    }

    @GetMapping
    public List<Book> getByAuthorName(@RequestParam(required = false, name = "author") String author){
        if(author != null){
            return bookStore.values().stream().filter( b -> b.author().contains(author) ).collect(Collectors.toList());
        } else {
            return bookStore.values().stream().collect(Collectors.toList());
        }
    }

    @PostMapping
    public Integer addBook(@RequestBody Book book) {
        Integer newId = bookStore.size()+1;
        bookStore.put(newId, book);
        return newId;
    }

    @PutMapping(value = "/{bookId}")
    public void updateBook(@RequestBody Book book, @PathVariable Integer bookId) {
        bookStore.put(bookId, book);
    }

    @DeleteMapping(value = "/{bookId}")
    public ResponseEntity<Void>  deleteBook(@PathVariable Integer bookId){
        Book removed = bookStore.remove(bookId);
        if (removed == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
}
