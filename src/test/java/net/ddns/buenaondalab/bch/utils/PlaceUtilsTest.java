package net.ddns.buenaondalab.bch.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.utils.PlaceUtils;

public class PlaceUtilsTest {

	@Test
	public void testCalculateDistance() {
		
		final double lat1 = 43.486698;
		final double lat2 = 43.614983;
		final double lng1 = 13.496836;
		final double lng2 = 13.534002;
		
		Place p1,p2;
		
		p1 = new Place();
		p1.setLat(lat1);
		p1.setLng(lng1);
		
		p2 = new Place();
		p2.setLat(lat2);
		p2.setLng(lng2);
		
		final double[] expected = {14.57};//14.5757
		final double[] actual = {PlaceUtils.calculateDistance(p1, p2)};
		
		assertArrayEquals(expected, actual, 0.01);
	}

}
