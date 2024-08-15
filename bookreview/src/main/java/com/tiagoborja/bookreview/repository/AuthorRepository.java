package com.tiagoborja.bookreview.repository;

import com.tiagoborja.bookreview.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Long, Author> {
}
