package redpig.utility.string;

/**
 * 
 * @작성자 KimYoungHun
 * @날짜 : 2015. 3. 11.
 * @설명 : 문자열 관련 유틸리티 클래스
 */
public class StringUtils {
	/**
	 * 
	 * 요약 : 여러개의 문자들을 치환하는 용도로 원본 text의 rePlaceChar의 부분들을 replaceToChar로 치환한다. 
	 * @param text 원본 문자열
	 * @param replaceChar 변경할 부분들 ex)PTMS
	 * @param replaceToChar 변경될 문자 ex) 시분초  P부분을 공백처리해야할 경우 해당 부분은 공백으로 한다.
	 * @return
	 */
	public static String replaceMultipleChar(String text, String replaceChar, String replaceToChar) {
		char[] replaceCharArray = replaceChar.toCharArray();
		char[] replaceToCharArray = replaceToChar.toCharArray();
		String result = text;
		
		for(int i=0; i<replaceCharArray.length; i++) {
			result = result.replace(replaceCharArray[i], replaceToCharArray[i]);
		}
		return result.trim();
	}
	
	/**
	 * 
	 * 요약 : 내용의 개행을 위해 구분자단위로 문자열을 자른다.
	 * @param str
	 * @return
	 */
	public static String getSplitContent(String str) {
		String[] contentsArray = str.split("\\|");
		StringBuffer sb = new StringBuffer();
		if(contentsArray.length != 1) {
			for(int i=0; i<contentsArray.length; i++) {
				if(i == contentsArray.length-1) {
					sb.append(contentsArray[contentsArray.length-1]);
					break;
				}
				sb.append(contentsArray[i] + "\n");
			}
		}else {
			sb.append(contentsArray[0]);
		}
		return sb.toString();
	}
}
