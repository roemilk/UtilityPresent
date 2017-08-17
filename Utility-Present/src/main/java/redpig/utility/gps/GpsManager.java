package redpig.utility.gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 *
 * @작성자 KimYoungHun
 * @날짜 : 2015. 11. 4.
 * @설명 : 현재 위치의 위도 경도를 가져온다.
 */
public class GpsManager {
	private static GpsManager mGpsManager = null;
	private LocationManager mLocationManager = null;
	private Criteria mCriteria = null;

	public GpsInterface.onUpdateListener mListener = null;
	private Context mContext;
	private boolean mUpdateFlag = false;

	private GpsManager(Context context, GpsInterface.onUpdateListener listener) {
		// TODO Auto-generated constructor stub
		mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		this.mListener = listener;
		this.mContext = context;
	}

	/**
	 *
	 * 요약 : GpsManager의 인스턴스를 반환한다. (Singletone)
	 * @param context
	 * @return
	 */
	public static GpsManager getGpsManager(Context context, GpsInterface.onUpdateListener listener) {
		if (mGpsManager == null) {
			mGpsManager = new GpsManager(context, listener);
			return mGpsManager;
		} else {
			mGpsManager.mListener = listener;
			return mGpsManager;
		}
	}

	/**
	 *
	 * 요약 : GPS 수신을 시작합니다.
	 * @param minTime 얼만큼의 시간을 주기로 GPS를 수신할지
	 * @param minDistance 얼만큼의 거리가 이동되었을때 GPS 정보를 수신할지
	 * @param updateFlag true : 지속적으로 위치정보를 수신받음 false : 한번만 위치정보를 수신받음
	 */
	public void startGpsService(final long minTime, final float minDistance, boolean updateFlag) {
		mUpdateFlag = updateFlag;
		if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			//권한이 없는 경우 권한 요청
			return;
		}
		mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, locationListener);
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				if (mLatitude == 0 || mLongitue == 0) {
//					Toast.makeText(mContext, "GPS의 수신이 불가한 상황 또는 지역이므로\nNetwork 기반으로 위치정보를 수신하도록 변경합니다.", Toast.LENGTH_SHORT).show();
//					if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//						//권한이 없는 경우 권한 요청
//
//						return;
//					}
//					mLocationManager.removeUpdates(locationListener);
//					mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, locationListener);
//				}
//			}
//		}, 2000);
	}

	/**
	 *
	 * 요약 : Gps 수신을 멈춘다.
	 */
	public void stopGpsService() {
		mUpdateFlag = false;
	}

	/**
	 * 이 리스너는 위치 정보를 위치 공급자로부터 지속적으로 받아오는 역할을 합니다. 
	 * void onLocationChanged(Location location)
	 . 위치 정보를 가져올 수 있는 메소드입니다.
	 . 위치 이동이나 시간 경과 등으로 인해 호출됩니다.
	 . 최신 위치는 location 파라메터가 가지고 있습니다.
	 . 최신 위치를 가져오려면, location 파라메터를 이용하시면 됩니다.

	 void onProviderDisabled(String provider)
	 . 위치 공급자가 사용 불가능해질(disabled) 때 호출 됩니다.
	 . 단순히 위치 정보를 구한다면, 코드를 작성하실 필요는 없습니다.

	 void onProviderEnabled(String provider)
	 . 위치 공급자가 사용 가능해질(enabled) 때 호출 됩니다.
	 . 단순히 위치 정보를 구한다면, 코드를 작성하실 필요는 없습니다.

	 void onStatusChanged(String provider, int status, Bundle extras)
	 . 위치 공급자의 상태가 바뀔 때 호출 됩니다.
	 . 단순히 위치 정보를 구한다면, 코드를 작성하실 필요는 없습니다.
	 */
	LocationListener locationListener = new LocationListener() {
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			Log.i("GPS", "onStatusChanged");

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			Log.i("GPS", "onProviderEnabled");

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			Log.i("GPS", "onProviderDisabled");

		}

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			Log.i("GPS", "onLocationChanged");

			double latitude = location.getLatitude();
			double longitue = location.getLongitude();
			float speed = location.getSpeed();
			float bearing = location.getBearing();

			//GpsInfo Data setting
			GpsInfo gpsInfo = new GpsInfo();
			gpsInfo.setLatitude(latitude);
			gpsInfo.setLongitude(longitue);
			gpsInfo.setSpeed(speed);
			gpsInfo.setBearing(bearing);
			mListener.updateGpsInfo(gpsInfo);

			if (mUpdateFlag == false) {
				Log.i("GPS", "최초 한번만 위치정보를 받아오고 더이상 위치정보를 수신하지 않습니다.");
				if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
					//권한이 없는 경우 권한 요청
					return;
				}
				mLocationManager.removeUpdates(locationListener);
			}
		}
	};


}
