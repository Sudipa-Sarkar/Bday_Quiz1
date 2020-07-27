package com.sudipasarkar.bday_quiz1;

import android.provider.BaseColumns;


    import android.provider.BaseColumns;

    public class QuizContract {
        private QuizContract() {
        }

        public static class QuestionTable implements BaseColumns {
            public static final String TABLE= "quiz_question";
            public static final String COLUMN_QUESTION= "question";
            public static final String COLUMN_OPTION1= "option1";
            public static final String COLUMN_OPTION2= "option2";
            public static final String COLUMN_OPTION3= "option3";
            public static final String COLUMN_OPTION4= "option4";
            public static final String COLUMN_ANSWER_NR= "answer_nr";
        }
    }


