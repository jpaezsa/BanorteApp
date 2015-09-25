package com.example.gerardogtn.banorteapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.gerardogtn.banorteapp.R;
import com.example.gerardogtn.banorteapp.data.model.Movement;
import com.example.gerardogtn.banorteapp.data.model.UserProductResponse;
import com.example.gerardogtn.banorteapp.service.RetrofitService.RetoBanorteApi.RetoBanorteApiClient;
import com.example.gerardogtn.banorteapp.ui.adapter.TransferAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gerardogtn on 9/24/15.
 */
public class TransfersActivity extends AppCompatActivity
        implements Callback<List<UserProductResponse>>, AdapterView.OnItemSelectedListener {

    public static final String LOG_TAG = TransfersActivity.class.getSimpleName();

    @Bind(R.id.rvw_transfers)
    RecyclerView mFragments;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.spn_accounts)
    Spinner mAccounts;

    private List<Movement> mTransfers;
    private List<UserProductResponse> mUserProducts;
    private List<String> mNames;
    private ArrayAdapter<String> mAdapter;

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
        RetoBanorteApiClient.getInstance().getUserProducts(1, this);
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
        mNames = new ArrayList<>();
        mNames.add("Todas las cuentas");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item,
                mNames);

        mAdapter = adapter;
        mAccounts.setAdapter(adapter);
        mAccounts.setOnItemSelectedListener(this);
    }


    @Override
    public void success(List<UserProductResponse> userProductResponses, Response response) {
        mUserProducts = userProductResponses;
        for (int i = 0; i < mUserProducts.size(); i++) {
            mNames.add(mUserProducts.get(i).getDescription());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(LOG_TAG, "Failure getting the user accounts");
        error.printStackTrace();
    }

    private void addAllRecyclerView() {
        if (mUserProducts.size() > 0) {
            updateRecyclerView(0, true);
            for (int i = 1; i < mUserProducts.size(); i++) {
                updateRecyclerView(i, false);
            }
        }
    }

    private void updateRecyclerView(int position, final boolean clear) {

        if (clear) {
            mTransfers = new ArrayList<>();
        }

        UserProductResponse current = mUserProducts.get(position);

        RetoBanorteApiClient.getInstance().getMovements(current.getClientId(),
                current.getAccountId(),
                new Callback<List<Movement>>() {
                    @Override
                    public void success(List<Movement> movements, Response response) {
                        ((TransferAdapter) mFragments.getAdapter()).addItemsToList(movements, clear);
                        mFragments.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(LOG_TAG, "Error getting movements");
                        error.printStackTrace();
                    }
                });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            addAllRecyclerView();
        } else {
            updateRecyclerView(position - 1, true);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
