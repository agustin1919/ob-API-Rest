package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    @Test
    void calculatedPrice() {
        Book book = new Book(null,"Relatos de un naufrago", "Gabriel Garcia Marquez", 430,
                1500d, LocalDate.of(1999, 12, 19), true);
        BookPriceCalculator calculator = new BookPriceCalculator();
        double price= calculator.calculatePrice(book);
        System.out.println(price);

        // Comprobaciones, aserciones
        assertTrue(price > 0);
        assertEquals(2400.0, price);

    }
}