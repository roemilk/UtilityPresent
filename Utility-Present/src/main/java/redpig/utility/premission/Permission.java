package redpig.utility.premission;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by Kim YoungHun on 2017-01-23.
 * 퍼미션 체크와 퍼미션 오픈 관련된 기능을 모아둔 클래스
 */
public class Permission {
    private static final String TAG = "Permission";

    /**
     * 퍼미션이 허용되었는지 여부를 체크합니다.
     */
    public static boolean checkPermission(Context context, String permission){
        int permissionCheck = ContextCompat.checkSelfPermission(context, permission);

        if(permissionCheck == PackageManager.PERMISSION_DENIED){ //권한 없음
            return false;
        }else{ //권한 있음
            return true;
        }
    }

    /**
     * 한개의 퍼미션을 허용한다. (엑티비티)
     * @param activity
     * @param permission ex) Manifest.permission.READ_CONTACTS
     * @param requestCode ex) 0x001
     */
    public static void requestPermissionActivity(Activity activity, String permission, int requestCode){
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
    }

    /**
     * 복수의 퍼미션을 허용한다. (엑티비티)
     * @param activity
     * @param permissions ex) new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission....}
     */
    public static void requestPermissionsActivity(Activity activity, String[] permissions, int requestCode){
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    /**
     * 한개의 퍼미션을 허용한다.(프래그먼트)
     * @param fragment
     * @param permission
     */
    public static void requestPermissionFragment(Fragment fragment, String permission, int requestCode){
        fragment.requestPermissions(new String[]{permission}, requestCode);
    }

    /**
     * 복수의 퍼미션을 허용한다.(프래그먼트)
     * @param fragment
     * @param permissions
     */
    public static void requestPermissionsFragment(Fragment fragment, String[] permissions, int requestCode){
        fragment.requestPermissions(permissions, requestCode);
    }
}
