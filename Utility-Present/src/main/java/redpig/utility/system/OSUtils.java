package redpig.utility.system;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.regex.Pattern;

/**
 * Created by Kim YoungHun on 2016-05-18.
 */
public class OSUtils {

    public static final int REQUEST_GET_ACCOUNT = 112;
    public static final int REQUEST_GET_PhoneNumber = 113;

    /**
     * 디바이스의 디버그 키해쉬 또는 릴리즈 키해쉬를 추출하여 리턴합니다.
     * 문자열 형태로 리턴하며 디버그모드에서는 디버그 해쉬키를 APK를 만들어서 릴리즈모드에서 호출할 경우
     * 릴리즈 해쉬키를 리턴합니다.
     * @return String
     */
    public static String getDebugOrRelease(Context context){
        final String TAG = "KeyHash";
        String keyHash = null;
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                keyHash = new String(Base64.encode(md.digest(), 0));
                Log.d(TAG, keyHash);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }

        if(keyHash != null){
            return keyHash;
        }else{
            return null;
        }
    }

    /**
     * 현재 디버그모드여부를 리턴
     *
     * @param context
     * @return
     */
    public static boolean isDebuggable(Context context) {
        boolean debuggable = false;

        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
            debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
        /* debuggable variable will remain false */
        }

        return debuggable;
    }

    /**
     *
     * 요약 : 버전 네임을 가져온다.
     * @param context
     * @return
     */
    public static String getVersionName(Context context)
    {
        try {
            PackageInfo pi= context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    /** 디바이스 ID 얻어오기 **/
    public static String getDeviceID() {
        /** 기기의 시리얼 번호 얻어오기 (2.3버젼부터 가능한 기능으로 2.2버젼은 문제점이 많다고 함.) **/
        String sDeviceID = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            sDeviceID = (String)get.invoke(c, "ro.serialno");
        }catch(Exception e) {}
        return sDeviceID;
    }

    /**
     *
     * 요약 : 디바이스에 저장된 구글 이메일 계정을 얻는다.
     * @param context
     * @return
     */
    public static String getMailAddress(Context context){
        String possibleEmail = null;
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccountsByType(
                "com.google");
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                possibleEmail = account.name;
                Log.i("MY_EMAIL_count", "" + possibleEmail);
            }
        }
        return possibleEmail;
    }

    public static boolean isGETACCOUNTSAllowed(Context context) {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.GET_ACCOUNTS);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    public static boolean isGETPhoneNumberAllowed(Context context) {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //if you don't have the permission then Requesting for permission
    public static void requestGET_ACCOUNTSPermission(Activity activity){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.GET_ACCOUNTS)){
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(activity,new String[]{android.Manifest.permission.GET_ACCOUNTS},REQUEST_GET_ACCOUNT);
    }

    /**
     * 휴대폰 번호를 리턴한다.
     * @return
     */
    public static String getPhoneNumber(Context context){
        TelephonyManager t = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = t.getLine1Number();
        return phoneNumber;
    }
}
