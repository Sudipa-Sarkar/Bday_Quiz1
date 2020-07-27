package com.sudipasarkar.bday_quiz1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sudipasarkar.bday_quiz1.QuizContract.*;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quizz.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question("Jibon ta puro dhone pata sudhu bato r chato","Souradeep","Shrijit","Pallab","Abhijeet",3);
        addQuestion(q1);
        Question q2 = new Question("Amara 2jon abhishek er help korte giye case kheyche","Anjali","Abhijeet","Sumit","Pratyay",3);
        addQuestion(q2);
        Question q3 = new Question("General Shepherd betrayed us", "Pratyay", "Shrijit", "Souradeep","Aditya", 1);
        addQuestion(q3);
        Question q4 = new Question("ye deewar todta kiun nahi hain", "Sumit", "Abhijeet", "Rownak","Aditi" ,3);
        addQuestion(q4);
        Question q5 = new Question("Hello Hello Debanjan Debanjan", "Sudipa", "Souradeep", "Aishi","Sayanti", 2);
        addQuestion(q5);
        Question q6 = new Question("ab main teri baby nehi rhi", "Anjali", "Sumit", "Abhijeet","Sudipa", 1);
        addQuestion(q6);
        Question q7 = new Question("Bhabi kaise hain?", "Sumit", "Aditya", "Shrijit","Abhijeet", 4);
        addQuestion(q7);
        Question q8 = new Question("Meet me at Delhi road..We will have our fab chai", "Aditya", "Mamta", "Debanjoli","Shrijit", 1);
        addQuestion(q8);
        Question q9 = new Question("Baccha chele!", "Aishi", "Sudipa", "Sumit","Sayanti", 2);
        addQuestion(q9);
        /*Question q10 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q10);
        Question q11 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q11);
        Question q12 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q12);
        Question q13 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q13);
        Question q14 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q5);
        Question q15 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q15);
        Question q16 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q16);
        Question q17 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q17);
        Question q18 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q18);
        Question q19 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q19);
        Question q20 = new Question("B is correct again", "A", "B", "C","D", 2);
        addQuestion(q20); */

    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAns());
        db.insert(QuestionTable.TABLE, null, cv);
    }
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
                question.setAns(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}