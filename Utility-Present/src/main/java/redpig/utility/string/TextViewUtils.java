package redpig.utility.string;

import android.text.Layout;
import android.widget.TextView;

/**
 * Created by Kim YoungHun on 2016-09-28.
 */
public class TextViewUtils {

    /**
     * 해당 텍스트뷰의 텍스트가 말줄임(Ellipsize)상태인지 체크합니다.
     * @param textView
     * @return
     */
    public static boolean isEllipsize(TextView textView){
        boolean result = false;
        Layout layout = textView.getLayout();
        if(layout != null) {
            int lines = layout.getLineCount();
            if(lines > 0) {
                int ellipsisCount = layout.getEllipsisCount(lines-1);
                if ( ellipsisCount > 0) {
                    result = true;
                }else{
                    result = false;
                }
            }
        }
        return result;
    }
}
