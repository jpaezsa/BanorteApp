package com.example.gerardogtn.banorteapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gerardogtn.banorteapp.R;
import com.example.gerardogtn.banorteapp.data.model.Movement;
import com.example.gerardogtn.banorteapp.data.model.UserProductResponse;
import com.example.gerardogtn.banorteapp.ui.adapter.TransferAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gerardogtn on 9/24/15.
 */
public class TransfersActivity extends AppCompatActivity {

    @Bind(R.id.rvw_transfers)
    RecyclerView mFragments;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.spn_accounts)
    Spinner mAccounts;

    List<Movement> mTransfers;

    List<UserProductResponse> mUserProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfers);
        ButterKnife.bind(this);
        setUpToolbar();
        mTransfers = new ArrayList<>();
        mTransfers.add(new Movement(28, new Date(2014, 03, 24), new BigDecimal(100), "Prueba"));
        mUserProducts = new ArrayList<>();
        setUpRecycleView();
        setUpSpinner();
    }



    // REQUIRES: None.
    // MODIFIES: this.
    // EFFECTS:  Sets support action toolbar with mToolbar.
    private void setUpToolbar() {
        this.setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(R.menu.menu_main);
        drawBackArrow();
    }



    // REQUIRES: None.
    // MODIFIES: this.
    // EFFECTS: Creates a vertical recycleview filled with mVenues, each view using a HomeListAdapter.
    private void setUpRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mFragments.setLayoutManager(linearLayoutManager);
        TransferAdapter transferAdapter = new TransferAdapter(this, mTransfers);
        mFragments.setAdapter(transferAdapter);
    }

    // REQUIRES: None.
    // MODIFIES: this.
    // EFFECTS:  Draws back arrow in toolbar.
    private void drawBackArrow() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // REQUIRES: None.
    // MODIFIES: this.
    // EFFECTS: Populates the spinner with the user accounts.
    private void setUpSpinner() {
        List<String> accountTitles = new ArrayList<>();

        for (int i = 0; i < mUserProducts.size(); i++){
            accountTitles.add(mUserProducts.get(i).getDescription());
        }

        accountTitles.add("Prueba :D");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                accountTitles);

        mAccounts.setAdapter(adapter);
    }


}
