package com.itecher.adampadam.itecher;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import com.itecher.adampadam.itecher.adapter.Word;
import com.itecher.adampadam.itecher.data.DictDbHelper;

public class BeginPracticeActivity extends AppCompatActivity {

    private static Context context;
    private static ImageButton back;
    private static TextView question;
    private static TextView right;
    private static Button answer1;
    private static Button answer2;
    private static Button answer3;
    private static Button answer4;
    private static Button next;
    public static ArrayList<Word> list_all_word;
    public static ArrayList<Word> list_learn_word;
    private static DictDbHelper dictdbh;
    private static SQLiteDatabase db;
    private static int right_answer;
    private static ArrayList<Word> list;
    public static boolean first = true;
    private static int MAX_ID = 167;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_practice);

        Log.d("mLog", "---------------------------------------------------------------------------------------------------------");

        context = getApplicationContext();
        back = (ImageButton) findViewById(R.id.back_btn);
        question = (TextView) findViewById(R.id.question);
        right = (TextView) findViewById(R.id.right);
        answer1 = (Button) findViewById(R.id.answer_1_btn);
        answer2 = (Button) findViewById(R.id.answer_2_btn);
        answer3 = (Button) findViewById(R.id.answer_3_btn);
        answer4 = (Button) findViewById(R.id.answer_4_btn);
        next = (Button) findViewById(R.id.next);

        dictdbh = new DictDbHelper(context);
        db = dictdbh.getReadableDatabase();

        list_learn_word = dictdbh.get_word_from_db(db);
        list_all_word = getAllWord();

        if (first) {
            update();
        }

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer1.setClickable(false);
                answer2.setClickable(false);
                answer3.setClickable(false);
                answer4.setClickable(false);
                right.setVisibility(view.VISIBLE);
                next.setVisibility(view.VISIBLE);

                if (right_answer == 1) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() + 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.green));
                    right.setText(getResources().getString(R.string.right));
                    right.setBackgroundColor(getResources().getColor(R.color.green));


                } else if (right_answer == 2) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel());
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.green));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                } else if (right_answer == 3) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.green));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                } else {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.green));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                }

                dictdbh.update_word_from_db(db, list.get(right_answer));

            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer1.setClickable(false);
                answer2.setClickable(false);
                answer3.setClickable(false);
                answer4.setClickable(false);
                right.setVisibility(view.VISIBLE);
                next.setVisibility(view.VISIBLE);

                if (right_answer == 1) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.green));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));


                } else if (right_answer == 2) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() + 1);
                    answer2.setBackgroundColor(getResources().getColor(R.color.green));
                    right.setText(getResources().getString(R.string.right));
                    right.setBackgroundColor(getResources().getColor(R.color.green));

                } else if (right_answer == 3) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.green));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                } else {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.green));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                }

                dictdbh.update_word_from_db(db, list.get(right_answer));

            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer1.setClickable(false);
                answer2.setClickable(false);
                answer3.setClickable(false);
                answer4.setClickable(false);
                right.setVisibility(view.VISIBLE);
                next.setVisibility(view.VISIBLE);

                if (right_answer == 1) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.green));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));


                } else if (right_answer == 2) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.green));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                } else if (right_answer == 3) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() + 1);
                    answer3.setBackgroundColor(getResources().getColor(R.color.green));
                    right.setText(getResources().getString(R.string.right));
                    right.setBackgroundColor(getResources().getColor(R.color.green));

                } else {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.green));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                }

                dictdbh.update_word_from_db(db, list.get(right_answer));

            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer1.setClickable(false);
                answer2.setClickable(false);
                answer3.setClickable(false);
                answer4.setClickable(false);
                right.setVisibility(view.VISIBLE);
                next.setVisibility(view.VISIBLE);

                if (right_answer == 1) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.green));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                } else if (right_answer == 2) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.green));
                    answer3.setBackgroundColor(getResources().getColor(R.color.red));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                } else if (right_answer == 3) {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() - 1);
                    answer1.setBackgroundColor(getResources().getColor(R.color.red));
                    answer2.setBackgroundColor(getResources().getColor(R.color.red));
                    answer3.setBackgroundColor(getResources().getColor(R.color.green));
                    answer4.setBackgroundColor(getResources().getColor(R.color.red));
                    right.setText(getResources().getString(R.string.not_right));
                    right.setBackgroundColor(getResources().getColor(R.color.red));

                } else {

                    (list.get(right_answer)).setLevel((list.get(right_answer)).getLevel() + 1);
                    answer4.setBackgroundColor(getResources().getColor(R.color.green));
                    right.setText(getResources().getString(R.string.right));
                    right.setBackgroundColor(getResources().getColor(R.color.green));

                }

                dictdbh.update_word_from_db(db, list.get(right_answer));

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.isBack = true;
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                right.setVisibility(view.INVISIBLE);
                next.setVisibility(view.INVISIBLE);
                answer1.setBackgroundColor(getResources().getColor(R.color.dark_white));
                answer2.setBackgroundColor(getResources().getColor(R.color.dark_white));
                answer3.setBackgroundColor(getResources().getColor(R.color.dark_white));
                answer4.setBackgroundColor(getResources().getColor(R.color.dark_white));
                update();

            }
        });

    }

    public static ArrayList<Word> getAllWord() {

        ArrayList<Word> word = new ArrayList<Word>();

        String[] list = context.getResources().getStringArray(R.array.dict);

        for (int i = 0; i < MAX_ID; i++) {

            String[] s = list[i].split(";");

            if (Integer.valueOf(s[3]) == 1) {

                word.add(new Word(false, s[0], s[1], "полезные глаголы", i + 1));

            } else if (Integer.valueOf(s[3]) == 2) {

                word.add(new Word(false, s[0], s[1], "компютер", i + 1));

            } else if (Integer.valueOf(s[3]) == 3) {

                word.add(new Word(false, s[0], s[1], "программирование", i + 1));

            } else if (Integer.valueOf(s[3]) == 4) {

                word.add(new Word(false, s[0], s[1], "интернет", i + 1));

            } else if (Integer.valueOf(s[3]) == 4) {

                word.add(new Word(false, s[0], s[1], "ОШИБКА", i + 1));

            }

        }

        return word;

    }

    private static ArrayList<Word> get4Words() {

        return null;
    }

    private static void fillData(ArrayList<Word> list, int b) {

    }

    public static void update() {

        if (list_learn_word.size() < 1) end();

        Random rnd = new Random();
        fillData(list_learn_word, PracticeActivity.group_number);
        list = get4Words();

        if (PracticeActivity.type_number == 1) {

            question.setText((list.get(right_answer)).getRus_word());
            answer1.setText((list.get(0)).getEng_word());
            answer2.setText((list.get(1)).getEng_word());
            answer3.setText((list.get(2)).getEng_word());
            answer4.setText((list.get(3)).getEng_word());

        } else if (PracticeActivity.type_number == 2) {

            question.setText((list.get(right_answer)).getEng_word());
            answer1.setText((list.get(0)).getRus_word());
            answer2.setText((list.get(1)).getRus_word());
            answer3.setText((list.get(2)).getRus_word());
            answer4.setText((list.get(3)).getRus_word());

        } else {

            if (rnd.nextInt(2) == 1) {

                question.setText((list.get(right_answer)).getEng_word());
                answer1.setText((list.get(0)).getRus_word());
                answer2.setText((list.get(1)).getRus_word());
                answer3.setText((list.get(2)).getRus_word());
                answer4.setText((list.get(3)).getRus_word());

            } else {

                question.setText((list.get(right_answer)).getRus_word());
                answer1.setText((list.get(0)).getEng_word());
                answer2.setText((list.get(1)).getEng_word());
                answer3.setText((list.get(2)).getEng_word());
                answer4.setText((list.get(3)).getEng_word());

            }

        }

        Log.d("mLog", "");
        Log.d("mLog", "");
        Log.d("mLog", "");
        Log.d("mLog", "---------------------------------------------------------------------------------------------------------");
        Log.d("mLog", "                 Word: " + question.getText().toString());
        Log.d("mLog", "                 answer1: " + answer1.getText().toString());
        Log.d("mLog", "                 answer2: " + answer2.getText().toString());
        Log.d("mLog", "                 answer3: " + answer3.getText().toString());
        Log.d("mLog", "                 answer4: " + answer4.getText().toString());
        Log.d("mLog", "                 right: " + String.valueOf(right_answer));
        Log.d("mLog", "                 lvl: " + String.valueOf((list.get(right_answer).getLevel())));
        Log.d("mLog", "---------------------------------------------------------------------------------------------------------");
        Log.d("mLog", "");
        Log.d("mLog", "");
        Log.d("mLog", "");

    }

    private static void end() {

        list_learn_word = dictdbh.get_word_from_db(db);
        MainActivity.isBack = true;
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}