package l;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.net.*;
import android.content.res.*;

public class l extends Activity
{
	VideoView v;
    String tv="http://nclive.grtn.cn/zjpd/sd/live.m3u8";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	//getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		v = new VideoView(this);
		v.setVideoURI(Uri.parse(tv));
		v.setKeepScreenOn(true);
		v.start();
		setContentView(v);
	}

	@Override
	protected void onRestart()
	{
		super.onRestart();
		v.start();
	}

}
