package com.gbhargavv.bordered_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

public class BorderedTextView extends AppCompatTextView {
    public enum TextAlign {
        top, bottom, left, right, centerVertical, centerHorizontal
    }

    private Rect bounds;
    private Paint borderPainter, insetPainter;
    private Typeface tf;
    private int strokeWidth = 0;
    private String currStr = "";
    // Default colors, black boarder/white text inset
    private int borderColor = 0x00000000;
    private int insetColor = 0x00FFFFFF;
    private float originx, originy = -1;
    private int alignHori, alignVert = 0;

    public BorderedTextView(Context context) {
        super(context);
        initDefaults();
        if (!getText().toString().isEmpty()) {
            currStr = getText().toString();
        }
    }

    public BorderedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaults();
        if (!getText().toString().isEmpty()) {
            currStr = getText().toString();
        }
    }

    public BorderedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaults();
        if (!getText().toString().isEmpty()) {
            currStr = getText().toString();
        }
    }

    protected void initDefaults() {
        tf = Typeface.createFromAsset(getContext().getAssets(), "Mont-Bold.ttf");
        borderPainter = new Paint();
        insetPainter = new Paint();

        borderPainter.setDither(true);
        insetPainter.setDither(true);

        // Default:
        // borderColor = Black
        // insetColor = White
        borderPainter.setColor(borderColor);
        insetPainter.setColor(insetColor);

        // Default: Centered text alignment
        borderPainter.setTextAlign(Paint.Align.CENTER);
        insetPainter.setTextAlign(Paint.Align.CENTER);

        // Default Typeface/Font:
        // https://en.wikipedia.org/wiki/Helvetica#Neue_Helvetica_.281983.29
        borderPainter.setTypeface(tf);
        insetPainter.setTypeface(tf);

        borderPainter.setFlags(Paint.ANTI_ALIAS_FLAG);
        insetPainter.setFlags(Paint.ANTI_ALIAS_FLAG);

        insetPainter.setTextSize(getTextSize());
        borderPainter.setTextSize(getTextSize());

        // Style set to stroke to distinguish
        borderPainter.setStyle(Paint.Style.STROKE);

        borderPainter.setStrokeJoin(Paint.Join.ROUND);
        borderPainter.setStrokeCap(Paint.Cap.ROUND);
        // Default stroke width: 5
        borderPainter.setStrokeWidth(strokeWidth);
        bounds = new Rect();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        currStr = getText().toString();
        if (!currStr.isEmpty()) {
            borderPainter.getTextBounds(currStr, 0, currStr.length(), bounds);
            if (alignHori == 0)
                originx = this.getWidth() / 2;
            else if (alignHori == 1)
                originx = bounds.width() / 2;
            else if (alignHori == 2)
                originx = (this.getWidth() - (bounds.width() / 2));
            if (alignVert == 0)
                originy = bounds.height() + ((this.getHeight() - bounds.height()) / 2);
            else if (alignVert == 1)
                originy = bounds.height();
            else if (alignVert == 2)
                originy = this.getHeight();
            canvas.drawText(currStr, originx, originy, insetPainter);
            canvas.drawText(currStr, originx, originy, borderPainter);
        }
    }

    public void setBorderedColor(int color) {
        borderPainter.setColor(color);
        invalidate();
    }

    public void setInsetColor(int color) {
        insetPainter.setColor(color);
        invalidate();
    }

    public void setBorderWidth(int width) {
        borderPainter.setStrokeWidth(width);
    }

    public void setTextSizes(float size) {
        insetPainter.setTextSize(size * getResources().getDisplayMetrics().density);
        borderPainter.setTextSize(size * getResources().getDisplayMetrics().density);
        invalidate();
    }

    public void setTextAlign(TextAlign textAlign) {
        switch (textAlign) {
            case top:
                alignVert = 1;
                break;
            case bottom:
                alignVert = 2;
                break;
            case left:
                alignHori = 1;
                break;
            case right:
                alignHori = 2;
                break;
            case centerHorizontal:
                alignHori = 0;
                break;
            case centerVertical:
                alignVert = 0;
                break;
        }
    }

    public void setBorderTypeface(Typeface tf) {
        borderPainter.setTypeface(tf);
        insetPainter.setTypeface(tf);
        invalidate();
    }
}
