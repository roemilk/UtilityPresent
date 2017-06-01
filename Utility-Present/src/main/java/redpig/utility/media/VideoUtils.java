package redpig.utility.media;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.util.Log;

public class VideoUtils {

	
	/**
	 * 비디오 썸네일 이미지 가져오기
	 * 
	 * @param id
	 *            비디오 아이디
	 */
	public static Bitmap GetVideoThumnailImg(Context context, long id, String name, int size) {
		ContentResolver mCrThumb = context.getContentResolver();
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 1;

		// MICRO_KIND :작은이미지(정사각형) MINI_KIND (중간이미지)
		Bitmap mVideoThumnailBm = MediaStore.Video.Thumbnails.getThumbnail(mCrThumb, id, size, options);
		
		if (mVideoThumnailBm != null) {
			Log.i("ThumbNail Bitmap", "ThumbNailBitmap width : " + mVideoThumnailBm.getWidth() + " height : " + mVideoThumnailBm.getHeight());
		}else {
			Log.i("ThumNail Bitmap", "비트맵 이미지가 없습니다.");
		}
		
		mCrThumb = null;
		options = null;

		return mVideoThumnailBm;
	}
}
