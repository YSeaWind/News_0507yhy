package com.example.aNews_0507yhy.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class MyImageView extends AppCompatImageView {
    private int r;
    float scale;
    private float mBorderWidth = dip2px(15);
    private int mBorderColor = 0xFF0080FF;//外边框的颜色
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private int dip2px(int dipVal) {
        float scale = getResources().getDisplayMetrics().density;
        return (int)(dipVal * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = Math.min(getMeasuredWidth(),getMeasuredHeight());
        r = i / 2;
        setMeasuredDimension(i,i);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        Drawable drawable = getDrawable();
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.CLAMP);
        scale = (r * 2.0f) / Math.min(bitmap.getWidth(),bitmap.getHeight());

        Matrix matrix = new Matrix();
        matrix.setScale(scale,scale);
        bitmapShader.setLocalMatrix(matrix);

        paint.setShader(bitmapShader);


        paint.setStyle(Paint.Style.FILL_AND_STROKE);//影响圆形显示  表示线条描边或填充的方式
        paint.setStrokeWidth(scale);//外边框的大小
        paint.setColor(mBorderColor);//添加外边框


        canvas.drawCircle(getWidth() / 2,getHeight() / 2,r,paint);



    }
}
