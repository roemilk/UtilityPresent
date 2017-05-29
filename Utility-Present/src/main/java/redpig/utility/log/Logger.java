package redpig.utility.log;

import android.util.Log;

/**
 * Created by Kim YoungHun on 2016-08-18.
 */
public class Logger {
    /**
     * JSON String을 효율적으로 로그캣에 출력하기 위한 메서드
     * @param json
     */
    public static void Logger(String TAG, String json){
        String temp_json = json;
        int log_index = 1;
        try {
            while (temp_json.length() > 0) {
                if (temp_json.length() > 4000) {
                    Log.e(TAG, "json - " + log_index + " : "
                            + temp_json.substring(0, 4000));
                    temp_json = temp_json.substring(4000);
                    log_index++;
                } else {
                    Log.e(TAG, "json - " + log_index + " :" + temp_json);
                    break;
                }
            }
        } catch (Exception e) {
        }
    }
}
