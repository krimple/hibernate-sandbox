package chariot.hibernate.sandbox.util;

import chariot.hibernate.sandbox.common.BookRating;

import java.util.Date;

/**
 * Created by krimple @ Sep 12, 2010 8:33:01 PM
 */
public interface ReviewCreator<T> {

    T createReview(BookRating rating, String reviewText, Date reviewDate);

}
