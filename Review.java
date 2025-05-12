package test1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.time.LocalDateTime;


/**
 *
 * @author Dell
 */
import java.time.LocalDateTime;

public final class Review {
    private final int reviewID;
    private final Customer customerID;
    private final int ratingNum;
    private final String comments;
    private final Restaurant restaurantID;
    private final LocalDateTime reviewDate;

    public Review(int reviewID, Customer customerID, Restaurant restaurantID, String comments, int ratingNum, LocalDateTime reviewDate) {
        this.reviewID = reviewID;
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        this.comments = comments;
        this.ratingNum = ratingNum;
        this.reviewDate = reviewDate;
    }

    public int getReviewID() {
        return reviewID;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public int getRatingNum() {
        return ratingNum;
    }

    public String getComments() {
        return comments;
    }

    public Restaurant getRestaurantID() {
        return restaurantID;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }
}


