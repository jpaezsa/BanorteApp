package com.example.gerardogtn.banorteapp.ui.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.gerardogtn.banorteapp.R;
import com.example.gerardogtn.banorteapp.data.model.User;
import com.example.gerardogtn.banorteapp.data.model.UserProductResponse;
import com.example.gerardogtn.banorteapp.service.RetrofitService.RetoBanorteApi.RetoBanorteApiClient;
import com.example.gerardogtn.banorteapp.util.CurrencyFormat;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        Callback<List<UserProductResponse>>{

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbar;

    @Bind(R.id.activity_main_ab_profile_name)
    TextView mUserName;

    @Bind(R.id.account1)
    TextView mFirstAccount;

    @Bind(R.id.activity_main_ab_deposit)
    TextView mFirstBalance;

    @Bind(R.id.account2)
    TextView mSecondAccount;

    @Bind(R.id.activity_main_ab_credit)
    TextView mSecondBalance;

    @Bind(R.id.account3)
    TextView mThirdAccount;

    @Bind(R.id.activity_main_ab_investment)
    TextView mThirdBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpToolbar();
        initializeElements();
        setUpUserName();
        RetoBanorteApiClient.getInstance().getUserProducts(1, this);
    }

    private void setUpUserName() {
        RetoBanorteApiClient.getInstance().getUser(1,
                new Callback<List<User>>() {

                    @Override
                    public void success(List<User> users, Response response) {
                        User user = users.get(0);
                        mUserName.setText(user.getFirstName() + " " + user.getPaternalLastName());
                        printWelcomeMessage(user.getFirstName());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(LOG_TAG, "No pudimos obtener el nombre del usuario");
                        error.printStackTrace();
                    }

                });
    }

    private void printWelcomeMessage(String name) {
        Snackbar.make(mUserName, "Bienvenido " + name, Snackbar.LENGTH_LONG).show();
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


    @Override
    public void success(List<UserProductResponse> userProductResponses, Response response) {
        mFirstAccount.setText(userProductResponses.get(0).getDescription());
        mFirstBalance.setText(CurrencyFormat.getCurrencyFormat(userProductResponses.get(0).getBalance()));
        mSecondAccount.setText(userProductResponses.get(1).getDescription());
        mSecondBalance.setText(CurrencyFormat.getCurrencyFormat(userProductResponses.get(1).getBalance()));
        mThirdAccount.setText(userProductResponses.get(2).getDescription());
        mThirdBalance.setText(CurrencyFormat.getCurrencyFormat(userProductResponses.get(2).getBalance()));
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(LOG_TAG, "error getting accounts");
        error.printStackTrace();
    }
}
