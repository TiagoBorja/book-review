package com.tiagoborja.bookreview.service;

import com.tiagoborja.bookreview.model.entity.Author;
import com.tiagoborja.bookreview.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author updateAuthor(Long id, Author authorDetails) {

        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        author.setName(authorDetails.getName());
        author.setBio(authorDetails.getBio());

        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {

        if (!authorRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");

        authorRepository.deleteById(id);
    }
}
