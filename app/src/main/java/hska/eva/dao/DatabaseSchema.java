package hska.eva.dao;

import android.provider.BaseColumns;

/**
 * Created by Luke on 09.01.2016.
 */
public final class DatabaseSchema {
    /*****************/
    /**STUDENT TABLE**/
    /*****************/
    public static abstract class dbStudent implements BaseColumns {
        public static final String TABLE_NAME = "studenten";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_VORNAME = "vorname";
        public static final String COLUMN_NAME_NACHNAME = "nachname";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }

    /*****************/
    /** RATING TABLE**/
    /*****************/
    public static abstract class dbRating implements BaseColumns {
        public static final String TABLE_NAME = "rating";
        public static final String COLUMN_NAME_MOTIVATION = "motivation";
        public static final String COLUMN_NAME_TEAMFAEHIGKEIT = "teamfaehigkeit";
        public static final String COLUMN_NAME_KOMMUNIKATION = "kommunikation";
        public static final String COLUMN_NAME_KNOWHOW = "knowhow";
        public static final String COLUMN_NAME_STUDENTB_FK = "bewerter";
        public static final String COLUMN_NAME_STUDENT_FK = "bewertete";
    }
}
