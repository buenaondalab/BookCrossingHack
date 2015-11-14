package net.ddns.buenaondalab.bch.utils;

import net.ddns.buenaondalab.bch.model.Place;

public class PlaceUtils {
	
	public static final double EARTH_RADIUS = 6371D;
	
	/**
	 * Calculate distance between two places using Haversine formula
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double calculateDistance(Place p1, Place p2) {
		
		double lat1, lat2, lng1, lng2;
		
		lat1 = p1.getLat()*Math.PI/180;
		lat2 = p2.getLat()*Math.PI/180;
		lng1 = p1.getLng()*Math.PI/180;
		lng2 = p2.getLng()*Math.PI/180;
		
		return 2*EARTH_RADIUS*Math.asin(
				 Math.sqrt(
							Math.pow(Math.sin((lat2-lat1)/2), 2) +
							Math.cos(lat2)*Math.cos(lat1)*Math.pow(Math.sin((lng2-lng1)/2), 2)
				 )
		);
	}

}
