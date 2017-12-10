package com.example.igroup.schoolinfo.Extras;

/**
 * Created by iGroup on 11/16/2017.
 */

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by iGroup on 11/16/2017.
 */

public class MyApplication extends Application {

    private Typeface journalFont;
    private Typeface avenirNextFont;
    Context context;
    private static MyApplication instance;


    public MyApplication(Context cntxt)
    {
        context = cntxt;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    // -- Fonts -- //
    public void setTypeface(TextView textView) {
        if(textView != null) {
            if(textView.getTypeface() != null) {
                textView.setTypeface(getAvenirNextFont());
            } else {
                textView.setTypeface(getJournalFont());
            }
        }
    }

    private Typeface getJournalFont() {
        if(journalFont == null) {
            journalFont = Typeface.createFromAsset(context.getAssets(),"fonts/journal.TTF");
        }
        return this.journalFont;
    }

    private Typeface getAvenirNextFont() {
        if(avenirNextFont == null) {
            avenirNextFont = Typeface.createFromAsset(context.getAssets(),"fonts/avenirnextregular.otf");
        }
        return this.avenirNextFont;
    }
}
