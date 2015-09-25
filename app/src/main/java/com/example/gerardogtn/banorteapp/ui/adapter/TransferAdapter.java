package com.example.gerardogtn.banorteapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gerardogtn.banorteapp.R;
import com.example.gerardogtn.banorteapp.data.model.Movement;
import com.example.gerardogtn.banorteapp.util.CurrencyFormat;
import com.example.gerardogtn.banorteapp.util.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gerardogtn on 9/24/15.
 */
public class TransferAdapter extends RecyclerView.Adapter<TransferAdapter.TransferViewHolder>{

    private Context mContext;
    private List<Movement> mTransfers;
    private LayoutInflater mInflater;

    public TransferAdapter(Context context, List<Movement> transfers) {
        this.mContext = context;
        this.mTransfers = transfers;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public TransferAdapter.TransferViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View item = mInflater.inflate(R.layout.item_transfer, viewGroup, false);
        return new TransferViewHolder(item);
    }

    @Override
    public void onBindViewHolder(TransferAdapter.TransferViewHolder transferViewHolder, int i) {
        transferViewHolder.setData(mTransfers.get(i));
    }

    public void addItemsToList(List<Movement> movements, boolean clear){
        if (clear){
            mTransfers = new ArrayList<>();
        }
        mTransfers.addAll(movements);
    }

    @Override
    public int getItemCount() {
        return mTransfers.size();
    }

    public class TransferViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txt_description)
        TextView mDescription;

        @Bind(R.id.txt_date)
        TextView mDate;

        @Bind(R.id.txt_price)
        TextView mPrice;

        public TransferViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        // REQUIRES: None.
        // MODIFIES: this.
        // EFFECTS:  Represents and visualizes venue data with views.
        public void setData(Movement movement) {
            mDescription.setText(movement.getDescription());
            mDate.setText(DateFormat.getDateFormat(movement.getDate()));
            mPrice.setText(CurrencyFormat.getCurrencyFormat(movement.getAmount()));
        }
    }
}
