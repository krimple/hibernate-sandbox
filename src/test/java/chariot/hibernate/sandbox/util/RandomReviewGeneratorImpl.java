package chariot.hibernate.sandbox.util;

import chariot.hibernate.sandbox.common.BookRating;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by krimple @ Sep 12, 2010 8:29:58 PM
 */
@Component
public class RandomReviewGeneratorImpl implements ReviewGenerator {

    private static final BookRating[] ratings = { BookRating.POOR, BookRating.SUBAVERAGE, BookRating.AVERAGE, BookRating.ABOVEAVERAGE, BookRating.EXCELLENT};

    private static final String[] sillyReviews = {
                "This was the worst book since somebody wrote the Cliffs Notes plot of Ishtar...",
                "I had more fun at a root canal",
                "Oh, so - so",
                "This wasn't that bad.  Better than reading Car and Driver...",
                "Wow, sign me up for that author's newsletter!"
    };

    public Object getRandomRating(ReviewCreator reviewCreator) {
        // select test data
        int val = ((int)(Math.random()*5));
        Date reviewDate = new Date(System.currentTimeMillis() - (long)(Math.random()* 1000000000000.0d));
        return reviewCreator.createReview(ratings[val], sillyReviews[val], reviewDate);
    }    
}
