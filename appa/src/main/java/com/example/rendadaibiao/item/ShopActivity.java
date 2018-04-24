package com.example.rendadaibiao.item;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.rendadaibiao.item.bean.Shop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    private EventGoodsList event;
    List<Shop.TuijianBean.ListBean> newList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ListView lv=findViewById(R.id.lv);

        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void setData(EventGoodsList event) {
        this.event = event;
        setsss();
    }

    private void setsss() {
        List<Shop.TuijianBean.ListBean> list = event.getList();
        for (int i = 0; i < list.size(); i++) {
            boolean checked = list.get(i).isChecked();
            if(checked){
                Shop.TuijianBean.ListBean listBean = list.get(i);
                newList.add(listBean);
                Log.i("===========list", "setsss: "+newList.size());
            }
        }
    }
}
