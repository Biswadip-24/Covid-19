package com.app.learning.covid_19;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<Example_Item> mExample_items;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView mState;
        public TextView mConfirmed;
        public TextView mRecovered;
        public TextView mDeceased;
        public TextView mdConfirmed;
        public TextView mdRecovered;
        public TextView mdDeceased;

        public ExampleViewHolder(View itemView)
        {
            super(itemView);
            mState=itemView.findViewById(R.id.state);
            mConfirmed=itemView.findViewById(R.id.confirm);
            mRecovered=itemView.findViewById(R.id.recover);
            mDeceased=itemView.findViewById(R.id.decease);
            mdConfirmed=itemView.findViewById(R.id.dconfirm);
            mdRecovered=itemView.findViewById(R.id.drecover);
            mdDeceased=itemView.findViewById(R.id.ddecease);
        }

    }
    public ExampleAdapter(ArrayList<Example_Item> RowList){
        mExample_items=RowList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        ExampleViewHolder evh=new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Example_Item currenItem=mExample_items.get(position);
        holder.mState.setText(currenItem.getState());
        holder.mConfirmed.setText(currenItem.getConf());
        holder.mRecovered.setText(currenItem.getRec());
        holder.mDeceased.setText(currenItem.getDec());
        holder.mdConfirmed.setText(currenItem.getDconf());
        holder.mdRecovered.setText(currenItem.getDrec());
        holder.mdDeceased.setText(currenItem.getDdec());
    }
    @Override
    public int getItemCount() {
        return mExample_items.size();
    }
}
