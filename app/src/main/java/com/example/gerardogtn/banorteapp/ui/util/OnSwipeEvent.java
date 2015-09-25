package com.example.gerardogtn.banorteapp.ui.util;


import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class OnSwipeEvent implements View.OnTouchListener
{

    private ScrollView mScrollView;
    private View    mLayout;
    private float   mMinSwipe;
    private float   mActionSwipe;
    private float   mMaxSwipe;
    private float   mMinSwipeClick;
    private boolean mIsDragged;

    private LinearLayout.LayoutParams lp;

    public OnSwipeEvent(ScrollView scrollView, View layout,
                        float minSwipe, float actionSwipe, float maxSwipe, float minSwipeClick)
    {
        mScrollView      = scrollView;
        mLayout          = layout;
        mMinSwipe        = minSwipe;
        mActionSwipe     = actionSwipe;
        mMaxSwipe        = maxSwipe;
        mMinSwipeClick   = minSwipeClick;
        lp               = (LinearLayout.LayoutParams)mLayout.getLayoutParams();
        mIsDragged       = true;
    }

    float posIni = 0;
    float dist = 0;


    public void setDragged(boolean isDragged){
        mIsDragged = isDragged;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        int action = event.getActionMasked();

        //Pressed
        if (action == MotionEvent.ACTION_DOWN)
        {
            posIni = event.getX();
            return true;
        }

        //OnSwipe
        if (action == MotionEvent.ACTION_MOVE)
        {
            dist = (event.getX()-posIni)*1f;

            if (Math.abs(dist) > mMinSwipe) {

                mScrollView.requestDisallowInterceptTouchEvent(true);

                if (dist > 0 && Math.abs(dist) < mMaxSwipe && mIsDragged)
                {
                    lp.setMargins((int) (dist),7,(int) (-dist),7);
                    mLayout.setLayoutParams(lp);
                }


                if (dist < 0 && Math.abs(dist) < mMaxSwipe && mIsDragged)
                {
                    lp.setMargins((int) (dist),7,(int) (-dist),7);
                    mLayout.setLayoutParams(lp);
                }
            }

            return true;
        }

        //Release
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL)
        {
            lp.setMargins(0, 7, 0, 7);
            mLayout.setLayoutParams(lp);
            mScrollView.requestDisallowInterceptTouchEvent(false);

            if (Math.abs(dist) < mMinSwipeClick &&  action != MotionEvent.ACTION_CANCEL)
                onClick();

            if (Math.abs(dist) > mActionSwipe)
            { //When the swipe execute an action
                if (dist < 0) {
                    onSwipeRight();
                }
                if (dist > 0) {
                    onSwipeLeft();
                }
            }

            if (Math.abs(dist) > mMinSwipe && dist > 0 && mIsDragged) { //Return to the original position
               // TranslateAnimation translateAnimation = new TranslateAnimation(dist, 0, 0, 0);
               // translateAnimation.setDuration(2 * (int) Math.abs(dist));
               // mLayout.startAnimation(translateAnimation);
            }



            return true;
        }
        return true;

    }

    public View getView(){return mLayout;}
    public void onClick(){}
    public void onSwipeLeft(){}
    public void onSwipeRight(){}
}
