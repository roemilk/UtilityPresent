package redpig.utility.gps;

public interface GpsInterface {
	
	public interface onUpdateListener{
		/**
		 * 
		 * 요약 : GPS Manager에서 업데이트된 위도 경도를 전달한다.
		 * @param lat
		 * @param lon
		 */
		public void updateGpsInfo(double lat, double lon);
	}
}
