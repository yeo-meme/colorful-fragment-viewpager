package com.nalazoocare.threadanimauto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn[] = new Button[3];
    ViewPager viewPager = null;



    //2차
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 3000;           // 오토 플립용 타이머 시작 후 해당 시간에 작동(초기 웨이팅 타임) ex) 앱 로딩 후 3초 뒤 플립됨.
    final long PERIOD_MS = 5000;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //viewPager

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        btn[0] = (Button)findViewById(R.id.btn_a);

        btn[1] = (Button)findViewById(R.id.btn_b);

        btn[2] = (Button)findViewById(R.id.btn_c);

        for(int i=0;i<btn.length; i++){
            btn[i].setOnClickListener(this);
        }



//        handler = new Handler(){
//            public void handleMessage(android.os.Message msg) {
//                if(p==0){
//                    viewPager.setCurrentItem(1);
//                    p++;
//                    v=1;
//                }if(p==1&&v==0){
//                    viewPager.setCurrentItem(1);
//                    p--;
//                }if(p==1&&v==1){
//                    viewPager.setCurrentItem(2);
//                    p++;
//                }if(p==2){
//                    viewPager.setCurrentItem(1);
//                    p--;
//                    v=0;
//                }
//            }
//        };





//
//        thread = new Thread(){
//            //run은 jvm이 쓰레드를 채택하면, 해당 쓰레드의 run메서드를 수행한다.
//            public void run() {
//                super.run();
//                while(true){
//                    try {
//                        Thread.sleep(1000);
//                        handler.sendEmptyMessage(0);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//
//        thread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Adapter 세팅 후 타이머 실행
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                currentPage = viewPager.getCurrentItem();
                int nextPage = currentPage + 1;

                if (nextPage >= 3) {
                    nextPage = 0;
                }
                viewPager.setCurrentItem(nextPage, true);
                currentPage = nextPage;
            }
        };

        timer = new Timer(); // thread에 작업용 thread 추가
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btn_a:

                viewPager.setCurrentItem(0);

                Toast.makeText(this,"A버튼", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_b:

                viewPager.setCurrentItem(1);

                Toast.makeText(this,"B버튼", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_c:

                viewPager.setCurrentItem(2);

                Toast.makeText(this,"C버튼", Toast.LENGTH_SHORT).show();

                break;

            default:

                break;



        }



    }



}
