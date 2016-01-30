package net.ddns.buenaondalab.bch.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import net.ddns.buenaondalab.bch.model.Book;
import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.service.PlaceService;

//TODO: add try catch around service calls... Let services throw exceptions!
@Path("/books")
@Produces("application/json")
public class BooksController {
	
	@EJB
	PlaceService placeService;
	
	/**
	 * List all books around a specific point in coordinates within nesw square
	 * TODO: limit square size to avoid too many books...
	 * @param lat
	 * @param lng
	 * @param radius in km
	 */
	@GET
	@Path("/bounded")
	public List<Book> getBooksAround(@QueryParam("north") final  double north,
									   @QueryParam("east") final double east,
									   @QueryParam("south") final double south,
									   @QueryParam("west") final double west) {
		
		List<Book> books = new ArrayList<>(); 
		List<Place> places = placeService.getPlacesAround(north,east,south,west);
		
		for (Place place : places) {
			
			books.addAll(place.getBooks());
		}
		
		return books;
	}

}
