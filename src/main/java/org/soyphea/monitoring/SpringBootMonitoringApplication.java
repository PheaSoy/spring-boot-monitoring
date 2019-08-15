package org.soyphea.monitoring;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
public class SpringBootMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMonitoringApplication.class, args);
    }


    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> greeting(@RequestBody Book book) {
        Book resultBook = bookService.save(book);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(resultBook.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Service
    class BookService {

        private java.util.List<Book> books = new ArrayList<>();

        public Book save(Book book) {
            Optional<Book> findBook = books.stream().filter(bk -> bk.getTitle().equals(book.getTitle())).findFirst();
            if (findBook.isPresent()) {
                throw new RuntimeException();
            }
            Book saveBook = bookService.save(book);
            return saveBook;
        }

        public Book findBook(String title) {

            return null;
        }

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Book {
        String id;
        String title;
        String author;

    }
}