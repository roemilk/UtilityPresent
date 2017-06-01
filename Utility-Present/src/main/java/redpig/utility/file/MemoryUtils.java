package redpig.utility.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.text.DecimalFormat;

public class MemoryUtils {
	private static final String TAG = "MemoryUtils";

	/**
		 * 
		 * 요약 : SD카드 유무를 확인합니다. 
		 * @return true, false
		 */
	    public static boolean isSDCardAvailable() {
	        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	    }

	    /**
	     * 
	     * 요약 : 해당 경로의 폴더를 생성합니다.
	     * @param sDir 폴더 Path (Root Path는 StoragePath class 참고)
	     * @return 성공 여부 리턴
	     */
	    public static boolean makeDir(String sDir) {
	        if (isSDCardAvailable()) {
	            // SD카드가 존재합니다.
	            File path = new File(sDir);
	            if (!path.exists()) {
	                // 디렉토리가 없음, 새로 생성함.
	                path.mkdir();
	                return true;
	            }else {
	            	return true;
	            }
	        }else {
	        	return false;
	        }
	    }
	    
	    /**
	     * 
	     * 요약 : 대상 폴더에 미디어 스캐닝을 실시한다.
	     */
	    public static void runMediaScannig(Context context, String pPath) {
			/** 이미지 파일 다운로드 후 미디어 스캐닝 하기 **/
			File path = new File(pPath);
			new SingleMediaScanner(context, path);
	    }
	    
	    /**
	     * 
	     * 요약 : 바이트를 KB, MB, GB, TB, PB로 변환하여 리턴한다.
	     * @param bytes 바이트수로 문자열형태로 보낸다.
	     * @return String result
	     */
	    public static String getBytesSize(String bytes) {
	        if(bytes.equals("0")) {
	        	return "0.0B";
	        }
	    	
	    	String retFormat = "0";
	        Double size = Double.parseDouble(bytes);
	        String[] s = { "bytes", "KB", "MB", "GB", "TB", "PB" };
	        if (bytes != "0") {
	              int idx = (int) Math.floor(Math.log(size) / Math.log(1024));
	              DecimalFormat df = new DecimalFormat("#,###.##");
	              double ret = ((size / Math.pow(1024, Math.floor(idx))));
	              retFormat = df.format(ret) + "" + s[idx];
	         } else {
	              retFormat += " " + s[0];
	         }
	         return retFormat;
	    }

	/**
	 * 외장메모리의 마운트 여부를 체크하여 최상위 절대경로를 리턴합니다.
	 * 외장메모리가 마운트 되지 않은 경우는 내장메모리의 절대경로를 리턴합니다.
	 * @return 절대경로
	 */
	public static String getExternalStorageDirectoryApsoulutePath(){
		String ext = Environment.getExternalStorageState();
		String path = null;

		if(ext.equals(Environment.MEDIA_MOUNTED)){
			Log.d(TAG, "ExternalStorage Mounted..");

			path = Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			Log.d(TAG, "ExternalStorage not Mounted..");

			path = Environment.MEDIA_UNMOUNTED;
		}
		return path;
	}
}
