package com.zou.fund.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

/**
 * Created by 邹远君 on 2018/2/25 0025.
 * 根据涨幅正还是负设置字体颜色
 */

public class RateTextView extends android.support.v7.widget.AppCompatTextView{

    String string="---";
    Paint paint=new Paint();

    Paint paint1=new Paint();

    public RateTextView(Context context) {
        super(context);
    }

    public RateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setdata(String s){
        this.string=s;
        paint.setColor(Color.RED);
        paint.setTextSize(40);
        paint.setAntiAlias(true);
        paint1.setColor(Color.GREEN);
        paint1.setTextSize(40);
        paint1.setAntiAlias(true);
        draw(new Canvas());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec) ;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec) ;

        //1.如果在布局中你设置文字的宽高是固定值[如100dp、200dp]，就不需要计算, 直接获取宽和高就可以
        int width = MeasureSpec.getSize(widthMeasureSpec);

        //1.如果在布局中你设置文字的宽高是wrap_content[对应MeasureSpec.AT_MOST] , 则需要使用模式来计算
        if (widthMode == MeasureSpec.AT_MOST){
            //计算的宽度 与字体的大小和长度有关 用画笔来测量
            Rect bounds = new Rect() ;
            //获取文本的Rect [区域]
            //参数一：要测量的文字、参数二：从位置0开始、参数三：到文字的长度、参数四：
            try {
                paint.getTextBounds(string , 0 , string.length() , bounds);
            }catch (NullPointerException e){
                paint.getTextBounds(string , 0 , 10 , bounds);
            }


            //文字的宽度
            width = bounds.width() ;
        }

        int height = MeasureSpec.getSize(heightMeasureSpec);
        //1.如果在布局中你设置文字的宽高是wrap_content[对应MeasureSpec.AT_MOST] , 则需要使用模式来计算
        if (heightMode == MeasureSpec.AT_MOST){
            //计算的宽度 与字体的大小和长度有关 用画笔来测量
            Rect bounds = new Rect() ;
            //获取文本的Rect [区域]
            //参数一：要测量的文字、参数二：从位置0开始、参数三：到文字的长度、参数四：
            paint.getTextBounds(string , 0 , string.length() , bounds);
            //文字的高度
            height = bounds.height() ;
        }
        //设置文字控件的宽和高
        setMeasuredDimension(width , height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //dy: 代表的是：高度的一半到baseLine的距离
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt() ;
        //top是负值 bottom是正值   bottom代表的是baseLine到文字底部的距离
        int dy = (fontMetrics.bottom - fontMetrics.top) /2 - fontMetrics.bottom ;

        int baseLine = getHeight() /2 + dy ;

        int x = getPaddingLeft() ;
        try {
            double d=Double.parseDouble(string);
            if (d>0){
                canvas.drawText(string+"%",x,baseLine,paint);
            }else  {
                canvas.drawText(string+"%",x,baseLine,paint1);
            }
        }catch (NumberFormatException e){
            canvas.drawText(string+"%",x,baseLine,paint1);
        };
    }
}
