package com.moon.kasper.hi1201206094.utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.moon.kasper.hi1201206094.R;

public class Boast {

    //region Variables
    //Debug TAG
    private transient final String TAG = ((Object)this).getClass().getSimpleName();

    private static int CustomGreenColor = 0xFF49C811; //Info
    private static int CustomBlueColor = 0xDC0092FF; //Message
    private static int CustomRedColor = 0xDCFF0011; //Warning

    private volatile static Boast globalBoast = null;

    private Toast internalToast;
    //endregion

    private Boast(Toast toast) {
        if (toast == null) {
            throw new NullPointerException("Boast.Boast(Toast) requires a non-null parameter");
        }

        internalToast = toast;
    }

    public void cancel() {
        internalToast.cancel();
    }

    private void show() {
        if (globalBoast != null) {
            globalBoast.cancel();
        }

        globalBoast = this;

        internalToast.show();
    }

    @SuppressLint("ShowToast")
    public static void makeText(Context context, CharSequence text) {
        makeText(context, text, Level.Info);
    }

    @SuppressLint("ShowToast")
    public static void makeText(Context context, CharSequence text, Level level) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.custom_toast, null);

        TextView textView = (TextView) layout.findViewById(R.id.custom_toast_text);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setBackgroundColor(level.getColor());

        Toast toast = new Toast(context);
        toast.setView(layout);

        Boast boast = new Boast(toast);
        boast.show();
    }

    public enum Level {
        Info(CustomGreenColor),
        Message(CustomBlueColor),
        Warning(CustomRedColor);

        private int color;

        Level(int color) {
            this.color = color;
        }

        public int getColor() {
            return color;
        }
    }

/*    @SuppressLint("ShowToast")
    public static Boast makeText(Context context, CharSequence text) {
        return new Boast(Toast.makeText(context, text, Toast.LENGTH_SHORT));
    }

    @SuppressLint("ShowToast")
    public static Boast makeText(Context context, CharSequence text, int duration) {
        return new Boast(Toast.makeText(context, text, duration));
    }

    @SuppressLint("ShowToast")
    public static Boast makeText(Context context, int resId, int duration) throws Resources.NotFoundException {
        return new Boast(Toast.makeText(context, resId, duration));
    }
    
    @SuppressLint("ShowToast")
    public static Boast makeText(Context context, int resId) throws Resources.NotFoundException {
        return new Boast(Toast.makeText(context, resId, Toast.LENGTH_SHORT));
    }*/
}