/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

//import Main.Customer;
//import Main.Restaurant;
import java.time.LocalDateTime;

/**
 *
 * @author Dell
 */
public class ReviewBuilder {
     private int reviewID;
    private Customer customerID;
    private int ratingNum;
    private String comments;
    private int restaurantID;
    private LocalDateTime reviewDate;
     public ReviewBuilder setReviewID(int reviewID) {
        this.reviewID = reviewID;
        return this;
    }

    public ReviewBuilder setCustomerID(Customer customerID) {
        this.customerID = customerID;
        return this;
    }

    public ReviewBuilder setRatingNum(int ratingNum) {
        this.ratingNum = ratingNum;
        return this;
    }

    public ReviewBuilder setComment(String comments) {
        this.comments = comments;
        return this;
    }

    public ReviewBuilder setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
        return this;
    }

    public ReviewBuilder setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
        return this;
    }

    public Review build() {
        Restaurant restaurant = new Restaurant(restaurantID); // assuming Restaurant can be created from ID
        return new Review(reviewID, customerID, restaurant, comments, ratingNum, reviewDate);
    }
}
