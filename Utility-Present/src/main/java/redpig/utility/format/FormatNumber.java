package redpig.utility.format;

import java.text.DecimalFormat;

public class FormatNumber {

	/**
	 * 
	 * 요약 : 숫자를 3자리 콤마표시하여 리턴한다. 
	 * @param junsu
	 * @return
	 */
	 public static String Comma_won(String junsu) {
		  int inValues = Integer.parseInt(junsu);
		  DecimalFormat Commas = new DecimalFormat("#,###");
		  String result_int = (String)Commas.format(inValues);
		  return result_int;
		 }
}
