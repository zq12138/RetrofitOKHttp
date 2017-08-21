package com.example.admin.retrofitokhttp.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by zq on 2017/8/16.
 */

public class CalendarView extends View {

    public Context context;


    public CalendarView(Context context) {
        super(context);
        this.context = context;
        init();
    }


    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    List<DatePoint> mDatePoints;

    private void init() {
        mCaleandar=Calendar.getInstance();
        mDatePoints = new ArrayList<>();
        mWeekHeader = new String[]{"日", "一", "二", "三", "四", "五", "六"};
        initPaint();
        initDate();
    }

    Calendar mCaleandar;
    public static Calendar copyCalendar(Calendar calendar) {
        Calendar result = Calendar.getInstance();
        result.setTime(calendar.getTime());
        return result;
    }
    /**
     * 获取本页第一个日期
     *
     * @return
     */
    Calendar getViewFirstDay() {
        Calendar calendarDay  =copyCalendar(mCaleandar);//拷贝当前日历;//拷贝当前日历
        calendarDay.roll(Calendar.DAY_OF_YEAR, -(calendarDay.get(Calendar.DAY_OF_MONTH) - 1));//当月第一天
        int dayOfWeek = calendarDay.get(Calendar.DAY_OF_WEEK);
        Log.e("tag",""+dayOfWeek);
        calendarDay.roll(Calendar.DAY_OF_YEAR, -(dayOfWeek - 1));
        return calendarDay;
    }
    public void setDate(int year, int month) {
        mCaleandar.set(Calendar.YEAR, year);
        mCaleandar.set(Calendar.MONTH, month - 1);
        update();
    }

    private void update() {
        initDate();
        invalidate();

    }

    private void initDate() {
        mDatePoints.clear();
        Calendar first = getViewFirstDay();
        for (int i = 0; i < 42; i++) {
            Calendar calendarDay = copyCalendar(first);
            DatePoint temp = new DatePoint(calendarDay, i);
            mDatePoints.add(temp);
            first.roll(Calendar.DAY_OF_YEAR, 1);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void drawWeekHeader(Canvas canvas) {
        int i = 0;
        for (String w : mWeekHeader) {
            drawText(w, (i + 0.5f) * dayWidth, 0.5f * dayHeight, canvas, mPaintWeekHeader);
            i++;
        }
    }

    public static void drawText(String text, float x, float y, Canvas canvas, Paint paint) {

        float halfH = paint.measureText("A", 0, 1) / 2;
        float halfW = paint.measureText(text) / 2;
        canvas.drawText(text, x - halfW, y + halfH, paint);
    }

    float dayWidth, dayHeight;
    Paint mPaintWeekHeader;
    String[] mWeekHeader;

    public void initPaint() {
        mPaintWeekHeader = new Paint();
        mPaintWeekHeader.setAntiAlias(true);
        mPaintWeekHeader.setColor(Color.parseColor("#000000"));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dayWidth = w / 7;
        dayHeight = h / 7;
        mPaintWeekHeader.setTextSize(Math.min(dayWidth, dayHeight) / 3);
    }

    class DatePoint {
        Calendar mCalendar;

        public Calendar getmCalendar() {
            return mCalendar;
        }

        int position;

        DatePoint(Calendar mCalendar, int position) {
            this.mCalendar = mCalendar;
            this.position = position;
        }



        public float getX() {
            return ((position % 7 + 0.5f) * dayWidth);
        }

        public float getY() {
            return ((position / 7 + 0.5f) * dayHeight);
        }

        public void onDraw(Canvas canvas) {
            drawText(getDay(this.mCalendar)+"", getX(), getY()+dayHeight, canvas, mPaintWeekHeader);
        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWeekHeader(canvas);
        for (DatePoint dateP : mDatePoints) {

//            dateP.onDraw(canvas);
            drawText(getDay(dateP.getmCalendar())+"", dateP.getX(), dateP.getY()+dayHeight, canvas, mPaintWeekHeader);
        }
    }

    public int getDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
