package com.hansung.teamproject.homework1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Junho on 2017-11-30.
 */

public class Ball {
    final int Rad = 12;
    int mx, my;             // 중심값
    int mWidth, mHeight;    // 넓이와 높이

    int dRad;    // 반환된 반지름
    int color;

    public Ball(int x, int y){
        mx = x;
        my = y;
        mWidth = mHeight = Rad *2;

        Random random = new Random();
        while(dRad == 0){ // 원넓이 0 제외
            dRad = random.nextInt(3);
        }
        color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();

        for (int r = 0, alpha = 1; r < Rad; r++, alpha +=5){
            paint.setColor(Color.argb(alpha,
                    Color.red(color),
                    Color.green(color),
                    Color.blue(color)));
            canvas.drawCircle(mx, my, r + dRad, paint);
        }
    }

    public void bigger(int nrad){
        dRad += nrad;
    }
}

