package com.tiagoborja.bookreview.repository;

import com.tiagoborja.bookreview.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
}
