package l;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.net.*;
import java.io.*;
import android.graphics.*;
import android.media.*;

public class l extends Activity implements MediaPlayer.OnInfoListener
{
	VideoView v;
	ProgressBar pb;
	FrameLayout f;
	FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(-1, -1,17);
	
	Uri tv;
    String s;
	String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		//getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		
		pm();
		//tv="http://nclive.grtn.cn/zjpd/sd/live.m3u8";
		a();
		//tv=a("tv.txt");
		
		v = new VideoView(this);
		v.setVideoURI(tv);
		if(Build.VERSION.SDK_INT>=17)
		v.setOnInfoListener(this);
		//v.setKeepScreenOn(true);
		v.start();
		
		pb=new ProgressBar(this);
		//FrameLayout.LayoutParams pp=new FrameLayout.LayoutParams(100, 100,17);
		//pb.setLayoutParams(pp);
		//pb.setVisibility(View.GONE);
		
		f=new FrameLayout(this);
		f.addView(v,lp);
		f.addView(pb);
		
		addContentView(f,lp);
	}

	private void pm()
	{
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) 
            if (checkSelfPermission(permissions[0]) != 0)
                requestPermissions(permissions, 321);
	}

	void a()
	{
		s=f("/sdcard/djwdj-tv.txt");
		if(s.equals(""))
		{
			s=a("djwdj-tv.txt");
			if(s.equals(""))
			{
				s="http://nclive.grtn.cn/zjpd/sd/live.m3u8";
			}
		}
		tv = Uri.parse( s.trim());
		
	}

	String f(String s)
	{
		byte[] b=new byte[1024];
		try
		{
			FileInputStream is=new FileInputStream(s);
			s = "";
			int i = 0;
			while ((i = is.read(b)) >0)
				s += new String(b,0,i);
		}
		catch (Exception e)
		{
			s="";
		}
		return s;
	}
	
	String a(String s)
	{
		byte[] b=new byte[1024];
		try
		{
			InputStream is=getAssets().open(s);
			s = "";
			int i = 0;
			while ((i = is.read(b)) >0)
				s += new String(b,0,i);
//			BufferedReader r = new BufferedReader(new InputStreamReader(getAssets().open(s)));
//			String l="";
//			s="";
//			while ((l = r.readLine()) != null)
//				s += l;
		}
		catch (Exception e)
		{
			s="";
		}
		return s;
	}

	@Override
	public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra)
	{
		if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START)
		{
			pb.setVisibility(View.VISIBLE);
		}
		else
		{
			pb.setVisibility(View.INVISIBLE);
		}
		return true;
	}

	
	@Override
	protected void onRestart()
	{
		super.onRestart();
		v.start();
	}

}
