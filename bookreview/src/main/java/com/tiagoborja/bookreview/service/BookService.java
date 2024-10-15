package com.tiagoborja.bookreview.service;

import com.tiagoborja.bookreview.model.dto.BookDTO;
import com.tiagoborja.bookreview.model.entity.Author;
import com.tiagoborja.bookreview.model.entity.Book;
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

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Book createBook(BookDTO bookDTO) {

        Author existingAuthor = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setAuthor(existingAuthor);
        return bookRepository.save(book);
    }

    public Book updateBook(BookDTO bookDTO) {

        Book existingBook = bookRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Author authorId = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));


        existingBook.setId(bookDTO.getId());
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());
        existingBook.setIsbn(bookDTO.getIsbn());
        existingBook.setAuthor(authorId);

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");

        bookRepository.deleteById(id);
    }
}
