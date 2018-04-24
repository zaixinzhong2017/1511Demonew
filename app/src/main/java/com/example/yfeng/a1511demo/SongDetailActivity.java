package com.example.yfeng.a1511demo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.yfeng.a1511demo.bean.ZhuanJi;

public class SongDetailActivity extends AppCompatActivity {

    Button btnPlay;
    Button btnBack,btnDel;
    SeekBar sb;
    ImageView iv;
    TextView tvContent;

    MediaPlayer mp;

    Dialog dialog;
    String song;

    Animation rotate;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            sb.setProgress(mp.getCurrentPosition());
            handler.sendEmptyMessageDelayed(0, 500);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detial);

        initView();
        setView();

        initMedia();
    }

    private void initMedia() {
        mp = MediaPlayer.create(this, R.raw.bajie);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
        sb.setMax(mp.getDuration());
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void play(){
        if(!mp.isPlaying()){
            mp.start();
        }
    }

    public void pause(){
        if(mp.isPlaying()){
            mp.pause();
        }
    }

    public void startAnim(){
        rotate = AnimationUtils.loadAnimation(this, R.anim.my_rotate);
        LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
        rotate.setInterpolator(interpolator);
        iv.startAnimation(rotate);
    }

    public void stopAnim(){
        iv.clearAnimation();
    }

    private void setView() {
        handler.sendEmptyMessage(0);
        Intent intent = getIntent();
        ZhuanJi zj = (ZhuanJi) intent.getSerializableExtra("bean");
        song = intent.getStringExtra("song");
        tvContent.setText(zj.getSinger() + "--" + zj.getName() + "--" + song);
        iv.setImageResource(zj.getImage());

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = btnPlay.getText().toString();
                if("开始".equals(str)){
                    btnPlay.setText("暂停");
                    play();
                    startAnim();
                }else{
                    btnPlay.setText("开始");
                    pause();
                    stopAnim();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void showDialog(){
        dialog = new AlertDialog.Builder(this)
                .setTitle("删除歌曲")
                .setMessage("确定删除" + song)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void initView() {
        btnPlay = findViewById(R.id.detail_play);
        sb = findViewById(R.id.detail_sb);
        iv = findViewById(R.id.detail_iv);
        initTop();
    }

    private void initTop() {
        btnBack = findViewById(R.id.top_btn_back);
        btnDel = findViewById(R.id.top_btn_del);
        tvContent = findViewById(R.id.top_tv_content);
    }
}
