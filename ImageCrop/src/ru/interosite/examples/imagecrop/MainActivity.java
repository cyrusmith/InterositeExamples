package ru.interosite.examples.imagecrop;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends Activity
{

	private class LoadImagesTask extends AsyncTask<Void, Integer, Void>
	{

		@Override
		protected Void doInBackground(Void... params)
		{

			return null;
		}

	}

	private LoadImagesTask imagesTask = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
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
