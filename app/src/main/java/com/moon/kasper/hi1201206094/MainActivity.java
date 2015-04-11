package com.moon.kasper.hi1201206094;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.moon.kasper.hi1201206094.utilities.Boast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    private int counter = 0;

    @InjectView(R.id.main_activity_textview) TextView textView;
    @InjectView(R.id.main_activity_button) Button button;
    @InjectView(R.id.secret_level_imageview) ImageView imageView;

    @OnClick(R.id.main_activity_button)
    public void buttonClick(View view) {
        counter++;
        String text = "";

        if(counter < 5 || (counter > 5 && counter < 10)) {
            text = getString(R.string.text_part_one) + " " + counter + " " + getString(R.string.text_part_two);
            imageView.setVisibility(View.GONE);
        }

        if(counter == 5) {
            text = getString(R.string.secret_level_one_unlocked);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.android));
            imageView.setVisibility(View.VISIBLE);
        }

        if(counter == 10) {
            text = getString(R.string.secret_level_two_unlocked);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.android_eating_apple));
            imageView.setVisibility(View.VISIBLE);
        }

        if(!TextUtils.isEmpty(text)) {
            Boast.makeText(this, text, Boast.Level.Info);
            textView.setText(text);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        //Inject views and listeners to avoid boilerplate code
        ButterKnife.inject(this);

        if(getActionBar() != null) {
            getActionBar().setTitle(getString(R.string.main_title));
        }
    }
}