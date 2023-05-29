package com.example.obrestdatajpa.controllers;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@RestController
public class BookController {
    private final Logger log= LoggerFactory.getLogger(BookController.class);

    private BookRepository repository;

    public BookController(BookRepository repository) {

        this.repository = repository;
    }

    //CRUD sobre la entidad book

    // Buscar todos los libros
    @GetMapping("/api/books")
    public List<Book> findAll(){

        return repository.findAll();
    }

    // Buscar un solo libro en case de datos segun su id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        Optional<Book> bookOpt= repository.findById(id);
        if(bookOpt.isPresent()){
            return ResponseEntity.ok(bookOpt.get());
        } else {
           return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo libro en base de datos
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        if(book.getId()!= null){
            log.warn("Trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
       Book result = repository.save(book);
        return ResponseEntity.ok(result);
    }
    // Actualizar libro existente en base de datos
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null){
            log.warn("Trying to update a non existing book");
            return ResponseEntity.badRequest().build();
        }
        if(!repository.existsById(book.getId())){
            log.warn("Trying to update a non existing book");
            return ResponseEntity.badRequest().build();
        }
        Book result= repository.save(book);
        return ResponseEntity.ok(result);
    }
    // Borrar libro en base de datos
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id, @RequestHeader HttpHeaders header){
        System.out.println(header.get("User-Agent"));
        if(!repository.existsById(id)){
            log.warn("Trying to delete a non existing book");
            return ResponseEntity.badRequest().build();
        } else {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
    // Borrar todos los libros de la base de datos
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll(@RequestHeader HttpHeaders header){
        System.out.println(header.get("User-Agent"));
        log.info("Rest request for deleting all books");
        repository.deleteAll();
        return ResponseEntity.noContent().build();
    }

}
