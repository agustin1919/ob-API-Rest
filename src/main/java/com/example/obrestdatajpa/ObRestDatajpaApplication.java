package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repositories.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		// CRUD: Create, Retrieve, Update, Delete
		// Crear libro
		Book book1 = new Book(null,"La divina comedia", "Dante Aligheri",500, 2500d, LocalDate.of(2018, 11, 1), true);
		Book book2 = new Book(null,"Inferno", "Dante Aligheri",250, 1500d, LocalDate.of(2017, 11, 1), true);
		// Almacenar libro
		System.out.println("Numero de libros en base de datos: "+ repository.findAll().size());
		repository.save(book1);
		repository.save(book2);
		System.out.println("Numero de libros en base de datos: "+ repository.findAll().size());
		// Recuperar libro
		System.out.println(repository.findAll());

	}

}
