package com.example.skvayzer.diamondauthority;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
//import android.widget.Button;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    ImageButton yellowB,blueB,pinkB;
    ImageView whiteB;
    ImageView diamoat,dlight, fon;
    Animation diamat,goaway,light,dyinglight,appearance, disappearance;
    TextView tv,test;
    boolean mayi=true;
    Timer timer;
    float volumee;
    boolean whiteF,yellowF,blueF,pinkF;

//    MediaPlayer yel, whi, blu, pin;
    SoundPool sp;
    int whis,yels,blus,pins,diat;
    int wsid,ysid,bsid,psid,diatsid;
    static final int st = AudioManager.STREAM_MUSIC;
    float volume;
    AudioManager audioManager;
boolean loaded;
    int[] a= {0,0,0,0,0};

    public void setdiam() {
        int k = 0;
        for (int i = 0; i < a.length - 1; i++) {

                k = a[i];
                a[i] = a[i + 1];

                a[i + 1] = k;


        }
    }
    public void arraycheck(){
        test.setText(10000*a[0] + 1000*a[1] + 100*a[2] + 10*a[3] + a[4] + "");
        if(a[0]==1 && a[1]==2 && a[2]==3 && a[3]==4 && a[4]==1){

            diamoat.setVisibility(View.VISIBLE);
            diamoat.startAnimation(diamat);
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                public void run() {
                    dlight.setVisibility(View.VISIBLE);
                    dlight.startAnimation(light);


                }
            }, 500);

            Handler handler2 = new Handler();
            handler1.postDelayed(new Runnable() {
                public void run() {
                    tv.setVisibility(View.VISIBLE);
                    fon.setVisibility(View.VISIBLE);
                    fon.startAnimation(appearance);
                    tv.startAnimation(appearance);


                }
            }, 1500);


            if(loaded)  {


                diatsid=sp.play(diat,volume, volume,1,0, 1f);

            }

            Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            diamoat.startAnimation(goaway);
                            tv.setVisibility(View.INVISIBLE);
                            fon.setVisibility(View.INVISIBLE);
                            dlight.startAnimation(dyinglight);
                            fon.startAnimation(disappearance);
                            tv.startAnimation(disappearance);


                        }
                    }, 5000);




        }else if(a[1]==3 && a[2]==2 && a[3]==3 && a[4]==2){
            Intent i=new Intent(MainActivity.this, Main2Activity.class);
            startActivityForResult(i, 1);
        }
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Current volumn Index of particular stream type.
        float currentVolumeIndex = (float) audioManager.getStreamVolume(st);

        // Get the maximum volume index for a particular stream type.
        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(st);

        // Volumn (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;
        volumee=volume;
        // Suggests an audio stream whose volume should be changed by
        // the hardware volume controls.
        this.setVolumeControlStream(st);





        sp=new SoundPool(6, AudioManager.STREAM_MUSIC, 0);
        this.sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        whis=sp.load(this,R.raw.whiteth,1);
        yels=sp.load(this,R.raw.yellowth,1);
        blus=sp.load(this,R.raw.blueth,1);
        pins=sp.load(this,R.raw.pinkth,1);
        diat=sp.load(this,R.raw.diattack,1);

        diamoat = (ImageView) findViewById(R.id.diattack);
        dlight= (ImageView) findViewById(R.id.dbg);


        diamat = AnimationUtils.loadAnimation(this, R.anim.diattack);
        goaway= AnimationUtils.loadAnimation(this, R.anim.goaway);
        light=AnimationUtils.loadAnimation(this, R.anim.light);
        dyinglight=AnimationUtils.loadAnimation(this,R.anim.dyinglight);
        appearance=AnimationUtils.loadAnimation(this, R.anim.appearance);
        disappearance=AnimationUtils.loadAnimation(this, R.anim.disappearance);



        whiteB = (ImageView) findViewById(R.id.whiteD);
        yellowB = (ImageButton) findViewById(R.id.yellowD);
        blueB = (ImageButton) findViewById(R.id.blueD);
        pinkB = (ImageButton) findViewById(R.id.pinkD);
        tv=(TextView) findViewById(R.id.tv);
        test=(TextView) findViewById(R.id.test);
        fon=(ImageView) findViewById(R.id.fon);
//        whi=MediaPlayer.create(MainActivity.this, R.raw.whiteth);
//        yel=MediaPlayer.create(MainActivity.this, R.raw.yellowth);
//        blu=MediaPlayer.create(MainActivity.this, R.raw.blueth);
//        pin=MediaPlayer.create(MainActivity.this, R.raw.pinkth);
////        whi.setLooping(true);
//        yel.setLooping(true);
//        blu.setLooping(true);
//        pin.setLooping(true);
        whiteF=true;
        yellowF=true;
        blueF=true;
        pinkF=true;

        whiteB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN) {


                        whiteB.setImageResource(R.drawable.white_click);

                        if (loaded) {

                            if(!mayi) {
                                timer.cancel();
                                sp.stop(wsid);
                                sp.stop(ysid);
                                sp.stop(bsid);
                                sp.stop(psid);
                                mayi=true;
                            }
                            volumee = volume;


                            wsid = sp.play(whis, volume, volume, 1, 50, 1f);



                        }

                    //whi.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {

                    whiteB.setImageResource(R.drawable.white);

                    setdiam();
                    a[4]=1;
                    timer=new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            mayi=false;
                            volumee-=0.1f;
                            if(volumee<0) {
                                sp.stop(wsid);
                                mayi=true;
                                cancel();
                            }

                            sp.setVolume(wsid, volumee, volumee);

                        }
                    }, 0,100);


                    //sp.stop(wsid);
                    arraycheck();

                    //whi.pause();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        public void run() {
//
//                            whi.pause();
//                        }
//                    }, 500);


                }
                return true;
            }
        });
        yellowB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    yellowB.setImageResource(R.drawable.yellow_click);
                    //yel.start();
                    if(loaded)  {
                        if(!mayi) {
                            timer.cancel();
                            sp.stop(wsid);
                            sp.stop(ysid);
                            sp.stop(bsid);
                            sp.stop(psid);
                            mayi=true;
                        }
                        volumee=volume;

                        ysid = sp.play(yels,volume, volume, 1, 50, 1f);

                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {

                    yellowB.setImageResource(R.drawable.yellow);
                    setdiam();
                    a[4]=2;

                    timer=new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            mayi=false;
                            volumee-=0.1f;
                            if(volumee<0) {
                                sp.stop(ysid);
                                mayi=true;
                                cancel();
                            }

                            sp.setVolume(ysid, volumee, volumee);

                        }
                    }, 0,100);

                    arraycheck();
                   // yel.pause();


                }
                return true;
            }
        });
        blueB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    blueB.setImageResource(R.drawable.blue_click);
                    //blu.start();
                    if(loaded)  {
                        if(!mayi) {
                            timer.cancel();
                            sp.stop(wsid);
                            sp.stop(ysid);
                            sp.stop(bsid);
                            sp.stop(psid);
                            mayi=true;
                        }

                        bsid = sp.play(blus,volume, volume, 1, 50, 1f);
                        volumee=volume;
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    blueB.setImageResource(R.drawable.blue);
                    setdiam();
                    a[4]=3;
                    timer=new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            mayi=false;
                            volumee-=0.1f;
                            if(volumee<0) {
                                sp.stop(bsid);
                                mayi=true;
                                cancel();
                            }

                            sp.setVolume(bsid, volumee, volumee);

                        }
                    }, 0,100);

                    arraycheck();
                    //blu.pause();

              }
                return true;
            }
        });
        pinkB.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    pinkB.setImageResource(R.drawable.pink_click);
                    if(loaded)  {
                        if(!mayi) {
                            timer.cancel();
                            sp.stop(wsid);
                            sp.stop(ysid);
                            sp.stop(bsid);
                            sp.stop(psid);
                            mayi=true;
                        }

                        psid = sp.play(pins,volume, volume, 1, 50, 1f);
                        volumee=volume;
                    }
                    //pin.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP ||
                        event.getAction() == MotionEvent.ACTION_CANCEL) {
                    pinkB.setImageResource(R.drawable.pink);
                    setdiam();
                    a[4]=4;
                    timer=new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            mayi=false;
                            volumee-=0.1f;
                            if(volumee<0) {
                                sp.stop(psid);
                                mayi=true;
                                cancel();
                            }

                            sp.setVolume(psid, volumee, volumee);

                        }
                    }, 0,100);

                    arraycheck();
                   // pin.pause();

                }
                return true;
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                tv.setText(data.getStringExtra("et"));
                break;
        }
    }





    };


