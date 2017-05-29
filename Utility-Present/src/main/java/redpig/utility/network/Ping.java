package redpig.utility.network;

import android.util.Log;

import java.io.IOException;
import java.net.Inet4Address;

/**
 * Created by Kim YoungHun on 2017-05-29.
 *
 */

public class Ping {
    private static final String TAG = "Ping";

    /**
     * Ping Check Interface
     */
    public interface OnScanPingCheckListener{
        void onSuccessPingCheck(String ipAddress);
        void onCompletePingCheck();
    }

    private OnScanPingCheckListener mOnScanPingCheckListener = null;
    private Thread mThread = null;
    private String mHostIpAddress = null;

    public Ping(String host){
        this.mHostIpAddress = host;
    }

    public void setOnScanPingCheckListener(OnScanPingCheckListener listener){
        this.mOnScanPingCheckListener = listener;
    }
    /**
     * 해당 HOST로 ping을 전송합니다.
     * @param ipAddress host ip
     * @return
     */
    public boolean checkPing(String ipAddress){
        String host = ipAddress;
        int timeout = 100; //msec
        boolean result = false;
        try {
            result = Inet4Address.getByName(host).isReachable(timeout);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(result){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 0~255까지 모든 ping에 대하여 브로드캐스트를 실시합니다.
     */
    public void scanAllPing(){
        mThread = new Thread(scanAllPingRunnable);
        mThread.start();
    }

    /**
     * . 구분자로 Split 후 재조합합니다.
     * @return
     */
    public String reBuildHostIpAddress(){
        int lastIndex = mHostIpAddress.lastIndexOf(".");
        String reBuildResult = mHostIpAddress.substring(0, lastIndex + 1);
        Log.d(TAG, "reBuildResult : " + reBuildResult);

        return reBuildResult;
    }

    private Runnable scanAllPingRunnable = new Runnable() {
        @Override
        public void run() {
            String scanHostAddress = reBuildHostIpAddress();
            Log.d(TAG, "scanHostAddress...");

            for (int i = 0; i <= 255; i++) {
                String pingHostAddress = scanHostAddress + i;
                boolean statePing = checkPing(pingHostAddress);

                if (statePing) {
                      mOnScanPingCheckListener.onSuccessPingCheck(pingHostAddress);
                }
            }

            mOnScanPingCheckListener.onCompletePingCheck();
        }
    };
}
