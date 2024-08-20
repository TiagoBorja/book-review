package com.tiagoborja.bookreview.controller;

import com.tiagoborja.bookreview.entity.Book;
import com.tiagoborja.bookreview.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/{authorId}")
    public ResponseEntity<Book> createBook(@PathVariable("authorId") Long authorId, @RequestBody Book book) {

        Book createdBook = bookService.createBook(authorId, book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}/{authorId}")
    public ResponseEntity<Book> updateBook(@PathVariable("bookId") Long bookId,
                                           @PathVariable("authorId") Long authorId,
                                           @RequestBody Book bookDetails) {

        Book updatedBook = bookService.updateBook(bookId, authorId, bookDetails);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
