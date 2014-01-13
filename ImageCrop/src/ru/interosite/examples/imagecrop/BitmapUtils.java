package ru.interosite.examples.imagecrop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class BitmapUtils
{
	private BitmapUtils()
	{}

	/**
	 * Scales source bitmap so that it fit into the square of given size If
	 * source is smaller that target square then the result will stretch to have
	 * the size equal to the target
	 * 
	 * @param source
	 *            bitmap to be scaled
	 * @param size
	 *            width and height of target bounding box
	 * @return resulting bitmap
	 */
	public static Bitmap scaleTo(Bitmap source, int size)
	{
		int destWidth = source.getWidth();

		int destHeight = source.getHeight();

		destHeight = destHeight * size / destWidth;
		destWidth = size;

		if (destHeight < size)
		{
			destWidth = destWidth * size / destHeight;
			destHeight = size;
		}

		Bitmap destBitmap = Bitmap.createBitmap(destWidth, destHeight, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(destBitmap);
		canvas.drawBitmap(source, new Rect(0, 0, source.getWidth(), source.getHeight()), new Rect(0, 0, destWidth, destHeight), new Paint(Paint.ANTI_ALIAS_FLAG));
		return destBitmap;
	}
}
