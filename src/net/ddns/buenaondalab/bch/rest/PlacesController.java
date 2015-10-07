/**
 * 
 */
package net.ddns.buenaondalab.bch.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.service.PlaceService;

/**
 * @author andrea
 *
 */
@Path("/places")
@Produces("application/json")
public class PlacesController {
	
	@EJB
	PlaceService placeService;
	
	/**
	 * List all the places where books have been released
	 * @return
	 */
	@GET
	@Path("/all")
	public List<Place> getAllPlaces() {
		
		List<Place> places = placeService.findAll();
		return places;		
	}
	
	/**
	 * List all release places in a specific country 
	 */
	@GET
	@Path("/{country}")
	public List<Place> getPlacesInCountry(@PathParam("country") final String country) {
		
		List<Place> places;
		
		if(country.contentEquals("all")) {
			places = placeService.findAll();
		}
		else {
			places = placeService.getPlacesByCountry(country);
		}
		return places;
	}
	
	
	/**
	 * List all release places in a specific region 
	 */
	@GET
	@Path("/{country}/{region}")
	public List<Place> getPlacesInRegion(@PathParam("region") final String region) {
		
		List<Place> places = placeService.getPlacesByRegion(region);
		return places;
	}
	
	/**
	 * List all release places in a specific city 
	 */
	@GET
	@Path("/{country}/{region}/{city}")
	public List<Place> getPlacesInCity(@PathParam("city") final String city) {
		
		List<Place> places = placeService.getPlacesByCity(city);
		return places;
	}
	

}
