package redpig.utility.gps;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 
 * @작성자 KimYoungHun
 * @날짜 : 2015. 11. 4.
 * @설명 : gps 관련 유틸리티
 */
public class GpsUtils {
	/**
	 * 
	 * 요약 : 현재 GPS를 사용할수 있는지 여부를 리턴한다.
	 * @param context
	 * @return boolean true:사용가능 false:사용불가능
	 */
	public static boolean getGpsState(Context context) {
		LocationManager locManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		if(!locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 
	 * 요약 : 위도 경도를 기준으로 주소값 시/구/동 형태의 문자열 리스트로 반환한다. 
	 * @param context
	 * @param lat 위도
	 * @param lng 경도
	 * @return 주소 문자열
	 */
	public static ArrayList<String> getFindAddressArray(Context context, double lat, double lng) {
			ArrayList<String> addressArray = new ArrayList<String>();
			Geocoder geocoder = new Geocoder(context, Locale.KOREA);
	        List<Address> address;
	        try {
	          if (geocoder != null) {
	                // 세번째 인수는 최대결과값인데 하나만 리턴받도록 설정했다
	                address = geocoder.getFromLocation(lat, lng, 1);
	                Address addressInfo = address.get(0);
	                // 설정한 데이터로 주소가 리턴된 데이터가 있으면
	               if (address != null && address.size() > 0) {
	                   addressArray.add(addressInfo.getAdminArea());
	                   addressArray.add(addressInfo.getLocality());
	                   addressArray.add(addressInfo.getThoroughfare());
	                }
	            }
	            
	        } catch (IOException e) {
	          Toast.makeText(context, "주소취득 실패", Toast.LENGTH_LONG).show();
              e.printStackTrace();
	        }
	        return addressArray;
	}
	
	/**
	 * 
	 * 요약 : 위도 경도를 기준으로 주소값 문자열 전체를 반환한다.
	 * @param context
	 * @param lat 위도
	 * @param lng 경도
	 * @return 주소 문자열
	 */
	public static String getFindAddressFullString(Context context, double lat, double lng) {
		String addressResult = null;
		Geocoder geocoder = new Geocoder(context, Locale.KOREA);
        List<Address> address;
        try {
          if (geocoder != null) {
                // 세번째 인수는 최대결과값인데 하나만 리턴받도록 설정했다
                address = geocoder.getFromLocation(lat, lng, 1);
                // 설정한 데이터로 주소가 리턴된 데이터가 있으면
               if (address != null && address.size() > 0) {
            	   Address addressInfo = address.get(0);
            	   addressResult = addressInfo.getAddressLine(0).toString();
                }
            }
            
        } catch (IOException e) {
          Toast.makeText(context, "주소취득 실패", Toast.LENGTH_LONG).show();
          e.printStackTrace();
        }
        return addressResult;
	}
}
