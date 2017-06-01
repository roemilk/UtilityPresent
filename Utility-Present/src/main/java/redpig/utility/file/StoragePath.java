package redpig.utility.file;

import android.os.Environment;
/**
 * 
 * @작성자 KimYoungHun
 * @날짜 : 2014. 7. 25.
 * @설명 : 디바이스의 SD카드 디렉토리 경로의 정보를 관리한다.
 */
public class StoragePath {
	/**
	 * SD카드의 Root 경로
	 */
	 public static final String EXTERNAL_ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath(); //SD_CARD 루트 경로
	 public static final String EXTERNAL_KIVI_ROOT_PATH = EXTERNAL_ROOT_PATH + "/KIVI"; 
//	 public static final String EXTERNAL_MISSZINE_TEMP_PATH = EXTERNAL_ROOT_PATH + "/.misszine"; 
//	 public static final String EXTERNAL_MISSZINE_IMAGE_PATH = EXTERNAL_MISSZINE_ROOT_PATH + "/Image"; 
}
