package redpig.utility.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Kim YoungHun on 2016-08-03.
 */
public class NetworkUtils {

    /**
     * 현재 데이터가 와이파이인지 4G인지 체크한다.
     *  타입이 리턴값 이므로, ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE 로 체크
     * @param context
     * @return
     */
    public static int getCurrNetworkType(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connMgr.getActiveNetworkInfo();
        if ( (info != null) && (info.isAvailable() == true) ){
            return info.getType();
        } else {
            return -1;
        }
    }

    /**
     *
     * 요약 : MD5로 암호화된 주소값을 얻는다.
     * @param s MD5로 암호화할 주소
     * @return MD5로 암호화된 주소
     */
    public static String getMD5Hash(String s) {
        String MD5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes());
            byte byteData[] = md.digest();
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < byteData.length ; i++){
                sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
            }
            MD5 = sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            MD5 = null;
        }
        return MD5;
    }

    /**
     * Try to extract a hardware MAC address from a given IP address using the
     * ARP cache (/proc/net/arp).<br>
     * <br>
     * We assume that the file has this structure:<br>
     * <br>
     * IP address       HW type     Flags       HW address            Mask     Device
     * 192.168.18.11    0x1         0x2         00:04:20:06:55:1a     *        eth0
     * 192.168.18.36    0x1         0x2         00:22:43:ab:2a:5b     *        eth0
     *
     * @param ip
     * @return the MAC from the ARP cache
     */
    public static String getMacFromArpCache(String ip) {
        if (ip == null)
            return null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" +");
                if (splitted != null && splitted.length >= 4 && ip.equals(splitted[0])) {
                    // Basic sanity check
                    String mac = splitted[3];
                    if (mac.matches("..:..:..:..:..:..")) {
                        return mac;
                    } else {
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * 요약 : 다운로드 할 url 파일의 전체 사이즈를 리턴
     * @param url
     * @return int
     */
    public static int getDownloadFileFullSize(String url){
        int size = 0;
        try {
            URLConnection urlFileSize = new URL(url).openConnection();
            urlFileSize.connect();
            size = urlFileSize.getContentLength();
        }catch(Exception e) {}
        return size;
    }
}
