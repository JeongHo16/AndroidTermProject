package com.hansung.teamproject.homework1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Junho on 2017-11-30.
 */

public class AnimatedSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Thread thread;
    private ArrayList<Ball> aryBall = new ArrayList<Ball>();
    public int count = 10;

    SurfaceHolder holder;

    public AnimatedSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("Log checking", "start" );
        holder = getHolder();
        holder.addCallback(this);

        thread = new Thread(){
            public void run(){
                createBalls(getWidth(), getHeight());
                draw();
            }
        };
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
        Log.i("Log checking", "thread start" );
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.i("Log checking", "change" );
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void draw(){
        while(true){
            Canvas canvas = holder.lockCanvas(null);
            if (canvas == null)
                break;
            canvas.drawColor(Color.BLACK);
            Random random = new Random();
            synchronized (holder){
                for(int idx = 0; idx< aryBall.size(); idx++){
                    Ball b = aryBall.get(idx);
                    b.bigger(random.nextInt(5));
                    b.draw(canvas);
                }
            }
            try{
                holder.unlockCanvasAndPost(canvas);
            }catch (Exception e){
                break;
            }
        }
    }

    private void createBalls(int x, int y){
        Random random = new Random();
        for(int i = 0; i < count; i++){
            aryBall.add(new Ball(random.nextInt(x), random.nextInt(y)));
        }
    }

  /*  public boolean onTouchEvent(MotionEvent event){
        Log.i("Log checking", "touch" );
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            int x = (int)event.getX();
            int y = (int) event.getY();

            aryBall.add(new Ball(x, y));
            return true;
        }
        return false;
    }*/
}
