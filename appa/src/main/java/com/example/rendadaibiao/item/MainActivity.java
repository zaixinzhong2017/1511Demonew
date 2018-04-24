package com.example.rendadaibiao.item;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.rendadaibiao.item.bean.Shop;
import com.example.rendadaibiao.item.okhttp.OkHttpUtils;
import com.example.rendadaibiao.item.okhttp.OnUiCallback;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    List<Shop.TuijianBean.ListBean> list;
    CheckBox ck;
    List<String> lists=new ArrayList<>();
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.rv);
        ck=findViewById(R.id.ck);
        bt=findViewById(R.id.bt);

        LinearLayoutManager mgs = new LinearLayoutManager(this);
        rv.setLayoutManager(mgs);
        initData();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                EventGoodsList goods = new EventGoodsList(list);
                EventBus.getDefault().postSticky(goods);
                startActivity(intent);
            }
        });

    }

    private void initData() {
        OkHttpUtils.getInstance().doGet("https://www.zhaoapi.cn/ad/getAd", new OnUiCallback() {
            @Override
            public void onFailed(Call call, IOException e) {

            }

            @Override
            public void onSuccess(String result) throws IOException {
                Gson gson = new Gson();
                Shop shop=gson.fromJson(result,Shop.class);
                list=new ArrayList<Shop.TuijianBean.ListBean>();
                list=shop.getTuijian().getList();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Myadpter myadpter=new Myadpter();
                        rv.setAdapter(myadpter);
                    }
                });
            }
        });

    }
    class Myadpter extends RecyclerView.Adapter<Myadpter.MyViewHolder>{


        @Override
        public Myadpter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=View.inflate(MainActivity.this, R.layout.item,null);
            MyViewHolder holder=new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(Myadpter.MyViewHolder holder, final int position) {
            String images = list.get(position).getImages();
            String[] split = images.split("\\|");
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(split[0]))
                    .build();
            holder.sim.setController(controller);
            holder.tv.setText(list.get(position).getPrice()+"");
//            holder.ck.setChecked(list.get(position).checked);
            holder.tv1.setText(list.get(position).getCreatetime());
//            final Shop.TuijianBean.ListBean listBean = list.get(position);
            holder.ck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.get(position).setChecked(!list.get(position).isChecked());
                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv,tv1;
            SimpleDraweeView sim;
            CheckBox ck;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv=itemView.findViewById(R.id.tv);
                sim=itemView.findViewById(R.id.sim);
                ck=itemView.findViewById(R.id.ck);
                tv1=itemView.findViewById(R.id.tv1);
            }
        }
    }


}
