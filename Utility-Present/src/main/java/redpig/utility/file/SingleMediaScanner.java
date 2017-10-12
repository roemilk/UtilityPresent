/**
 *
 */
package redpig.utility.file;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @작성자 KimYoungHun
 * @날짜 : 2014. 7. 21.
 * @설명 : 미디어 스캐닝을 실시한다.
 * 개별적으로 외부 패키지에서 인스턴스를 생성할수 없으며 MemoryUtils 클래스의 미디어스캐닝 메서드를 통해서 생성된다.
 */
class SingleMediaScanner implements MediaScannerConnectionClient {

	private MediaScannerConnection mMs;
	private File mFile;

	/**
	 *
	 * @param context 컨텍스트
	 * @param path 미디어 스캐닝 대상 폴더
	 */
	public SingleMediaScanner(Context context, File path) {
		// TODO Auto-generated constructor stub
		this.mFile = path;
		this.mMs = new MediaScannerConnection(context, this);
		mMs.connect();
	}

	/* (non-Javadoc)
	 * @see android.media.MediaScannerConnection.MediaScannerConnectionClient#onMediaScannerConnected()
	 */
	@Override
	public void onMediaScannerConnected() {
		// TODO Auto-generated method stub
		File[] fileNames = mFile.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				// TODO Auto-generated method stub
				return filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".bmp") || filename.endsWith(".mp4") || filename.endsWith(".mov") || filename.endsWith(".avi"); //대상 확장자 명
			}
		});

		if(fileNames != null){
			for(File file:fileNames) {
				String fileName = file.getName();

				Log.i("FILE_NAME", fileName);
				mMs.scanFile(file.getAbsolutePath(), null);
			}
		}
	}

	/* (non-Javadoc)
	 * @see android.media.MediaScannerConnection.MediaScannerConnectionClient#onScanCompleted(java.lang.String, android.net.Uri)
	 */
	@Override
	public void onScanCompleted(String path, Uri uri) {
		// TODO Auto-generated method stub
		mMs.disconnect();
		Log.i("MEDIA_SCANNING", "" + path + "," + uri.toString());
	}
}
