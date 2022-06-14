package com.masoud.autotextswitcher;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextSwitcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;


public class AutoTextSwitcher extends TextSwitcher
{
    private static final int DEFAULT_ANIMATION_TIME = 1000; // Milliseconds

    int index = 0;
    private ArrayList<CharSequence> textArray;
    private long changeAnimationTime;

    private Handler handler;
    private Timer animationTimer;
    private TimerTask timerTask;


    public AutoTextSwitcher(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs)
    {
        TypedArray properties = context.obtainStyledAttributes(attrs, R.styleable.AutoTextSwitcher);

        boolean autoStart;
        try
        {
            CharSequence[] array = properties.getTextArray(R.styleable.AutoTextSwitcher_textArray);
            if (array != null)
                textArray = new ArrayList<>(Arrays.asList(array));

            changeAnimationTime = properties.getInteger(R.styleable.AutoTextSwitcher_changeAnimationTime, DEFAULT_ANIMATION_TIME);

            autoStart = properties.getBoolean(R.styleable.AutoTextSwitcher_autoStart, false);
        }
        finally
        {
            properties.recycle();
        }

        if (autoStart && !isInEditMode())
            startTextAnimation();
    }

    private void setupTimer()
    {
        animationTimer = new Timer();
    }

    public void startTextAnimation()
    {
        if (isTextAnimationIsRunning())
            return;

        if (textArray != null)
        {
            if (textArray.size() > 1)
            {
                setupTimer();
                animationTimer.schedule(getAnimationTask(), 350, changeAnimationTime);
            }
            else
                setCurrentText(textArray.get(0));
        }
    }


    public void stopTextAnimation()
    {
        if (textArray != null && animationTimer != null)
        {
            animationTimer.cancel();
            animationTimer.purge();
            animationTimer = null;
            timerTask = null;
        }
    }


    public boolean isTextAnimationIsRunning()
    {
        return animationTimer != null;
    }


    private TimerTask getAnimationTask()
    {
        if (timerTask == null)
        {
            handler = new Handler();

            timerTask = new TimerTask()
            {
                @Override
                public void run()
                {
                    handler.postDelayed(() ->
                    {
                        setText(textArray.get(index));
                        index++;
                        if (index == textArray.size())
                            index = 0;
                    }, 0);
                }
            };
        }

        return timerTask;
    }


    public void setTextArray(ArrayList<CharSequence> textArray)
    {
        if (isTextAnimationIsRunning())
            stopTextAnimation();

        this.textArray = textArray;
        startTextAnimation();
    }


    public void appendToTextArray(CharSequence newText)
    {
        if (isTextAnimationIsRunning())
            stopTextAnimation();

        textArray.add(newText);

        startTextAnimation();
    }
}
