package com.moon.kasper.hi1201206094;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.moon.kasper.hi1201206094.utilities.Boast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    private int counter = 0;

    @InjectView(R.id.main_activity_textview) TextView textView;
    @InjectView(R.id.main_activity_button) Button button;

    @OnClick(R.id.main_activity_button)
    public void buttonClick(View view) {
        counter++;
        String text = getResources().getString(R.string.text_part_one) + " " + counter + " " + getResources().getString(R.string.text_part_two);

        Boast.makeText(this, text, Boast.Level.Info);
        textView.setText(text);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        //Inject views and listeners to avoid boilerplate code
        ButterKnife.inject(this);

        if(getActionBar() != null) {
            getActionBar().setTitle(getResources().getString(R.string.main_title));
        }
    }
}