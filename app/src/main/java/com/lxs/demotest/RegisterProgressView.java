package com.lxs.demotest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 实现功能：
 * Created by lvxinsheng on 2018/12/26 上午11:05
 */
public class RegisterProgressView extends View {

    private static final int DEFAULT_PROGRESS_HEIGHT = 3;
    private static final int DEFAULT_BACKGROUND_HEIGHT = 6;
    private static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#EBEBEB");
    public static final int DEFAULT_PROGRESS_COLOR = Color.parseColor("#FE803E");
    private static final int DEFAULT_INNER_DOT_COLOR = Color.WHITE;
    private static final int DEFAULT_INNER_RADIUS = 3;
    private static final int DEFAULT_OUTTER_RADIUS = 6;
    private static final int DEFAULT_INDICATOR_WANT_WIDTH = 14;
    private static final int DEFAULT_INDICATOR_WANT_HEIGHT = 10;

    private Bitmap mEndBitmap;

    private Paint mBitmapPaint;
    private Paint mPathPaint;

    private float mPathStartY;
    private float mPathEndX;

    private float mProgressHeight;
    private float mBackgroundHeight;
    private int mBackgroundColor;
    private int mProgressColor;
    private int mInnerDotColor;
    private float mInnerRadius;
    private float mOuterRadius;
    // 共几步
    private int mAllStep;
    // 第几步
    private int mStep;
    private float mProgress;

    // 券图标期望宽度
    private int mIndicatorWantWidth;
    private int mIndicatorWantHeight;

    private ValueAnimator mValueAnimator;
    private int mLastStep;

    public RegisterProgressView(Context context) {
        this(context, null);
    }

    public RegisterProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RegisterProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * parse the typedarray and init necessary properties
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RegisterProgressView);

            mProgressHeight = typedArray.getDimension(R.styleable.RegisterProgressView_register_progress_view_progress_height, dip2px(DEFAULT_PROGRESS_HEIGHT));
            mProgressColor = typedArray.getColor(R.styleable.RegisterProgressView_register_progress_view_progress_color, DEFAULT_PROGRESS_COLOR);
            mBackgroundHeight = typedArray.getDimension(R.styleable.RegisterProgressView_register_progress_view_background_height, dip2px(DEFAULT_BACKGROUND_HEIGHT));
            mBackgroundColor = typedArray.getColor(R.styleable.RegisterProgressView_register_progress_view_background_color, DEFAULT_BACKGROUND_COLOR);
            mInnerDotColor = typedArray.getColor(R.styleable.RegisterProgressView_register_progress_view_inner_dot_color, DEFAULT_INNER_DOT_COLOR);
            mInnerRadius = typedArray.getDimension(R.styleable.RegisterProgressView_register_progress_view_inner_radius, dip2px(DEFAULT_INNER_RADIUS));
            mOuterRadius = typedArray.getDimension(R.styleable.RegisterProgressView_register_progress_view_outer_radius, dip2px(DEFAULT_OUTTER_RADIUS));
            mAllStep = typedArray.getInteger(R.styleable.RegisterProgressView_register_progress_view_all_step, 1);
            mStep = typedArray.getInteger(R.styleable.RegisterProgressView_register_progress_view_step, 0);
            mProgress = 100f * mStep / mAllStep;
            int indicatorIcon = typedArray.getResourceId(R.styleable.RegisterProgressView_register_progress_view_indicator_icon, -1);
            mIndicatorWantWidth = typedArray.getInteger(R.styleable.RegisterProgressView_register_progress_view_indicator_width, dip2px(DEFAULT_INDICATOR_WANT_WIDTH));
            mIndicatorWantHeight = typedArray.getInteger(R.styleable.RegisterProgressView_register_progress_view_indicator_height, dip2px(DEFAULT_INDICATOR_WANT_HEIGHT));

            typedArray.recycle();

            mEndBitmap = BitmapFactory.decodeResource(getResources(), indicatorIcon);

            mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBitmapPaint.setFilterBitmap(true);
            mBitmapPaint.setDither(true);

            mPathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPathPaint.setPathEffect(new CornerPathEffect(mProgressHeight));
            mPathPaint.setStrokeWidth(mProgressHeight);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPathStartY = getHeight() - mOuterRadius;
        mPathEndX = getWidth() - (mEndBitmap == null ? mOuterRadius : mIndicatorWantWidth / 2f);

        // 1.draw background
        drawBackground(canvas);

        // 2.draw progress
        drawProgress(canvas);

        // 3.draw start
        drawStartIcon(canvas);

        // 4.draw step
        drawStepIcon(canvas);

        // 5.draw end
        drawEndIcon(canvas);

        // 6.draw indicator
        drawIndicator(canvas);
    }

    private void drawIndicator(Canvas canvas) {
        if (mEndBitmap == null) return;
        Rect src = new Rect(0, 0, mEndBitmap.getWidth(), mEndBitmap.getHeight());
        RectF dest = new RectF(getWidth() - mIndicatorWantWidth, mPathStartY - mIndicatorWantHeight / 2f, getWidth(), mPathStartY + mIndicatorWantHeight / 2f);
        canvas.drawBitmap(mEndBitmap, src, dest, mBitmapPaint);
    }

    private void drawEndIcon(Canvas canvas) {
        if (mProgress >= 100) {
            mPathPaint.setColor(mProgressColor);
            canvas.drawCircle(mPathEndX, mPathStartY, mOuterRadius, mPathPaint);
        } else {
            mPathPaint.setColor(mBackgroundColor);
            canvas.drawCircle(mPathEndX, mPathStartY, mOuterRadius, mPathPaint);
        }

        mPathPaint.setColor(mInnerDotColor);
        canvas.drawCircle(mPathEndX, mPathStartY, mInnerRadius, mPathPaint);
    }

    private void drawStepIcon(Canvas canvas) {
        for (int i = 0; i < mAllStep - 1; i++) {
            float centerX = (mPathEndX - mOuterRadius) * (float) (i + 1) / mAllStep;
            if (i + 1 <= mStep) {
                mPathPaint.setColor(mProgressColor);
            } else {
                mPathPaint.setColor(mBackgroundColor);
            }
            canvas.drawCircle(centerX, mPathStartY, mOuterRadius, mPathPaint);

            mPathPaint.setColor(mInnerDotColor);
            canvas.drawCircle(centerX, mPathStartY, mInnerRadius, mPathPaint);
        }
    }

    private void drawStartIcon(Canvas canvas) {
        mPathPaint.setColor(mProgressColor);
        canvas.drawCircle(mOuterRadius, mPathStartY, mOuterRadius, mPathPaint);

        mPathPaint.setColor(mInnerDotColor);
        canvas.drawCircle(mOuterRadius, mPathStartY, mInnerRadius, mPathPaint);
    }

    private void drawProgress(Canvas canvas) {
        float stopX = (mPathEndX - mOuterRadius) * mProgress / 100.0f;
        mPathPaint.setColor(mProgressColor);
        mPathPaint.setStrokeWidth(mProgressHeight);
        canvas.drawLine(mOuterRadius, mPathStartY, stopX, mPathStartY, mPathPaint);
    }

    private void drawBackground(Canvas canvas) {
        mPathPaint.setColor(mBackgroundColor);
        mPathPaint.setStrokeWidth(mBackgroundHeight);
        canvas.drawLine(mOuterRadius / 2, mPathStartY, mPathEndX, mPathStartY, mPathPaint);
    }

    /**
     * 设置券图标
     *
     * @param endBitmap
     */
    public void setEndBitmap(Bitmap endBitmap) {
        this.mEndBitmap = endBitmap;
        invalidate();
    }

    /**
     * 设置券图标
     *
     * @param resId
     */
    public void setEndBitmap(@DrawableRes int resId) {
        setEndBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }

    /**
     * 设置进度条颜色
     *
     * @param progressColor
     */
    public void setProgressColor(int progressColor) {
        this.mProgressColor = progressColor;
        invalidate();
    }

    /**
     * DP 2 PX
     *
     * @param dpValue
     * @return
     */
    private int dip2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * set progress
     *
     * @param progress
     */
    private void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }

    /**
     * step enter which step
     *
     * @param step
     */
    public void setStep(int step) {
        this.mStep = step;
        setProgress(100f * step / mAllStep);
    }


    /**
     * set enter which step by Animator effect
     *
     * @param step
     */
    public void setStepByAnimator(final int step) {
        if (mLastStep == step) {
            return;
        }
        mLastStep = step;

        if (mValueAnimator != null && mValueAnimator.isRunning()) {
            mValueAnimator.cancel();
        }

        mValueAnimator = ValueAnimator.ofFloat(this.mProgress, 100f * step / mAllStep);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float curValue = (float) animation.getAnimatedValue();
                setProgress(curValue);
            }
        });
        mValueAnimator.setDuration(500);
        mValueAnimator.start();

        if (step < mStep) {
            setStep(step);
        } else {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    setStep(step);
                }
            }, 500);
        }

    }
}
