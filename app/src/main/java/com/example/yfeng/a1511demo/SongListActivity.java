package com.example.yfeng.a1511demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yfeng.a1511demo.bean.ZhuanJi;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    ArrayList<String> list;
    ListView lv;
    MyListAdapter adapter;

    Intent proIntent;
    ZhuanJi zj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        proIntent = getIntent();
        zj = (ZhuanJi) proIntent.getSerializableExtra("bean");
        initView();
        showLV();
    }

    private void showLV() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("歌曲" + i);
        }
        adapter = new MyListAdapter();
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SongListActivity.this, SongDetailActivity.class);
                intent.putExtra("bean", zj);
                intent.putExtra("song", list.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        lv = findViewById(R.id.song_lv);
        initTop();
    }

    private void initTop() {

        findViewById(R.id.top_btn_back).setVisibility(View.GONE);
        findViewById(R.id.top_btn_del).setVisibility(View.GONE);
        TextView tv = findViewById(R.id.top_tv_content);
        tv.setText(zj.getSinger() + "--" + zj.getName());
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if(list != null){
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LVViewHolder holder;
            if(convertView == null){
                convertView = View.inflate(SongListActivity.this, R.layout.listview_item, null);
                holder = new LVViewHolder();
                holder.tvName = convertView.findViewById(R.id.list_item_tv_name);
                convertView.setTag(holder);
            }else{
                holder = (LVViewHolder) convertView.getTag();
            }
            holder.tvName.setText(list.get(position));
            return convertView;
        }
    }
    public static class LVViewHolder{
        TextView tvName;
    }
}
