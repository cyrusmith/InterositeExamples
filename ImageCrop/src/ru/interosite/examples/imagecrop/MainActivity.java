package ru.interosite.examples.imagecrop;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity
{

	private class LoadImagesTask extends AsyncTask<Void, Void, Bitmap>
	{

		@Override
		protected Bitmap doInBackground(Void... params)
		{
			return BitmapUtils.scaleTo(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.coolphoto), 50);
		}

		@Override
		protected void onPostExecute(Bitmap result)
		{
			if (isCancelled() || result == null)
			{
				return;
			}
			image1.setImageBitmap(result);
		}

	}

	private LoadImagesTask imagesTask = null;
	private ImageView image1 = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image1 = (ImageView) findViewById(R.id.image1);
	}

	@Override
	protected void onStart()
	{
		super.onStart();
		imagesTask = new LoadImagesTask();
		imagesTask.execute();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		imagesTask.cancel(true);
	}
}
