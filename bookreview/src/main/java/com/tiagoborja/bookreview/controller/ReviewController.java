package com.tiagoborja.bookreview.controller;

import com.tiagoborja.bookreview.entity.Review;
import com.tiagoborja.bookreview.entity.User;
import com.tiagoborja.bookreview.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllUsers() {

        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/id/{reviewId}")
    public ResponseEntity<Review> getUserById(@PathVariable("reviewId") Long reviewId) {

        Review review = reviewService.getReviewById(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/book/{bookId}/user/{userId}")
    public ResponseEntity<Review> createReview(@PathVariable("bookId") Long bookId,
                                               @PathVariable("userId") Long userId,
                                               @RequestBody Review review) {
        Review createdReview = reviewService.createReview(bookId, userId, review);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    @PutMapping("/id/{reviewId}/book/{bookId}/user/{userId}")
    public ResponseEntity<Review> updateReview(@PathVariable("reviewId") Long reviewId,
                                               @RequestBody Review review) {

        Review updatedReview = reviewService.updateReview(reviewId, review);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/id/{reviewId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
