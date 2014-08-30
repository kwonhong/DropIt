package com.example.winterproject.Classes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class ProgressWheel extends View {

	private Paint outerBoundColor;
	private Paint innerBoundColor;
	private Canvas mCanvas;
	private RectF rect1;
	private int angle=0;
	
	public ProgressWheel(Context context) {
		super(context);
		
		outerBoundColor = new Paint();
		innerBoundColor = new Paint();
		innerBoundColor.setColor(Color.WHITE);
		outerBoundColor.setColor(Color.RED);
		rect1 = new RectF(100,400,300,600);
		drawAnimation();
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mCanvas = canvas;
		mCanvas.drawCircle(100, 100, 100, outerBoundColor);
		mCanvas.drawArc(rect1, 0, angle, true, innerBoundColor);
		
			
	}
	
	private void drawAnimation() {
		
			new Thread(new Runnable() {
			Canvas c;	
			public void run() {
				while (angle <= 360) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				angle += 30;
				draw(c);
				
				}
			}
		}).start();
	}

	
	
}
