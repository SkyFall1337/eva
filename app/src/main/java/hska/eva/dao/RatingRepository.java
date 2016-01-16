package hska.eva.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import hska.eva.dao.DatabaseSchema.dbRating;

/**
 * Created by Luke on 12.01.2016.
 */
public class RatingRepository {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase writeableDb;
    private SQLiteDatabase readableDb;

    public RatingRepository(Context context){
        dbHelper = DatabaseHelper.getInstance(context);
        readableDb = dbHelper.getReadableDatabase();
        writeableDb = dbHelper.getWritableDatabase();
    }

    public Cursor findAllRatingsForStudent(long studentID){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(dbRating.TABLE_NAME
        );
        return qb.query(readableDb,
                null,
                dbRating.TABLE_NAME + "." + dbRating.COLUMN_NAME_STUDENT_FK + "=" + studentID,
                null, null, null, null);
    }
}
