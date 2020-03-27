package com.liyuanjinglyj.shapedrawableapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class OvalShapeView extends View {
    private ShapeDrawable shapeDrawable;//定义一个ShapeDrawable

    /***
     * 初始化
     */
    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);//关闭硬件加速
        this.shapeDrawable=new ShapeDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.shapeDrawable.draw(canvas);
    }

    public OvalShapeView(Context context) {
        super(context);
        this.init();
    }

    public OvalShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public OvalShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }
}
