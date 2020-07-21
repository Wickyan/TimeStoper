package com.example.timestoper;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv, tv2;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);

        tv.setText("aaaa");

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (true) {
                    try {
                        i++;
//                        tv2.setText("String.valueOf(i)" + i + "!!");

                        tv.setText(secToMin(i));

                        switch (i) {
                            case 3 * 60 :
                            case 5 * 60 :
                            case 12 * 60 :
                            case 15 * 60 :
                                vibrateByTime(500);
                                break;
                            case 1 * 60 :
                            case 6 * 60 :
                            case 18 * 60 :
                                vibrateByTime(1500);
                                break;
                        }

                        Thread.sleep(1000);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        Toast.makeText(MainActivity.this, "error",Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void demo(View view) {
        Toast.makeText(MainActivity.this, "第个6按钮被点击了",Toast.LENGTH_SHORT).show();

        /*
         震动
         */
        vibrateByTime(100);
        /*
        默认声音
         */
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }
    public void vibrateByTime(int time) {
        Vibrator vibrator = (Vibrator)this.getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(time);
    }

    public String secToMin(int seconds) {
        int minutes = seconds / 60;
        String min = "";
        if (minutes < 10) {
            min += "0";
        }
        min += minutes;

        int remainingSeconds = seconds % 60;
        String sec = "";
        if (remainingSeconds < 10) {
            sec += "0";
        }
        sec += remainingSeconds;

        String ss = min + ":" + sec;
        return ss;
    }
}