package hska.eva.service;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import hska.eva.dao.DatabaseSchema.dbRating;
import hska.eva.dao.RatingRepository;
import hska.eva.domain.Rating;

/**
 * Created by Luke on 12.01.2016.
 */
public class RatingService {
    private RatingRepository ratingRepository;

    public RatingService(Context ctx) {
        ratingRepository = new RatingRepository(ctx);
    }

    public List<Float> findRatingsForStudent(long studentID){
        List<Rating> ratings = new ArrayList<>();

        float sumMotivation = 0;
        float sumTeamfaehigkeit = 0;
        float sumKommunikation = 0;
        float sumKnowHow =  0;
        float sumGesamt = 0;
        int counter = 0;

        Cursor cursorRating = ratingRepository.findAllRatingsForStudent(studentID);

        if ( cursorRating.moveToFirst()) {
            do {
                long ratingId = cursorRating.getLong(cursorRating.getColumnIndex(dbRating._ID));
                int ratingMotivation = cursorRating.getInt(cursorRating.getColumnIndex(dbRating.COLUMN_NAME_MOTIVATION));
                int ratingTeamfaehigkeit = cursorRating.getInt(cursorRating.getColumnIndex(dbRating.COLUMN_NAME_TEAMFAEHIGKEIT));
                int ratingKommunikation = cursorRating.getInt(cursorRating.getColumnIndex(dbRating.COLUMN_NAME_KOMMUNIKATION));
                int ratingKnowhow = cursorRating.getInt(cursorRating.getColumnIndex(dbRating.COLUMN_NAME_KNOWHOW));
                long ratingStudentb_fk = cursorRating.getLong(cursorRating.getColumnIndex(dbRating.COLUMN_NAME_STUDENTB_FK));

                ratings.add(new Rating(ratingId, ratingMotivation, ratingTeamfaehigkeit, ratingKommunikation, ratingKnowhow, ratingStudentb_fk, studentID));
            }
            while (cursorRating.moveToNext());

            for (Rating rating : ratings) {
                sumMotivation += rating.getMotivation();
                sumTeamfaehigkeit += rating.getTeamfaehigkeit();
                sumKommunikation += rating.getKommunikation();
                sumKnowHow += rating.getKnowhow();
                counter += 1;
            }
            // Mittelwert der Attribute berechnen
            float mwMotivation = sumMotivation / counter;
            float mwTeamfaehigkeit = sumTeamfaehigkeit / counter;
            float mwKommunikation = sumKommunikation / counter;
            float mwKnowHow = sumKnowHow / counter;
            float mwGesamt = (mwMotivation + mwTeamfaehigkeit + mwKommunikation + mwKnowHow)/4;

            //Add MittelWerte in Liste
            List<Float> mittelWerte = new ArrayList<Float>();
            mittelWerte.add(mwMotivation);
            mittelWerte.add(mwTeamfaehigkeit);
            mittelWerte.add(mwKommunikation);
            mittelWerte.add(mwKnowHow);
            mittelWerte.add(mwGesamt);
            return mittelWerte;
        }

        else {
            List<Float> mittelWerte = new ArrayList<Float>();
            mittelWerte.add(sumMotivation);
            mittelWerte.add(sumTeamfaehigkeit);
            mittelWerte.add(sumKommunikation);
            mittelWerte.add(sumKnowHow);
            mittelWerte.add(sumGesamt);
            return mittelWerte;
        }
    }
}
