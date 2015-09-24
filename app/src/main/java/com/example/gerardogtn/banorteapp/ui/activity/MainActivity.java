package com.example.gerardogtn.banorteapp.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.gerardogtn.banorteapp.R;
import com.example.gerardogtn.banorteapp.ui.util.OnSwipeEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        initializeDefaultItems();
        initializeEditText();
    }


    private void initializeEditText()
    {
        ((EditText)this.findViewById(R.id.activity_main_search_edit_text)).addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) initializeDefaultItems();
                        else setSearchItems();
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                }
        );
    }


    private void initializeDefaultItems()
    {
        ((ViewGroup) this.findViewById(R.id.activity_main_content_container)).removeAllViews();
        inflateCard1("Transacciones", "Subititulo", "#1976D2", R.mipmap.img_card_transfer);
        inflateCard1("Servicios", "Subititulo","#009688",0);
        inflateCard1("Seguros", "Subititulo","#F5BE28",0);
        inflateCard1("Tarjetas", "Subititulo","#ED7D32",R.mipmap.img_checkbook);
        inflateCard1("Otros ", "Subititulo", "#7C4DFA", 0);
    }


    private void setSearchItems()
    {
        ((ViewGroup) this.findViewById(R.id.activity_main_content_container)).removeAllViews();
    }

    private View inflateCard(int layout)
    {
        ViewGroup parent = (ViewGroup) this.findViewById(R.id.activity_main_content_container);
        View view = LayoutInflater.from(this).inflate(layout, parent, false);
        parent.addView(view);
        return view;
    }

    private void inflateCard1(String title, String subtitle, String color, int image)
    {
        final View view = inflateCard(R.layout.activity_main_card_design_1);
        view.setTag(title);

        //Set attributes
        ((TextView)view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView)view.findViewById(R.id.activity_main_card_subtitle)).setText(subtitle);
        ((ImageView)view.findViewById(R.id.activity_main_card_image)).setImageResource(image);
        view.setBackgroundColor(Color.parseColor(color));

        OnSwipeEvent swipe = new OnSwipeEvent(
                (ScrollView)this.findViewById(R.id.activity_main_scroll_view),view,
                4,30,350,2)
        {
            @Override
            public void onSwipeLeft()
            {
                AnimatorSet as = new AnimatorSet();
                as.play(ObjectAnimator.ofFloat(view, "rotationY", -180).setDuration(650));
                as.start();
            }

            @Override
            public void onSwipeRight(){
                AnimatorSet as = new AnimatorSet();
                as.play(ObjectAnimator.ofFloat(view, "rotationY", -180).setDuration(650));

                as.addListener(new Animator.AnimatorListener() {
                    @Override  public void onAnimationStart (Animator animation) {}
                    @Override  public void onAnimationCancel(Animator animation) {}
                    @Override  public void onAnimationRepeat(Animator animation) {}

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), android.R.anim.fade_in));
                        view.setBackgroundColor(Color.parseColor("#a0a0a0"));
                        AnimatorSet as = new AnimatorSet();
                        as.play(ObjectAnimator.ofFloat(view, "rotationY", 180).setDuration(0));
                    }
                });
                as.start();
            }

        };
        swipe.setDragged(false);
        view.setOnTouchListener(swipe);
    }


    private void inflateCard2(String title, String subtitle, int image)
    {
        View view = inflateCard(R.layout.activity_main_card_design_2);
        //Set attributes
        ((TextView)view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView)view.findViewById(R.id.activity_main_card_subtitle)).setText(subtitle);
    }


    private void inflateCard3(String title, String content)
    {
        View view = inflateCard(R.layout.activity_main_card_design_3);
        //Set attributes
        ((TextView)view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView)view.findViewById(R.id.activity_main_card_subtitle)).setText(content);
    }

}
