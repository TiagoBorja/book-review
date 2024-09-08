package com.tiagoborja.bookreview.repository;

import com.tiagoborja.bookreview.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
