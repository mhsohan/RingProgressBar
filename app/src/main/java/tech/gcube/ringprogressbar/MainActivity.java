package tech.gcube.ringprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    RingProgressBar ringProgressBar1;

    int progress = 0;

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {

            //HERE THE PROGRESS WILL INCREASE
            if (msg.what == 0) {
                if (progress < 100) {
                    progress++;
                    ringProgressBar1.setProgress(progress);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ringProgressBar1 = (RingProgressBar) findViewById(R.id.progress_bar_1);

        ringProgressBar1.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                Toast.makeText(MainActivity.this,"Complete!!",Toast.LENGTH_LONG).show();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<100;i++){
                    try{
                        Thread.sleep(100);
                        myHandler.sendEmptyMessage(0);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}