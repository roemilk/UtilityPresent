package redpig.utility.media;

/**
 * Created by Kim YoungHun on 2016-05-16.
 * 미디어 작업에 관련된 유틸리티 모음입니다.
 */
public class MediaUtils {

    private MediaUtils() {}

    /**
     *밀리초를 시분초 형식으로 변환하여 리턴합니다.
     * @param milliSec
     */
    public static String getMillSecToHMS(int milliSec){

        int hour = ( milliSec / ( 1000 * 60 * 60 )) % 100;
        int minute = ( milliSec / ( 1000 * 60 )) % 60;
        int second = ( milliSec / 1000 ) % 60;

        String result = String.format("%02d:%02d:%02d", hour, minute, second);
        return result;
    }
}
