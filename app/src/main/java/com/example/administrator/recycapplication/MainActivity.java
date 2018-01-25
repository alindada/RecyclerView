package com.example.administrator.recycapplication;


import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private Button btn_del;
    private Button btn_list;
    private Button btn_grid;
    private Button btn_flow;
    private RecyclerView v_flow;
    private ArrayList<String> datas;
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);
    }

    public void initView() {
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_grid = (Button) findViewById(R.id.btn_grid);
        btn_flow = (Button) findViewById(R.id.btn_flow);
        v_flow = (RecyclerView) findViewById(R.id.v_flow);


        datas = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            datas.add("Content" + i);
        }

        //设置适配器
        adapter = new MyRecyclerViewAdapter(MainActivity.this, datas);
        v_flow.setAdapter(adapter);
        adapter.SetOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

        //设置布局方式
        v_flow.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        v_flow.scrollToPosition(0);


        //分割线
        Drawable drawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.line_divider);
        v_flow.addItemDecoration(new DividertemDecoration(this, drawable, 1));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                adapter.addData(0,"enw nwe ");
                v_flow.scrollToPosition(0);
               // Toast.makeText(MainActivity.this, "添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_del:
                adapter.removeData(0);
                //Toast.makeText(MainActivity.this, "删除", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_list:
                v_flow.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.btn_grid:
                v_flow.setLayoutManager(new GridLayoutManager(MainActivity.this, 3, GridLayoutManager.VERTICAL, false));
                break;
            case R.id.btn_flow:
                v_flow.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
                break;
        }

    }
}
