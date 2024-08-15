package com.tiagoborja.bookreview.repository;

import com.tiagoborja.bookreview.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Long, Book> {
}
