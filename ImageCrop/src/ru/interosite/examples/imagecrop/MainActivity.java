package ru.interosite.examples.imagecrop;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity
{

	private class LoadImagesTask extends AsyncTask<Void, Void, List<Bitmap>>
	{

		@Override
		protected List<Bitmap> doInBackground(Void... params)
		{
			List<Bitmap> bitmaps = new ArrayList<Bitmap>();
			bitmaps.add(BitmapUtils.scaleTo(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.coolphoto), 50));
			bitmaps.add(BitmapUtils.getCircleMaskedBitmapUsingClip(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable.coolphoto), 25));
			return bitmaps;
		}

		@Override
		protected void onPostExecute(List<Bitmap> result)
		{
			if (isCancelled() || result == null)
			{
				return;
			}
			image1.setImageBitmap(result.get(0));
			image2.setImageBitmap(result.get(1));
		}

	}

	private LoadImagesTask imagesTask = null;
	private ImageView image1 = null;
	private ImageView image2 = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image1 = (ImageView) findViewById(R.id.image1);
		image2 = (ImageView) findViewById(R.id.image2);
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
