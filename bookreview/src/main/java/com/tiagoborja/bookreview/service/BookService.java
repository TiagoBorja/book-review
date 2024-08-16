package com.tiagoborja.bookreview.service;

import com.tiagoborja.bookreview.entity.Author;
import com.tiagoborja.bookreview.entity.Book;
import com.tiagoborja.bookreview.repository.AuthorRepository;
import com.tiagoborja.bookreview.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public List<Book> getAllBooks(Book book) {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Book createBook(Long authorId, Book book) {

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        book.setAuthor(author);
        return bookRepository.save(book);
    }

    public Book updateBook(Long id,
                           Long authorId,
                           Book bookDetails) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        book.setTitle(bookDetails.getTitle());
        book.setPublicationYear(bookDetails.getPublicationYear());
        book.setIsbn(bookDetails.getIsbn());
        book.setAuthor(author);

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");

        bookRepository.deleteById(id);
    }
}
