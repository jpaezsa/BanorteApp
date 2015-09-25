package com.example.gerardogtn.banorteapp.ui.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.gerardogtn.banorteapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    @Bind(R.id.toolbar)
//    Toolbar mToolbar;


    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpToolbar();
        initializeElements();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_movements) {
            Intent transfersActivity = new Intent(this, TransfersActivity.class);
            startActivity(transfersActivity);
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(final View v) {
        AnimatorSet as = new AnimatorSet();
        as.play(ObjectAnimator.ofFloat(v, "rotationY", -180).setDuration(650));

        as.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.startAnimation(AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in));
                v.setBackgroundColor(Color.parseColor("#a0a0a0"));
                AnimatorSet as = new AnimatorSet();
                as.play(ObjectAnimator.ofFloat(v, "rotationY", 180).setDuration(0));
            }

        });

        as.start();
    }

    private void setUpToolbar() {
        mCollapsingToolbar.setTitle("Banorte");
        mCollapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    private View inflateCard(int layout) {
        ViewGroup parent = (ViewGroup) this.findViewById(R.id.activity_main_content_container);
        View view = LayoutInflater.from(this).inflate(layout, parent, false);
        parent.addView(view);
        return view;
    }

    private void inflateCard1(String title, String subtitle, String color, int image) {
        View view = inflateCard(R.layout.activity_main_card_design_1);
        ((TextView) view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView) view.findViewById(R.id.activity_main_card_subtitle)).setText(subtitle);
        view.setBackgroundColor(Color.parseColor(color));
        view.setOnClickListener(this);
    }


    private void inflateCard2(String title, String subtitle, int image) {
        View view = inflateCard(R.layout.activity_main_card_design_2);
        ((TextView) view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView) view.findViewById(R.id.activity_main_card_subtitle)).setText(subtitle);
    }


    private void inflateCard3(String title, String content) {
        View view = inflateCard(R.layout.activity_main_card_design_3);
        ((TextView) view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView) view.findViewById(R.id.activity_main_card_subtitle)).setText(content);
    }

    private void initializeElements() {
        for (int i = 0; i < 2; i++) inflateCard3("Titulo " + i, "Subititulo");
        for (int i = 0; i < 4; i++) inflateCard2("Titulo " + i, "Subititulo", 0);

        inflateCard1("Titulo ", "Subititulo", "#1976D2", 0);
        inflateCard1("Titulo ", "Subititulo", "#009688", 0);
        inflateCard1("Titulo ", "Subititulo", "#F5BE28", 0);
        inflateCard1("Titulo ", "Subititulo", "#ED7D32", 0);
        inflateCard1("Titulo ", "Subititulo", "#7C4DFA", 0);
    }


}
