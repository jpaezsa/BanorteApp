package com.example.gerardogtn.banorteapp.ui.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.DialogInterface;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gerardogtn.banorteapp.R;
import com.example.gerardogtn.banorteapp.data.local.ServiceList;
import com.example.gerardogtn.banorteapp.service.PreferencesHelper.PreferencesUtil;
import com.example.gerardogtn.banorteapp.ui.util.ColorUtil;
import com.example.gerardogtn.banorteapp.ui.util.OnSwipeEvent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
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
        initializeDefaultItems();
        initializeSearch();
        initializeEditText();
        initializeFloatingButton();
        setUpToolbar();
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
                        //printWelcomeMessage(user.getFirstName());
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

    private void initializeEditText() {
        ((EditText)this.findViewById(R.id.activity_main_search_edit_text)).addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() == 0) initializeDefaultItems();
                        else {
                            ((ViewGroup) MainActivity.this.findViewById(R.id.activity_main_content_container)).removeAllViews();
                            setSearchItems(s.toString());
                        }
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

    private void initializeSearch() {

        this.findViewById(R.id.activity_main_search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWatson();
            }
        });

        this.findViewById(R.id.activity_main_search_button).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startVoiceRecognitionActivity();
                return false;
            }

        });
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

    private void initializeFloatingButton()
    {
        this.findViewById(R.id.activity_main_fb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShakeMapActivity.class));
            }
        });
    }

    private void initializeDefaultItems()
    {
        ((ViewGroup) this.findViewById(R.id.activity_main_content_container)).removeAllViews();

        for (int i = 0; i < 10; i++)
        {
            if (PreferencesUtil.getIntCount(this,i) > 0)
                inflateCard2(ServiceList.TITULOS[i],ServiceList.DESCRIPCIONES[i],ServiceList.IMAGENES[i],i);

        }

        inflateCard1("Transacciones", "Revisa tus movimientos como depositos, retiros o transpasos", "#1976D2", R.mipmap.img_card_transfer, "NUEVA", "DESPLEGAR", null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.startActivity(new Intent(MainActivity.this,TransfersActivity.class));
            }
        });
        inflateCard1("Servicios", "Paga y consulta los servicios que ofrecemos como luz, teléfono y agua", "#009688", R.mipmap.img_services, "VER", "PAGAR",null,null);
        inflateCard1("Tarjetas", "Consulta el status de tus tarjetas y revisa tu status de pago", "#F5BE28", R.mipmap.img_checkbook, "VER", "PAGAR", null, null);
        inflateCard1("Seguros", "Ve tus seguros, revisa las caracteristicas de los seguros y ve más", "#ED7D32", R.mipmap.img_insurance, "CATALOGO", "STATUS", null, null);
        inflateCard1("Otros ", "Revisa información adicional como los detalles de tus cuentas y nuevas funciones", "#7C4DFA", R.mipmap.img_other, "ACERCA DE", "MAS", null, null);
    }

    private void searchWatson()
    {
        final String search =   ((EditText)this.findViewById(R.id.activity_main_search_edit_text)).getText().toString();

        new AsyncWatson(search)
        {
            @Override
            public void onFinish(String content)
            {
                String data = content;
                Document doc = Jsoup.parse(data);
                String answer = doc.text();
                if (answer.length() >= 100)
                    answer.substring(0, 99);
                inflateCard3(search, answer);
                //End
            }
        }.execute();


        ((ViewGroup) MainActivity.this.findViewById(R.id.activity_main_content_container)).removeAllViews();
        setSearchItems(search);

    }

    private void setSearchItems(String search)
    {
        for (int i = 0; i < ServiceList.NUM_IDS; i++){
            if (
                    ServiceList.TITULOS[i].toLowerCase().contains(search.toLowerCase()) ||
                            ServiceList.CLAVE[i].toLowerCase().contains(search.toLowerCase()) ||
                            ServiceList.DESCRIPCIONES[i].toLowerCase().contains(search.toLowerCase()))
            {
                inflateCard2(ServiceList.TITULOS[i],ServiceList.DESCRIPCIONES[i],ServiceList.IMAGENES[i],i);

            }

        }
    }

    private void setUpToolbar() {
        mCollapsingToolbar.setTitle("Banorte");
        mCollapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    private View inflateCard(int layout, int position){
        ViewGroup parent = (ViewGroup) this.findViewById(R.id.activity_main_content_container);
        View view = LayoutInflater.from(this).inflate(layout, parent, false);
        if (position == -1) parent.addView(view);
        else                parent.addView(view,position);
        return view;
    }

    private View inflateCard(int layout)
    {
        return inflateCard(layout,-1);
    }

    private void inflateCard1(final String title, final String subtitle, final String color,final int image,final String button1,final String button2,final View.OnClickListener click1,final View.OnClickListener click2 )
    {
        final View view = inflateCard(R.layout.activity_main_card_design_1);
        view.setTag(title);

        //Set attributes
        setAttributes(view,title,subtitle,color,image,button1,button2,click1,click2);
        view.setBackgroundColor(Color.parseColor(color));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playFlipCard(view, 180, title, subtitle, color, image, button1, button2, click1, click2);
            }
        });

        view.findViewById(R.id.activity_main_card_pin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) MainActivity.this.findViewById(R.id.activity_main_content_container))
                        .removeView((View) v.getParent().getParent());
            }
        });
    }

    public void setAttributes(View view, String title, String subtitle, final String color, int image, String button1, String button2, View.OnClickListener click1, View.OnClickListener click2 )
    {
        ((TextView)view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView)view.findViewById(R.id.activity_main_card_subtitle)).setText(subtitle);
        ((TextView)view.findViewById(R.id.activity_main_card_button_1)).setText(button1);
        ((TextView)view.findViewById(R.id.activity_main_card_button_2)).setText(button2);
        view.findViewById(R.id.activity_main_card_button_2).setOnClickListener(click1);
        view.findViewById(R.id.activity_main_card_button_2).setOnClickListener(click2);
        ((ImageView)view.findViewById(R.id.activity_main_card_image)).setImageResource(image);
    }

    private void playFlipCard(final View view, int rotation, final String title, final String subtitle, final String color, final int image, final String button1, final String button2, final View.OnClickListener click1, final View.OnClickListener click2)
    {
        final AnimatorSet as = new AnimatorSet();
        final ObjectAnimator oa = ObjectAnimator.ofFloat(view, "rotationY", rotation).setDuration(500);
        as.play(oa);

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
                view.startAnimation(AnimationUtils.loadAnimation(view.getContext(), android.R.anim.fade_in));
                view.invalidate();
                oa.reverse();
                ViewGroup parent = (ViewGroup) view;

                if (view.findViewById(R.id.activity_main_card_image) == null) {
                    parent.removeAllViews();
                    View newView = LayoutInflater.from(view.getContext()).inflate(
                            R.layout.activity_main_card_design_1a, parent, false);
                    view.setBackgroundColor(Color.parseColor("#" + ColorUtil.changeColorBrightness(color, 100)));
                    setAttributes(newView, title, subtitle, color, image, button1, button2, click1, click2);
                    parent.addView(newView);
                } else {
                    parent.removeAllViews();
                    View newView = LayoutInflater.from(view.getContext()).inflate(
                            R.layout.activity_main_card_design_1b, parent, false);
                    view.setBackgroundColor(Color.parseColor("#" + ColorUtil.changeColorBrightness(color, 95)));
                    parent.addView(newView);
                }
            }
        });
        as.start();
    }

    private void inflateCard2(String title, String subtitle, int image, int id)
    {
        View view = inflateCard(R.layout.activity_main_card_design_2);
        //Set attributes
        view.setTag(id);
        ((TextView)view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView)view.findViewById(R.id.activity_main_card_subtitle)).setText(subtitle);
        ((ImageView)view.findViewById(R.id.activity_main_card_image)).setImageResource(image);
        view.findViewById(R.id.activity_main_card_pin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup) MainActivity.this.findViewById(R.id.activity_main_content_container))
                        .removeView((View) v.getParent().getParent());
            }
        });
    }

    private void inflateCard3(String title, String content) {
        View view = inflateCard(R.layout.activity_main_card_design_3,0);
        //Set attributes
        ((TextView)view.findViewById(R.id.activity_main_card_title)).setText(title);
        ((TextView)view.findViewById(R.id.activity_main_card_subtitle)).setText(content);
    }


    public void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        startActivityForResult(intent, 123);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 123 && resultCode == RESULT_OK) {
            ArrayList<String> matches_text = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches_text.size() > 0){
                ((EditText) this.findViewById(R.id.activity_main_search_edit_text))
                        .setText(matches_text.get(0));
                searchWatson();
            }
        }
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
