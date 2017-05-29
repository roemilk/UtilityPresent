package redpig.utility.system;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Kim YoungHun on 2017-04-26.
 */

public class BackPressCloseHandler {
    private long mBackKeyPressedTime = 0;
    private Toast mToast = null;
    private String mMessage = null;

    private Activity mActivity;

    public BackPressCloseHandler(Activity activity) {
        this.mActivity = activity;
    }

    public void onBackPressed(){
        if (System.currentTimeMillis() > mBackKeyPressedTime + 2000) {
            mBackKeyPressedTime = System.currentTimeMillis();
            showToast();
            return;
        }
        if (System.currentTimeMillis() <= mBackKeyPressedTime + 2000) {
            mToast.cancel();

            mActivity.moveTaskToBack(true);
            mActivity.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /**
     * 안내 메시지를 세팅합니다.
     * @param msg
     */
    public void setMessage(String msg){
        this.mMessage = msg;
    }

    /**
     * 커스텀으로 만든 Toast 객체를 세팅합니다.
     * @param toast
     */
    public void setCustomToast(Toast toast){
        this.mToast = toast;
    }

    private void showToast() {
        if(mMessage == null){
            mMessage = "한번 더 누르시면 종료합니다.";
        }

        if(mToast != null){
            mToast.show();
        }else{
            mToast = Toast.makeText(mActivity, mMessage, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }
}
