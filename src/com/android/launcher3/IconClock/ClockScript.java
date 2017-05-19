
package com.android.launcher3.IconClock;
import com.android.launcher3.FastBitmapDrawable;
import android.graphics.Paint;
import com.android.launcher3.*;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import android.view.View;
import android.text.format.Time;
import android.graphics.Color;
import android.graphics.Canvas;
import android.util.Log;
import android.graphics.Bitmap;
import android.content.Context;
import com.android.launcher3.Utilities;
import  com.android.launcher3.IconClock.*;
public class ClockScript extends IconScript {  
    Rect mRect = null;  
	Context mcontext=null;
    /** 
     * Ч��չʾĿ��View 
     */  
    private View mView;  
    /** 
     * ֪ͨϵͳ������ͼ�ֳ� 
     */  
    private ClockThread mClockThread = null;  
    /** 
     * ��ǰ�Ƿ���ʾ����Ļ�� 
     */  
    private boolean mIsShowInScreen = false;  
      
    public ClockScript(){  
        super();  
    }  
	public ClockScript(Context context){  
	mcontext=context;
	}
    public void run(View view) { 

		
        mView = view;  
        mRect = getBounds();  
        if(mClockThread == null){  
            mClockThread = new ClockThread();  
            mClockThread.start();  
        }  
    }  
      
    @Override  
    public void onPause() {  
        mClockThread.pauseRun();  
        super.onPause();  
    }  
      
    @Override  
    public void onResume() {  
        mClockThread.resumeRun();  
        super.onResume();  
    }  
      
    @Override  
    public void onStop() {  
        mClockThread.stopRun();  
        super.onStop();  
    }  
      
  
    @Override  
    public void draw(Canvas canvas) {  
	
  //Bitmap calendarIcon = Utilities.createIconBitmap(mcontext.getResources().getDrawable(R.drawable.ic_launcher_alarmclock),mcontext);
 // canvas.setBitmap(calendarIcon);

    Log.d("lihuachun","ClockScript:draw before");
        super.draw(canvas);  
        mIsShowInScreen = true;  
//      mPaint.setColor(Color.WHITE);  
//      canvas.drawCircle(mRect.centerX(), mRect.centerY(), mRect.height()/2, mPaint);  
//      mPaint.setColor(Color.BLACK);  
//      canvas.drawLine(mRect.centerX(), 0, mRect.centerX(), 10, mPaint);  
//      canvas.drawLine(mRect.centerX(), mRect.height(), mRect.centerX(), mRect.height()-10, mPaint);  
//      canvas.drawLine(mRect.left, mRect.centerY(), mRect.left + 10, mRect.centerY(), mPaint);  
//      canvas.drawLine(mRect.right, mRect.centerY(), mRect.right-10, mRect.centerY(), mPaint);  
        drawIndicator(canvas,mRect.centerX(),mRect.centerY()+60,mPaint);  
//      mPaint.setColor(Color.MAGENTA);  
        canvas.drawCircle(mRect.centerX(), mRect.centerY(), 0, mPaint);  
          
        if(mClockThread.wait){  
            mClockThread.resumeRun();  
        }  
		Log.d("lihuachun","ClockScript:draw after");
    }  
    /** 
     * ��ָ�� 
     * @param canvas 
     * @param centerX 
     * @param centerY 
     * @param p 
     */  
    private void drawIndicator(Canvas canvas,int centerX,int centerY,Paint p){  
          Log.d("lihuachun","ClockScript:drawIndicator before");
        Time t=new Time();  
        t.setToNow();  
        p.setStrokeWidth(2);  
        p.setColor(Color.BLACK);  
        //ʱ��  
        canvas.drawLine(centerX, centerY,   
                (int)(centerX + (mRect.width()/2-35) * Math.cos((t.hour+(float)t.minute/60) * (Math.PI / 6) - Math.PI / 2)),   
                (int)(centerY + (mRect.width()/2-35) * Math.sin((t.hour+(float)t.minute/60) * (Math.PI / 6) - Math.PI / 2)), p);  
        canvas.drawLine(centerX, centerY,   
                (int)(centerX + (mRect.width()/2-50) * Math.cos((t.hour+(float)t.minute/60) * (Math.PI / 6) + Math.PI / 2)),   
                (int)(centerY + (mRect.width()/2-50) * Math.sin((t.hour+(float)t.minute/60) * (Math.PI / 6) + Math.PI / 2)), p);  
    p.setColor(Color.GREEN);  
        //����  
        canvas.drawLine(centerX, centerY,  
                (int)(centerX + (mRect.width()/2-27) * Math.cos(t.minute * (Math.PI / 30) - Math.PI / 2)),  
                (int)(centerY + (mRect.width()/2-27) * Math.sin(t.minute * (Math.PI / 30) - Math.PI / 2)),p);  
        canvas.drawLine(centerX, centerY,  
                (int)(centerX + (mRect.width()/2-48) * Math.cos(t.minute * (Math.PI / 30) + Math.PI / 2)),  
                (int)(centerY + (mRect.width()/2-48) * Math.sin(t.minute * (Math.PI / 30) + Math.PI / 2)),p);  
        p.setColor(Color.RED);  
        //����  
        canvas.drawLine(centerX, centerY,  
                (int)(centerX + (mRect.width()/2-24) * Math.cos(t.second * (Math.PI / 30) - Math.PI / 2)),  
                (int)(centerY + (mRect.width()/2-24) * Math.sin(t.second * (Math.PI / 30) - Math.PI / 2)),p);  
        p.setStrokeWidth(3);  
        canvas.drawLine(centerX, centerY,  
                (int)(centerX + (mRect.width()/2-45) * Math.cos(t.second * (Math.PI / 30) + Math.PI/2)),  
                (int)(centerY + (mRect.width()/2-45) * Math.sin(t.second * (Math.PI / 30) + Math.PI/2)),p);  
  Log.d("lihuachun","ClockScript:drawIndicator after");
    }  
      
    class ClockThread extends Thread {  
        int times = 0;  
        boolean running = true;  
  
        public boolean wait = false;  
  
        public void stopRun() {  
            running = false;  
            synchronized (this) {  
                this.notify();  
            }  
        };  
  
        public void pauseRun() {  
            this.wait = true;  
            synchronized (this) {  
                this.notify();  
            }  
        }  
  
        public void resumeRun() {  
            this.wait = false;  
            synchronized (this) {  
                this.notify();  
            }  
        }  
  
        public void run() {  
            while (running) {  
				  Log.d("lihuachun","ClockScript:ClockThread running before");
                synchronized (mView) {  
                    mView.postInvalidate();  
				//LauncherAppState.getInstance().getModel().forceReload();
                }  
                  
                if(!mIsShowInScreen){  
                    pauseRun();  
                }  
                mIsShowInScreen = false;  
                try {  
                    Thread.sleep(500);  
                } catch (Exception e) {  
                    System.out.println(e);  
                }  
                  
                synchronized (this) {  
                    if (wait) {  
                        try {  
                            wait();  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                    }  
                }  
            }  
        }  
//  Log.d("lihuachun","ClockScript:ClockThread running after");
	}  
  
}  