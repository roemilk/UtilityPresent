package redpig.utility.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;

/**
 * 
 * @작성자 KimYoungHun
 * @날짜 : 2014. 8. 18.
 * @설명 : 간단한 효과음등의 재생을 도와주는 클래스
 */
public class MediaSoundPlay {
	
	/**
	 * 
	 * 요약 : 사운드를 재생시켜준다.
	 * @param context 어플리케이션 Context
	 * @param res 사운드 리소스
	 */
	public static void doSoundPlay(Context context, int res) {
		SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		final int sound = soundPool.load(context, res, 1);
		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
				// TODO Auto-generated method stub
				soundPool.play(sound, 1, 1, 0, 0, 1);
			}
		});
	}
}
