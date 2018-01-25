package com.example.administrator.recycapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/24.
 */

public class MyRecyclerViewAdapter extends Adapter<MyRecyclerViewAdapter.MyHolder> {

    private final Context context;
    private ArrayList<String> datas;

    public MyRecyclerViewAdapter(Context conText, ArrayList<String> daTas) {
        this.context = conText;
        this.datas = daTas;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_recyclerview, null);
        // View itemView=LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        return new MyHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String data = datas.get(position);
        holder.textview.setText(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(int position, String s) {
        datas.add(position,s);
        notifyItemInserted(position);

    }

    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public ImageView imageview;
        public TextView textview;

        public MyHolder(View v) {
            super(v);
            imageview = (ImageView) v.findViewById(R.id.iv_icon);
            textview = (TextView) v.findViewById(R.id.tv_title);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.OnItemClick(view, datas.get(getLayoutPosition()));

                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void OnItemClick(View view, String data);
    }

    public OnItemClickListener onItemClickListener;

    public void SetOnItemClickListener(OnItemClickListener mylistener) {
        this.onItemClickListener = mylistener;
    }

}
