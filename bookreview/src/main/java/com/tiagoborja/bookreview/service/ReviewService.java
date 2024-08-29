package com.tiagoborja.bookreview.service;

import com.tiagoborja.bookreview.entity.Book;
import com.tiagoborja.bookreview.entity.Review;
import com.tiagoborja.bookreview.entity.User;
import com.tiagoborja.bookreview.repository.BookRepository;
import com.tiagoborja.bookreview.repository.ReviewRepository;
import com.tiagoborja.bookreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Review createReview(Long bookId, Long userId, Review review) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        review.setBook(book);
        review.setUser(user);
        return reviewRepository.save(review);
    }

    public Review updateReview(Long reviewId, Review reviewDetails) {
        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found"));

        existingReview.setRating(reviewDetails.getRating());
        existingReview.setComment(reviewDetails.getComment());

        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Long reviewId){

        if (!reviewRepository.existsById(reviewId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");

        reviewRepository.deleteById(reviewId);
    }
}
