package com.liyuanjinglyj.shapedrawableapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class LoupeView extends View {
    private Bitmap bitmap;//获取原图片
    private ShapeDrawable shapeDrawable;//创建一个
    private final Matrix matrix = new Matrix();//矩阵运算
    private static final int RADIUS = 160;//半径
    private static final int TIMES = 1;//放大倍数
    private BitmapShader bitmapShader;

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);//禁止硬件加速
    }

    private loupeViewInterface loupeViewInterface;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (loupeViewInterface != null) {
            loupeViewInterface.OnListeningView();
        }
        if (this.bitmap == null) {
            bitmapShader = new BitmapShader(
                    Bitmap.createScaledBitmap(this.bitmap, bitmap.getWidth() * TIMES, bitmap.getHeight() * TIMES, true),//按比例进行缩放图片
                    Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
        }
        if (this.shapeDrawable == null) {
            this.shapeDrawable = new ShapeDrawable();//构造一个椭圆图形
        }
        if (bitmapShader != null){
        this.shapeDrawable.getPaint().setShader(bitmapShader);
        }

        canvas.drawBitmap(this.bitmap, 0, 0, null);
        this.shapeDrawable.draw(canvas);

        invalidate();
    }

    public void OnViewListening(loupeViewInterface onListeningView) {
        loupeViewInterface = onListeningView;
    }


    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        final int x = (int) event.getX();
//        final int y = (int) event.getY();
//        this.matrix.setTranslate(RADIUS - x * TIMES, RADIUS - y * TIMES);
//        this.shapeDrawable.getPaint().getShader().setLocalMatrix(this.matrix);
//
//        this.shapeDrawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
//        invalidate();
//        return true;
//    }

    public LoupeView(Context context) {
        super(context);
        this.init();
    }

    public LoupeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public LoupeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }
}
