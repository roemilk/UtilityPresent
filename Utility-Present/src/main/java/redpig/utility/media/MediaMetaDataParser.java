package redpig.utility.media;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;

import redpig.utility.file.MemoryUtils;
import redpig.utility.file.StoragePath;

/**
 * 
 * @작성자 KimYoungHun
 * @날짜 : 2014. 8. 6.
 * @설명 : 영상 파일의 헤더 정보 및 기타 메타정보를 파싱 해주는 클래스
 */
public class MediaMetaDataParser {
	public static final String META_FTYP = "ftyp";
	public static final String META_MOOV = "moov";
	private RandomAccessFile mRandomAccessFile = null;
	private Context mContext;
	public MediaMetaDataParser(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	
	/**
	 * 
	 * 요약 : 대상 파일의 헤더정보중 MOOV 정보를 찾아서 해당 블락의 사이즈만큼 읽은후 파일로 저장하여 그 정보를 리턴한다.
	 * @param openFile 정보를 파싱할 파일을 오픈한다.
	 * @param metaElementType 추출한 메타 엘리먼트 타입을 설정한다. (MetaDataParser.META_FTYP, META_MOOV)
	 * @return Moov의 정보가 저장된 파일의 경로를 리턴한다.
	 */
	public File getMetaDataMP4(File openFile, String metaElementType) {
		 byte[] mSizebuffer = new byte[4]; //8바이트중 앞의 4바이트로 사이즈의 정보를 담아올 버퍼
		 byte[] mElementNameBuffer = new byte[4]; //8바이트중 뒤의 4바이트로 타입 네임을 담아올 버퍼
		 long mOffset = 0; //파일 포인터 위치
		 long mBoxSize = 0; //헤더의 전체사이즈
		 String mElementName = "";
		
		String saveFileDirPath = StoragePath.EXTERNAL_ROOT_PATH + "/Meta";
		if(!MemoryUtils.makeDir(saveFileDirPath)) {
			Toast.makeText(mContext, "폴더를 생성할 수 없습니다.", Toast.LENGTH_SHORT).show();
			return null;
		}
		File saveMetaFile = new File(saveFileDirPath + "/" + metaElementType + ".dat");
		File openMediaFile = openFile;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		try {
			mRandomAccessFile = new RandomAccessFile(openMediaFile, "r");
			fos = new FileOutputStream(saveMetaFile);
			bos = new BufferedOutputStream(fos);
			
		while(true) {
			mRandomAccessFile.read(mSizebuffer);
			mRandomAccessFile.read(mElementNameBuffer);
			
			mBoxSize = MediaMetaDataUtils.byteToInt(mSizebuffer, ByteOrder.BIG_ENDIAN);
			System.out.println("박스사이즈 : " + mBoxSize);
			
			StringBuffer sb = new StringBuffer();
			for(byte b : mElementNameBuffer) {
				sb.append((char)b);
			}
			mElementName = sb.toString();
			if(mElementName.equals(metaElementType)) {
				Log.i("MetaData","엘리먼트 네임 : " + mElementName);
				Log.i("MetaData","박스 사이즈 : " + mBoxSize);
				Log.i("MetaData","현재 오프셋 : " + mOffset + " 잘라야될 사이즈 : " + mBoxSize);
				mRandomAccessFile.seek(mOffset);
				Log.i("MetaData","오프셋 스타트 오프셋으로 이동후 파일포인터 위치 : " + mRandomAccessFile.getFilePointer());
				
				byte[] moovBuffer;//파일리드시 필요한 버퍼
				if(MediaMetaDataParser.META_FTYP.equals(metaElementType)) { //추출 타입에 따라 버퍼의 사이즈를 다르게 설정한다.
					moovBuffer = new byte[1]; //ftyp는 boxsize가 작기 때문에 데이터 리드시 오차를 줄이기위해 1바이트 버퍼를 사용
				}else {
					moovBuffer = new byte[1024]; //moov의 boxsize는 크기 때문에 스트림의 read 연산 성능을 고려하여 버퍼를 크게 잡는다. 
				}
				int len = 0; //읽은 데이터 길이
				int totalLen = 0; //읽은 데이터 길이의 총 합
				while(true){
					if(totalLen >= mBoxSize) {
						break;
					}
					len = mRandomAccessFile.read(moovBuffer); //1024바이트 만큼 데이터를 읽어온다.
					System.out.println("len : " + len);
					totalLen += len;
					bos.write(moovBuffer, 0, len);
					System.out.println(totalLen);
				}
				bos.close();
				fos.close();
				break;
			}
			mOffset = mBoxSize + mOffset;
			mRandomAccessFile.seek(mOffset);
			
			Log.i("MetaData","오프셋 : " + mOffset);
			Log.i("MetaData","현재 파일 포인터 위치  : " + mRandomAccessFile.getFilePointer());
			Log.i("MetaData","------------------------------------------------------------------");
			}//while end
		}catch(Exception e) {Log.i("ERR", ""+e.toString());}
		
		return saveMetaFile;
	}
}
