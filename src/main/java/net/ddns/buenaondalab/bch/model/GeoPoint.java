package net.ddns.buenaondalab.bch.model;

public class GeoPoint implements GeoPosition {
	
	double lat;
	double lng;
	
	GeoPoint() {
		
	}
	
	GeoPoint(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	@Override
	public double getLat() {
		return lat;
	}
	
	@Override
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	@Override
	public double getLng() {
		return lng;
	}
	
	@Override
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	

}
