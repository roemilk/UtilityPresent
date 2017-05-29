package redpig.utility.file;

import android.os.Environment;
import android.util.Log;

/**
 * Created by Kim YoungHun on 2017-04-28.
 */

public class FileUtils {
    private static final String TAG = "FileUtils";

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
