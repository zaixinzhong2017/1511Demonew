package com.example.yfeng.a1511demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yfeng.a1511demo.bean.ZhuanJi;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<ZhuanJi> list;
    GridView gv;
    MyAdapter adapter;
    String[] arrayName = {"《八度空间》","《叶惠美》","《第三个》","《第四个》","《第五个》","《第六个》","《第七个》","《第八个》","《第九个》","《第十个》"};
    String[] arraySinger = {"刘德华","周杰伦","小3","小4","小5","小6","小7","小8","小9","小10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        showGV();
    }

    private void showGV() {
        list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            ZhuanJi zj = new ZhuanJi();
            if(i%2 == 1){
                zj.setImage(R.mipmap.ic_launcher);
            }else {
                zj.setImage(R.mipmap.ic_launcher_round);
            }
            zj.setName(arrayName[i]);
            zj.setSinger(arraySinger[i]);
            zj.setNum(6 + random.nextInt(6));
            list.add(zj);
        }
        adapter = new MyAdapter();
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SongListActivity.class);
                intent.putExtra("bean", list.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        gv = findViewById(R.id.main_gv);
        initTop();
    }

    private void initTop() {
        findViewById(R.id.top_btn_back).setVisibility(View.GONE);
        findViewById(R.id.top_btn_del).setVisibility(View.GONE);
        findViewById(R.id.top_tv_content).setVisibility(View.GONE);
    }

    class MyAdapter extends BaseAdapter{

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
            ViewHolder holder;
            if(convertView == null){
                convertView = View.inflate(MainActivity.this, R.layout.gridview_item, null);
                holder = new ViewHolder();
                holder.tvName = convertView.findViewById(R.id.item_iv_name);
                holder.tvNum = convertView.findViewById(R.id.item_tv_num);
                holder.tvSinger = convertView.findViewById(R.id.item_tv_singer);
                holder.ivLogo = convertView.findViewById(R.id.item_iv_logo);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.ivLogo.setImageResource(list.get(position).getImage());
            holder.tvName.setText(list.get(position).getName());
            holder.tvSinger.setText(list.get(position).getSinger());
            holder.tvNum.setText(list.get(position).getNum() + "首");
            return convertView;
        }
    }
    public static class ViewHolder{
        TextView tvName;
        TextView tvSinger;
        TextView tvNum;
        ImageView ivLogo;
    }
}
