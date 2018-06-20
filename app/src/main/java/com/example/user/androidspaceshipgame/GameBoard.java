package com.example.user.androidspaceshipgame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard extends View {

    private static final int NUM_OF_STARS = 50;
    private Paint p;
    //private  Paint p1;
    private List<Point> starField = null;
    private int starAlpha = 80;
    private int starFade = 2;
    private Rect sprite1Bounds = new Rect(0, 0, 0, 0);
    private Rect sprite2Bounds = new Rect(0, 0, 0, 0);
    private Rect sprite3Bounds = new Rect(0, 0, 0, 0);
    private Rect sprite4Bounds = new Rect(0,0,0,0);
    private Rect sprite5Bounds = new Rect(0,0,0,0);
    private Point sprite1;
    private Point sprite2;
    private Point sprite3;
    private Point sprite4;
    private Point sprite5;

    private Bitmap bm1 = null;
    private Matrix m = null;
    private Bitmap bm2 = null;
    private Bitmap bm3 = null;
    private Bitmap bm4 = null;
    private Bitmap bm5 = null;

//    private int backalpha =80;
//    private int backfade= 2;

    private boolean collisionDetected = false;
    private Point lastCollision = new Point(-1, -1);
    private int sprite1Rotation = 0;
    private int color = Color.BLACK;

    public GameBoard(Context context, AttributeSet aSet) {
        super(context, aSet);
        p = new Paint();
        //p1 = new Paint();
        sprite1 = new Point(-1, -1);
        sprite2 = new Point(-1, -1);
        sprite3 = new Point(-1, -1);
        sprite4 = new Point(-1, -1);
        sprite5 = new Point(-1, -1);

        m = new Matrix();

        bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.asteroid);
        bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.ufo);
        bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.asteroidd);
        bm4 = BitmapFactory.decodeResource(getResources(), R.drawable.moon);
        bm5 = BitmapFactory.decodeResource(getResources(), R.drawable.sun);
        sprite1Bounds = new Rect(0, 0, bm1.getWidth(), bm1.getHeight());
        sprite2Bounds = new Rect(0, 0, bm2.getWidth(), bm2.getHeight());
        sprite3Bounds = new Rect(0, 0, bm3.getWidth(), bm3.getHeight());
        sprite4Bounds = new Rect(0,0, bm4.getWidth(), bm4.getHeight());
        sprite5Bounds = new Rect(0,0, bm5.getWidth(), bm5.getHeight());


    }

    synchronized public void setSprite1(int x, int y) {
        sprite1 = new Point(x, y);
    }

    synchronized public int getSprite1X() {
        return sprite1.x;
    }

    synchronized public int getSprite1Y() {
        return sprite1.y;
    }

    synchronized public void setSprite2(int x, int y) {
        sprite2 = new Point(x, y);
    }

    synchronized public int getSprite2X() {
        return sprite2.x;
    }

    synchronized public int getSprite2Y() {
        return sprite2.y;
    }

    synchronized public void setSprite3(int x, int y) {
        sprite3 = new Point(x, y);
    }

    synchronized public int getSprite3X() {
        return sprite3.x;
    }

    synchronized public int getSprite3Y() {
        return sprite3.y;
    }


    synchronized public void setSprite4(int x, int y) {
        sprite4 = new Point(x, y);
    }

    synchronized public int getSprite4X() {
        return sprite4.x;
    }

    synchronized public int getSprite4Y() {
        return sprite4.y;
    }
    synchronized public void setSprite5(int x, int y) {
        sprite5 = new Point(x, y);
    }

    synchronized public int getSprite5X() {
        return sprite5.x;
    }

    synchronized public int getSprite5Y() {
        return sprite5.y;
    }

    synchronized public void resetStarField() {
        starField = null;
    }

    synchronized public int getSprite1Width() {
        return sprite1Bounds.width();
    }

    synchronized public int getSprite1Height() {
        return sprite1Bounds.height();
    }

    synchronized public int getSprite2Width() {
        return sprite2Bounds.width();
    }

    synchronized public int getSprite2Height() {
        return sprite2Bounds.height();
    }



    synchronized public int getSprite3Width() {
        return sprite3Bounds.width();
    }

    synchronized public int getSprite3Height() {
        return sprite3Bounds.height();
    }

    synchronized public int getSprite4Width() {
        return sprite4Bounds.width();
    }

    synchronized public int getSprite4Height() {
        return sprite4Bounds.height();
    }
        synchronized public int getSprite5Width() {
            return sprite5Bounds.width();
        }

        synchronized public int getSprite5Height() {
            return sprite5Bounds.height();
        }

    synchronized public Point getLastCollision() {
        return lastCollision;
    }

    synchronized public boolean wasCollisionDetected() {
        return collisionDetected;

    }
//    synchronized public void setPaintColor(int c) {
//         int pColor = c;
//    }
//    synchronized public int getPaintColor() {
//        int pColor = 0;
//        return pColor;
//    }



    synchronized private void initializeStars(int maxX, int maxY) {
        sprite3 = new Point(this.getWidth()/2,this.getHeight()/2);
        sprite4 = new Point(this.getWidth()/2,0);
        sprite5 = new Point(0,0);
        starField = new ArrayList<Point>();
        for (int i = 0; i < NUM_OF_STARS; i++) {
            Random r = new Random();
            int x = r.nextInt(maxX - 5 + 1) + 5;
            int y = r.nextInt(maxY - 5 + 1) + 5;
            starField.add(new Point(x, y));
        }
        collisionDetected = false;
    }

    private boolean checkForCollision() {
        if (sprite1.x < 0 && sprite2.x < 0 && sprite3.x < 0 && sprite1.y < 0 && sprite2.y < 0 &&
                sprite3.y < 0)
            return
                false;
        Rect r1 = new Rect(sprite1.x, sprite1.y, sprite1.x +
                sprite1Bounds.width(), sprite1.y + sprite1Bounds.height());
        Rect r2 = new Rect(sprite2.x, sprite2.y, sprite2.x +
                sprite2Bounds.width(), sprite2.y + sprite2Bounds.height());
        Rect r4 = new Rect(sprite3.x, sprite3.y, sprite3.x +
                sprite3Bounds.width(), sprite3.y + sprite3Bounds.height());





        Rect r3 = new Rect(r1);
        if (r1.intersect(r2) && r4.intersect(r2) && r1.intersect(r4))  {
            for (int i = r1.left; i < r1.right; i++) {
                for (int j = r1.top; j < r1.bottom; j++) {
                        if (bm1.getPixel(i - r3.left, j - r3.top) != Color.TRANSPARENT) {
                            if (bm2.getPixel(i - r2.left, j - r2.top) != Color.TRANSPARENT) {
                                if (bm3.getPixel(i - r4.left, j - r4.top) != Color.TRANSPARENT) {
                                    lastCollision = new Point(sprite2.x + i - r2.left, sprite2.y + j - r2.top);
                                    return true;
                                }
                            }
                        }
                }
            }
        }
        lastCollision = new Point(-1, -1);
        return false;
    }

    @Override
    synchronized public void onDraw(Canvas canvas) {

        p .setColor(color);
        p.setAlpha(255);
        p.setStrokeWidth(1);
        canvas.drawRect(0, 0, getWidth(), getHeight(), p);
        if (starField == null) {
            initializeStars(canvas.getWidth(), canvas.getHeight());
        }
        p.setColor(Color.CYAN);
        p.setAlpha(starAlpha += starFade);
        if (starAlpha >= 252 || starAlpha <= 80) starFade = starFade * -1;
        p.setStrokeWidth(5);
        for (int i = 0; i < NUM_OF_STARS; i++) {
            canvas.drawPoint(starField.get(i).x, starField.get(i).y, p);
        }
        if (sprite1.x >= 0) {
            m.reset();
            m.postTranslate((float) (sprite1.x), (float) (sprite1.y));
            m.postRotate(sprite1Rotation,
                    (float) (sprite1.x + sprite1Bounds.width() / 2.0),
                    (float) (sprite1.y + sprite1Bounds.width() / 2.0));
            canvas.drawBitmap(bm1, m, null);
            sprite1Rotation += 5;
            if (sprite1Rotation >= 360) sprite1Rotation = 0;
        }
        if (sprite2.x >= 0) {
            canvas.drawBitmap(bm2, sprite2.x, sprite2.y, null);
        }
        if (sprite3.x >= 0) {
            canvas.drawBitmap(bm3, sprite3.x, sprite3.y, null);
        }
        if (sprite4.x >=0) {
            canvas.drawBitmap(bm4,  sprite4.x - sprite4Bounds.width()/2 , sprite4.y , null);
            sprite4.x += 1;
            // Rect moonRect = new Rect(sprite4.x, sprite4.y, sprite4.x
            // + sprite4Bounds.width(),  sprite4.y + sprite4Bounds.height());
            // sun is comming in
            if(sprite4.x > this.getWidth() && sprite5.x/2 < this.getWidth()/2) {
                canvas.drawBitmap(bm5,  sprite5.x - sprite5Bounds.width() , sprite5.y ,null);
                // background color moon b/w sun
                color = Color.GRAY;

//                p1.setColor(color);
//                p1.setAlpha(255);
//                p1.setStrokeWidth(1);
//                canvas.drawRect(0, 0, getWidth(), getHeight(), p1);
//                if (starField == null) {
//                    initializeStars(canvas.getWidth(), canvas.getHeight());
//                }
//                p1.setColor(Color.CYAN);
//                p1.setAlpha(backalpha+= backfade);
//                if (backalpha >= 252 || backalpha <= 80) backfade = backfade * -1;
//                p1.setStrokeWidth(5);
//                for (int i = 0; i < sprite5.x; i++) {
//                    canvas.drawPoint(starField.get(i).x, starField.get(i).y, p1);
//                }

                if (((sprite5.x) - sprite5Bounds.width()/2) > this.getWidth()/2) {

                    //canvas.drawBitmap(bm5,  sprite5.x - sprite5Bounds.width() , sprite5.y , null);
                    //sprite5.x += 1;
                    // background color
                    color = Color.BLUE;

//for (int i=sprite5.x ; i>sprite5.x/2 ;i ++ )
//    int i= setBackgroundColor();


                }
                else {
                    sprite5.x += 1;
                }

            }

            //

        }


        collisionDetected = checkForCollision();
        if (collisionDetected) {
            p.setColor(Color.RED);
            p.setAlpha(255);
            p.setStrokeWidth(5);
            canvas.drawLine(lastCollision.x - 5, lastCollision.y - 5,
                    lastCollision.x + 5, lastCollision.y + 5, p);
            canvas.drawLine(lastCollision.x + 5, lastCollision.y - 5,
                    lastCollision.x - 5, lastCollision.y + 5, p);
        }
    }
}