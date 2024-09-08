package com.tiagoborja.bookreview.repository;

import com.tiagoborja.bookreview.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
