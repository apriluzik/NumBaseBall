package com.apriluziknaver.numbaseball;

import android.content.Context;
import android.graphics.Typeface;
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


        if(item.get(position).strike.equals("0S")){
            mholder.strike.setText("");
        }else{
            mholder.strike.setText(item.get(position).strike);
        }

        if(item.get(position).ball.equals("0B")){
            mholder.ball.setText("");
        }else{
            mholder.ball.setText(item.get(position).ball);
        }


        if(item.get(position).strike.equals("0S")&&item.get(position).ball.equals("0B")){
            mholder.strike.setText("OUT");
        }




    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Typeface typeface;
        TextView ball;
        TextView strike;
        TextView number;

        public ViewHolder(View itemView) {
            super(itemView);
            typeface = Typeface.createFromAsset(context.getAssets(),"fonts/Semi-Coder-Regular.otf");
            ball = itemView.findViewById(R.id.ball);
            ball.setTypeface(typeface);
            strike = itemView.findViewById(R.id.strike);
            strike.setTypeface(typeface);
            number = itemView.findViewById(R.id.number);
            number.setTypeface(typeface);



        }
    }
}
