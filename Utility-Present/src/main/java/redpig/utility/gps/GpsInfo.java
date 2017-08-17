package redpig.utility.gps;

/**
 * 
 * @작성자 KimYoungHun
 * @날짜 : 2015. 11. 4.
 * @설명 : 위도, 경도 정보를 저장하는 클래스
 */
public class GpsInfo {
	private double latitude;
	private double longitude;
	private float speed;
	private float bearing;

	/**
	 * 
	 * 요약 : 위도를 반환합니다. 
	 * @return
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * 
	 * 요약 : 위도를 지정합니다. 
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * 
	 * 요약 : 경도를 반환합니다.
	 * @return
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * 
	 * 요약 : 경도를 지정합니다.
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 요약 : 속도를 반환합니다.
	 * @return
     */
	public float getSpeed() {
		return speed;
	}

	/**
	 * 요약 : 속도를 지정합니다.
	 * @param speed
     */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * 각도를 반환합니다.
	 * @return
     */
	public float getBearing() {
		return bearing;
	}

	/**
	 * 각도를 지정합니다.
	 * @param bearing
     */
	public void setBearing(float bearing) {
		this.bearing = bearing;
	}
}
