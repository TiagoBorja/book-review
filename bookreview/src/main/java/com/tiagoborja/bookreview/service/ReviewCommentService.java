package com.tiagoborja.bookreview.service;

import com.tiagoborja.bookreview.entity.Review;
import com.tiagoborja.bookreview.entity.ReviewComment;
import com.tiagoborja.bookreview.entity.User;
import com.tiagoborja.bookreview.repository.ReviewCommentRepository;
import com.tiagoborja.bookreview.repository.ReviewRepository;
import com.tiagoborja.bookreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewCommentService {

    @Autowired
    private ReviewCommentRepository reviewCommentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReviewComment> getAllComments() {
        return reviewCommentRepository.findAll();
    }

    public ReviewComment getCommentById(Long id) {
        return reviewCommentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));
    }

    public ReviewComment createComment(Long reviewId, Long userId, ReviewComment reviewComment) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        reviewComment.setReview(review);
        reviewComment.setUser(user);
        return reviewCommentRepository.save(reviewComment);
    }

    public ReviewComment updateComment(Long commentId, ReviewComment commentDetails) {
        ReviewComment existingComment = reviewCommentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found"));

        existingComment.setComment(commentDetails.getComment());

        return reviewCommentRepository.save(existingComment);
    }

    public void deleteComment(Long commentId) {
        if (!reviewCommentRepository.existsById(commentId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");

        reviewCommentRepository.deleteById(commentId);
    }
}
