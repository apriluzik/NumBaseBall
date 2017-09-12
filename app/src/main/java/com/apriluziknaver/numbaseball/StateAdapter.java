package com.apriluziknaver.numbaseball;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mapri on 2017-09-09.
 */

public class StateAdapter extends RecyclerView.Adapter {

    ArrayList<StateItem> item;
    Context context;

    public StateAdapter(ArrayList<StateItem> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.state_item,parent,false);
        RecyclerView.ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder mholder = (ViewHolder)holder;

        mholder.number.setText(item.get(position).number);
        mholder.strike.setText(item.get(position).strike);
        mholder.ball.setText(item.get(position).ball);

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView ball;
        TextView strike;
        TextView number;

        public ViewHolder(View itemView) {
            super(itemView);

            ball = itemView.findViewById(R.id.ball);
            strike = itemView.findViewById(R.id.strike);
            number = itemView.findViewById(R.id.number);

        }
    }
}
