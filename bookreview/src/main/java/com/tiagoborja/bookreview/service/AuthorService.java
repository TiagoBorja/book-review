package com.tiagoborja.bookreview.service;

import com.tiagoborja.bookreview.model.dto.AuthorDTO;
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

    public Author createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setBio(authorDTO.getBio());
        return authorRepository.save(author);
    }

    public Author updateAuthor(AuthorDTO authorDTO) {

        Author existingAuthor = authorRepository.findById(authorDTO.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found"));

        existingAuthor.setName(authorDTO.getName());
        existingAuthor.setBio(authorDTO.getBio());

        return authorRepository.save(existingAuthor);
    }

    public void deleteAuthor(Long id) {

        if (!authorRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");

        authorRepository.deleteById(id);
    }
}
