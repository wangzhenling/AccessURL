package com.example.accessurl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView myImg;
	private Handler myHandler;
	private Bitmap bitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myImg=(ImageView) this.findViewById(R.id.myImg);
		myHandler=new Handler(){
			public void handleMessage(Message msg){
				if(msg.what==0x1122){
					myImg.setImageBitmap(bitmap);
				}
			}
		};
		new Thread(){
			public void run(){
				try {
					URL url=new URL("http://www.baidu.com/" +
							"img/baidu_sylogo1.gif");//获取百度首页图片
					InputStream is=url.openStream();
					bitmap=BitmapFactory.decodeStream(is);		
                    is.close();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				myHandler.sendEmptyMessage(0x1122);
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
