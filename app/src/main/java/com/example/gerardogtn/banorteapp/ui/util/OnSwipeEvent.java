package com.example.gerardogtn.banorteapp.ui.util;


import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.maildots.maildots.R;


public class OnSwipeEvent implements View.OnTouchListener
{

    private RecyclerView mRecyclerView;
    private View mLayout;
    private View mBackground;
    private Drawable mShapeLeft;
    private Drawable mShapeRight;
    private Drawable mDefaultDrawable;
    private float mMinSwipe;
    private float mActionSwipe;
    private float mMaxSwipe;
    private float mMinSwipeClick;

    private FrameLayout.LayoutParams lp;

    public OnSwipeEvent(RecyclerView recyclerView, View layout, View background,
                        Drawable shapeLeft, Drawable shapeRight, Drawable defaultDrawable,
                        float minSwipe, float actionSwipe, float maxSwipe, float minSwipeClick)
    {
        mRecyclerView    = recyclerView;
        mLayout          = layout;
        mBackground      = background;
        mShapeLeft       = shapeLeft;
        mShapeRight      = shapeRight;
        mDefaultDrawable = defaultDrawable;
        mMinSwipe        = minSwipe;
        mActionSwipe     = actionSwipe;
        mMaxSwipe        = maxSwipe;
        mMinSwipeClick   = minSwipeClick;
        lp               = (FrameLayout.LayoutParams)mLayout.getLayoutParams();
    }

    float posIni = 0;
    float dist = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        int action = event.getActionMasked();

        //Pressed
        if (action == MotionEvent.ACTION_DOWN)
        {
            posIni = event.getX();
            mLayout.setBackgroundResource(R.color.application_color_pressed);
            return true;
        }

        //OnSwipe
        if (action == MotionEvent.ACTION_MOVE)
        {
            dist = (event.getX()-posIni)*0.8f;

            if (Math.abs(dist) > mMinSwipe) {

                mRecyclerView.requestDisallowInterceptTouchEvent(true);
                if (dist < 0 && Math.abs(dist) < mMaxSwipe*1.12f)
                {
                    lp.setMargins((int)(dist), 0, (int)(-dist), 0);
                    mLayout.setLayoutParams(lp);
                    mBackground.setBackgroundDrawable(mShapeRight);
                }

                if (dist > 0 && Math.abs(dist) < mMaxSwipe )
                {
                    lp.setMargins((int) (dist),0,(int) (-dist),0);
                    mLayout.setLayoutParams(lp);
                    mBackground.setBackgroundDrawable(mShapeLeft);
                }
            }


            if (Math.abs(dist) < mMinSwipeClick)
                mLayout.setBackgroundResource(R.color.application_color_pressed);
            else
                mLayout.setBackgroundDrawable(mDefaultDrawable);

            return true;
        }

        //Release
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL)
        {
            mBackground.setPadding(0, 0,0, 0);
            mBackground.setBackgroundDrawable(null);
            lp.setMargins(0,0,0,0);
            mLayout.setLayoutParams(lp);
            mRecyclerView.requestDisallowInterceptTouchEvent(false);
            mRecyclerView.getParent().requestDisallowInterceptTouchEvent(false);
            mLayout.setBackgroundDrawable(mDefaultDrawable);

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

            if (Math.abs(dist) > mMinSwipe) { //Return to the original position
                TranslateAnimation translateAnimation = new TranslateAnimation(dist, 0, 0, 0);
                translateAnimation.setDuration(2 * (int) Math.abs(dist));
                mLayout.startAnimation(translateAnimation);
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
