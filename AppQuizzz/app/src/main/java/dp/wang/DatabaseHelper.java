package dp.wang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizApp.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_QUESTIONS = "questions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TOPIC = "topic";
    private static final String COLUMN_QUESTION_TEXT = "question_text";
    private static final String COLUMN_OPTIONS = "options";
    private static final String COLUMN_CORRECT_ANSWER = "correct_answer";
    private static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_QUESTIONS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TOPIC + " TEXT, " +
                COLUMN_QUESTION_TEXT + " TEXT, " +
                COLUMN_OPTIONS + " TEXT, " +
                COLUMN_CORRECT_ANSWER + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        onCreate(db);
    }

    public void addQuestion(Question question, String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TOPIC, topic);
        values.put(COLUMN_QUESTION_TEXT, question.getQuestionText());
        values.put(COLUMN_OPTIONS, String.join(",", question.getOptions()));
        values.put(COLUMN_CORRECT_ANSWER, question.getCorrectAnswerIndex());
        db.insert(TABLE_QUESTIONS, null, values);
        db.close();
    }

    public List<Question> getQuestionsForTopic(String topic) {
        List<Question> questions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_QUESTIONS,
                new String[]{COLUMN_QUESTION_TEXT, COLUMN_OPTIONS, COLUMN_CORRECT_ANSWER},
                COLUMN_TOPIC + " = ?",
                new String[]{topic},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String questionText = cursor.getString(0);
                String optionsString = cursor.getString(1);
                int correctAnswer = cursor.getInt(2);
                String[] options = optionsString.split(",");
                questions.add(new Question(questionText, options, correctAnswer));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questions;
    }

    // Phương thức kiểm tra dữ liệu
    public void logAllQuestions() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTIONS, null);
        if (cursor.moveToFirst()) {
            do {
                String topic = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TOPIC));
                String questionText = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_QUESTION_TEXT));
                String options = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPTIONS));
                int correctAnswer = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_CORRECT_ANSWER));
                Log.d(TAG, "Topic: " + topic + ", Question: " + questionText + ", Options: " + options + ", Correct: " + correctAnswer);
            } while (cursor.moveToNext());
        } else {
            Log.d(TAG, "Không có câu hỏi nào trong cơ sở dữ liệu.");
        }
        cursor.close();
        db.close();
    }
}