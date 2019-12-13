package com.example.eva3_6_banner_post;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    int tiempo = 900;
    int[] tiempos = {900,800,700,600,500,400,300,200,100,50};
    int contador = 0;
    SeekBar barra;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            switch(contador){
                case 0:
                    image.setImageResource(R.drawable.atmospher);
                    contador++;
                    break;
                case 1:
                    image.setImageResource(R.drawable.cloudy);
                    contador++;
                    break;
                case 2:
                    image.setImageResource(R.drawable.tornado);
                    contador++;
                    break;
                case 3:
                    image.setImageResource(R.drawable.snow);
                    contador = 0;
                    break;

            }
        }
    };

    Thread hilo = new Thread(){
        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    Thread.sleep(tiempo);
                    handler.post(runnable);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.imageView);
        hilo.start();
        barra = findViewById(R.id.seekBar);
        barra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tiempo =tiempos[i];
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}