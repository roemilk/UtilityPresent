/**
 * 
 */
package redpig.utility.date;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @FileName   : DateUtil.java
 * @Project    : PigNetworkServer
 * @Date       : 2014. 1. 27. 
 * @작성자     : Kim Young Hun
 * @변경이력  :
 * @프로그램 설명 :
 */
public class DateUtil {
	private static final String TAG = "DateUtil";

	public static final int RETURN_TYPE_DATE = 0x001;
	public static final int RETURN_TYPE_STRING = 0x002;

	public static final String DefaultFormatYMD = "yyy.MM.dd";
	public static final String DefaultFormatYMDHMS = "yyy.MM.dd.hh.mm.ss";


	/**
	 *
	 * @return 현재 년도,월,날짜를 리턴받는다.
	 */
	public static String getTodayDate(String format) {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat (format, Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format (currentTime);
		return mTime;
	}
	
	/**
	 * 
	 * 요약 : 현재 년도,월,날짜,시간,분,초를 리턴받는다. 
	 * @return
	 */
	public static String getTodayTimeDate() {
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy.MM.dd.hh.mm.ss", Locale.KOREA );
		Date currentTime = new Date ( );
		String mTime = mSimpleDateFormat.format (currentTime);
	return mTime;
	}
	
	/**
	 * 
	 * 요약 : -표시가 된 날짜 표시 형식으로 리턴한다. 
	 * @return
	 */
	public static String getBarTimeFormat(long time) {
		Date date = new Date(time * 1000); //unix time stamp는 * 1000을 해줘야한다.
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd hh:mm", Locale.KOREA);
		String mTime = mSimpleDateFormat.format (date);
	return mTime;
	}
	
	/**
	 * 
	 * 요약 : 유닉스방식의 타임스탬프를 형식에 맞추어 변환 후 리턴해준다. 
	 * @param time 유닉스용 타임스탬프
	 * @param style 포멧 스타일 값을 세팅한다.
	 * DateFormat.FULL : 2009년 5월 29일 금요일
	 * DateFormat.LONG : 2009년 5월 29일 (금)
	 * DateFormat.MEDIUM : 2009. 5. 29
	 * DateFormat.SHORT : 09. 5. 29
	 * @return
	 */
	public static String getUnixTimeStampConvertToDateFormat(long time, int style) {
		Date date = new Date(time * 1000); //unix time stamp는 * 1000을 해줘야한다.
		DateFormat format = DateFormat.getDateInstance(style);
		String day = format.format(date);
		return day;
	}
	
	/**
	 * 
	 * 요약 : 특정 날짜로부터 해당 일수 만큼의 뒤의 날짜를 얻는다. (예: 포멧 yyyy-mm-dd)
	 * @param targetDate 지정한 날짜로부터
	 * @param afterDays 몇일 뒤
	 * @return 결과 날짜
	 */
	public static Date getAfterDay(String targetDate, int afterDays, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		String resultString = null;
		Date resultDate = null;
		Calendar cal = null;
		try {
			Date date = dateFormat.parse(targetDate);
			//포맷설정
			  cal = Calendar.getInstance();
			  cal.setTime(date);
			  cal.add(Calendar.DATE, afterDays);
			     
			// 특정 형태의 날짜로 값을 뽑기
			  resultString = dateFormat.format(cal.getTime());
			  resultDate = dateFormat.parse(resultString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			Log.d(TAG, e.toString());
		}
		return resultDate;
	}
	
	/**
	 * 
	 * 요약 : 문자열로된 날짜를 지정된 포멧의 형식에 맞춰 Date 객체로 변환한다. 
	 * @param dateString 날짜 문자열
	 * @param format 지정된 포멧
	 * @return Date 객체
	 */
	public static Date getParseDateFormat(String dateString, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Date를 String으로 Format에 맞게 변환한다.
	 * @param date
	 * @param format
     * @return
     */
	public static String getParseDateToString(Date date, String format){
		SimpleDateFormat transFormat = new SimpleDateFormat(format);
		String result = transFormat.format(date);

		return result;
	}

	/**
	 * 오늘날짜가 정해진 날짜를 지났는지 체크한다.(New)
	 * @param today
	 * @param afterDay
	 * @return
	 */
	public static boolean checkAfterDay(String today, String afterDay, String format){
		Date todayDate = getParseDateFormat(today, format);
		Date afterDayDate = getParseDateFormat(afterDay, format);

		long todaySec = todayDate.getTime();
		long afterDaySec = afterDayDate.getTime();

		if(todaySec > afterDaySec){ //오늘 날짜가 7일뒤 날짜를 지났다.
			return true;
		}else{ //지나지 않았다.
			return false;
		}
	}
}
