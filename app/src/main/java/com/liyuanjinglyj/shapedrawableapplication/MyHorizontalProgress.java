package com.liyuanjinglyj.shapedrawableapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

import static android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT;

public class MyHorizontalProgress extends View {

    private static final int DEFAULT_ANIMATION_DURATION = 2000;
    private static final int DEFAULT_START_COLOR = Color.RED;
    private static final int DEFAULT_END_COLOR = Color.BLUE;


    private int animationDuration;
    private int startColor;
    private int endColor, direction;

    private int laidOutWidth;
    private final Paint paint;
    private Path path1;
    int[] colorStare;
    int[] oldColorStare;

    Drawable drawable;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int offset = MyHorizontalProgress.this.offset / 2;
        int w = width / 2;
        int v = (int) ((Math.sqrt(2) * w - w) / 2);
        drawPart(canvas, offset, w, v, 0, 0, 180);
        drawPart(canvas, offset, w, v, w, 0, 270);
        drawPart(canvas, offset, w, v, 0, w, 90);
        drawPart(canvas, offset, w, v, w, w, 0);
    }

    // 绘制分片数据
    private void drawPart(Canvas canvas, int offset, int w, int v, int i, int i2, int i3) {
        canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(i, i2);
        canvas.rotate(i3, w / 2, w / 2);
        drawColors(canvas, offset, w, v);
        canvas.restore();
    }

    // 独立分片内容绘制
    private void drawColors(Canvas canvas, int offset, int w, int v) {
        canvas.clipRect(0, 0, w, w);
        drawable.setBounds(-v, -v, w + v, w + v);
        canvas.save();
        canvas.translate(offset, offset);
        canvas.rotate(45, w / 2, w / 2);
        drawable.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.translate(-w + offset, -w + offset);
        canvas.rotate(45, w / 2, w / 2);
        drawable.draw(canvas);
        canvas.restore();
        canvas.save();
        canvas.translate(w + offset, w + offset);
        canvas.rotate(45, w / 2, w / 2);
        drawable.draw(canvas);
        canvas.restore();
    }


    public MyHorizontalProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path1 = new Path();
        readAttributes(attrs);


        colorStare = new int[]{
                getResources().getColor(R.color.color_view7),
                getResources().getColor(R.color.color_view8),
                getResources().getColor(R.color.color_view9),
                getResources().getColor(R.color.color_view10),
                getResources().getColor(R.color.color_view11),
                getResources().getColor(R.color.color_view12),
                getResources().getColor(R.color.color_view1),
                getResources().getColor(R.color.color_view2),
                getResources().getColor(R.color.color_view3),
                getResources().getColor(R.color.color_view4),
                getResources().getColor(R.color.color_view5),
                getResources().getColor(R.color.color_view6),
        };


        oldColorStare = colorStare;
        drawable = new GradientDrawable(RIGHT_LEFT, colorStare);



        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                laidOutWidth = MyHorizontalProgress.this.getWidth();

                ValueAnimator animator = ValueAnimator.ofInt(0, 2 * laidOutWidth);
                animator.setInterpolator(new LinearInterpolator());
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.setDuration(5000);
                animator.addUpdateListener(updateListener);
                animator.start();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    int offset = 0;


    private void readAttributes(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyHorizontalProgress);
        animationDuration = a.getInt(R.styleable.MyHorizontalProgress_animationDuration, DEFAULT_ANIMATION_DURATION);
        startColor = a.getColor(R.styleable.MyHorizontalProgress_gradientStartColor, DEFAULT_START_COLOR);
        endColor = a.getColor(R.styleable.MyHorizontalProgress_gradientEndColor, DEFAULT_END_COLOR);
        direction = a.getColor(R.styleable.MyHorizontalProgress_direction, 0);
        a.recycle();
    }

    //    int offsetValue = 0;
    private ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {

        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            offset = (int) valueAnimator.getAnimatedValue() + (-1 * laidOutWidth);
//            Log.e("offset = ", "" + offset);
//            int[] colors = new int[oldColorStare.length];
//            for (int i = 0; i < oldColorStare.length; i++) {
//                colors[i] = oldColorStare[(i + offsetValue) % oldColorStare.length];
//            }
//            offsetValue += 1;
//            if (offsetValue > oldColorStare.length) offsetValue = 0;
//            drawable = new GradientDrawable(TL_BR, colors);
            MyHorizontalProgress.this.postInvalidate();
//            one.setTranslationX(calculateOneTranslationX(laidOutWidth, offset));
//            one.setTranslationY(calculateOneTranslationX(laidOutWidth, offset));
//            two.setTranslationX(calculateTwoTranslationX(laidOutWidth, offset));
//            two.setTranslationY(calculateTwoTranslationX(laidOutWidth, offset));
        }
    };

    private int calculateOneTranslationX(int width, int offset) {
        return (-1 * width) + offset;
    }

    private int calculateTwoTranslationX(int width, int offset) {
        if (offset <= width) {
            return offset;
        } else {
            return (-2 * width) + offset;
        }
    }
}