package com.itecher.adampadam.itecher.practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.itecher.adampadam.itecher.MainActivity;
import com.itecher.adampadam.itecher.R;

public class EndPracticeActivity extends AppCompatActivity {

    private static Context context;
    protected static int all_word_number = 0;
    protected static int right_word_number = 0;
    protected static Button go_end;
    protected static Button go_again;
    protected static TextView status;
    protected static TextView count;
    protected static boolean first_end = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_end);

        context = getApplicationContext();
        go_again = (Button) findViewById(R.id.go_again_btn);
        go_end = (Button) findViewById(R.id.go_end_btn);
        status = (TextView) findViewById(R.id.status);
        count = (TextView) findViewById(R.id.count);

        if (first_end) {
            update();
        }

        go_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                all_word_number = 0;
                right_word_number = 0;
                MainActivity.isBack = true;
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

        go_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (((BeginPracticeActivity.dictdbh).get_word_from_db(BeginPracticeActivity.db)).size() >= 1) {

                    all_word_number = 0;
                    right_word_number = 0;
                    if (!BeginPracticeActivity.first_begin) BeginPracticeActivity.update();
                    startActivity(new Intent(context, BeginPracticeActivity.class));

                } else {

                    Toast.makeText(context, getResources().getString(R.string.is_little_word), Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    protected static void update() {

        status.setText(getStatus(right_word_number, all_word_number) + context.getString(R.string.you_answer_text));

        count.setText(right_word_number + "/" + all_word_number);

    }

    private static String getStatus(int x, int y) {

        int p = 100 - (100 * y - 100 * x) / y;

        if (p > 75) {

            return context.getString(R.string.beast_practice);

        } else if (p > 50 && p <= 75) {

            return context.getString(R.string.good__practice);

        } else if (p > 25 && p <= 50) {

            return context.getString(R.string.notbad__practice);

        } else {

            return context.getString(R.string.bad_practice);

        }

    }
}
