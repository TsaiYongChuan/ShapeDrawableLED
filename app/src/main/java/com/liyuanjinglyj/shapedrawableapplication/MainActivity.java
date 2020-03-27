package com.liyuanjinglyj.shapedrawableapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;


public class MainActivity extends AppCompatActivity {

    private LoupeView lv;
    private MyHorizontalProgress hp;
    private EZLedView ledView,ledView2;
    private LottieAnimationView la,la2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        hp = findViewById(R.id.hp);
        ledView = findViewById(R.id.ledView);
        ledView2 = findViewById(R.id.ledView2);
        la = findViewById(R.id.la);
        la2 = findViewById(R.id.la2);
        lv.OnViewListening(new loupeViewInterface() {
            @Override
            public void OnListeningView() {

                la.destroyDrawingCache();
                //设置视图是否开启缓存
                la.setDrawingCacheEnabled(true);
                //会调用buildDrawingCache，将View转换为bitmap并赋值给mDrawingCache
                Bitmap bitmap = la.getDrawingCache(true);
                //bitmap转为Drawable对象
                Drawable drawable = new BitmapDrawable(null, bitmap);
                ledView.setDrawable(drawable);

                Drawable drawable2 = new BitmapDrawable(null, loadBitmapFromView(la2));
                ledView2.setDrawable(drawable2);

//                lv.setBitmap(bitmap);
                    lv.invalidate();
            }
        });
    }


//    之后再获取Bitmap就没问题了：

    private Bitmap loadBitmapFromView(View v) {

        int w = v.getWidth();

        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);

/** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);

        v.draw(c);

        return bmp;

    }
}
