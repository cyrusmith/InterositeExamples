package ru.interosite.examples.imagecrop;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
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

	/**
	 * Creates image masked by circle using clip path technique
	 * 
	 * @param source
	 *            Source bitmap
	 * @param radius
	 *            Target circle radius
	 * @return Resulting bitmap
	 */
	public static Bitmap getCircleMaskedBitmapUsingClip(Bitmap source, int radius)
	{
		if (source == null)
		{
			return null;
		}

		int diam = radius << 1;

		Bitmap scaledBitmap = scaleTo(source, diam);

		final Path path = new Path();
		path.addCircle(radius, radius, radius, Path.Direction.CCW);

		Bitmap targetBitmap = Bitmap.createBitmap(diam, diam, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(targetBitmap);
		
		canvas.clipPath(path);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

		canvas.drawBitmap(scaledBitmap, 0, 0, paint);
		
		return targetBitmap;
	}
}
