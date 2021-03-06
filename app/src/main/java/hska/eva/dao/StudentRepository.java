package hska.eva.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import hska.eva.dao.DatabaseSchema.dbStudent;
import hska.eva.domain.Student;

/**
 * Created by Steko on 11.01.2016.
 */
public class StudentRepository {

    private DatabaseHelper dbhelper;
    private SQLiteDatabase readableDb;

    public StudentRepository(Context context){
        dbhelper = DatabaseHelper.getInstance(context);
        readableDb = dbhelper.getReadableDatabase();
    }

    public Cursor findAllStudentsForOverview(){
        return readableDb.query(dbStudent.TABLE_NAME, null, null, null, null, null, null);
    }

    public Student findStudentWithLoginInformation(String email, String password){
        Cursor cursor = findAllStudentsForOverview();

        cursor.moveToFirst();
        do{
            Long studentId = cursor.getLong(cursor.getColumnIndex(DatabaseSchema.dbStudent._ID));
            String studentEmail = cursor.getString(cursor.getColumnIndex(DatabaseSchema.dbStudent.COLUMN_NAME_EMAIL));
            String studentPassword = cursor.getString(cursor.getColumnIndex(DatabaseSchema.dbStudent.COLUMN_NAME_PASSWORD));

            if (studentEmail.equals(email) && studentPassword.equals(password)) {
                return new Student(studentId, studentEmail, studentPassword, null, null);
            }
        }while(cursor.moveToNext());

        return null;
    }

    public Cursor findStudent(long studentId){
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(
                dbStudent.TABLE_NAME
                );
        return qb.query(readableDb,
                null,
                dbStudent.TABLE_NAME + "." + dbStudent._ID + " = " + studentId,
                null, null, null, null);
    }
}
