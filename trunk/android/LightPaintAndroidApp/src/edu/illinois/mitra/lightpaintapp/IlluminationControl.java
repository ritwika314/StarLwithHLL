package edu.illinois.mitra.lightpaintapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import edu.illinois.mitra.lightpaint.utility.Utility;

public class IlluminationControl extends View {

	private static final int DIMENSION = 400;

	private boolean displayX = false;
	private static final Paint xPaint = new Paint();
	
	static {
		xPaint.setColor(Color.RED);
		xPaint.setStrokeWidth(10);
	}
	
	public IlluminationControl(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public IlluminationControl(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public IlluminationControl(Context context) {
		super(context);
		init();
	}
	
	private int centerX, centerY;
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(DIMENSION, DIMENSION);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		centerX = (int) (getWidth()/2.0);
		centerY = (int) (getHeight()/2.0);
	}

	private Paint paint;
	private float width = 1f;
	
	private void init() {
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
	}
	
	public void setColor(int color) {
		color |= 0xff000000; // Set to 100% opacity
		paint.setColor(color);
		this.invalidate();
	}
	
	public void setWidth(float width) {
		this.width = Utility.cap(width, 0f,1f);
		this.invalidate();
	}
	
	public void setX(boolean xOn) {
		displayX = xOn;
		this.invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawCircle(centerX, centerY, width*DIMENSION/2, paint);
		if(displayX) {
			canvas.drawLine(0, 0, DIMENSION, DIMENSION, xPaint);
			canvas.drawLine(DIMENSION, 0, 0, DIMENSION, xPaint);
		}
			
	}
}
