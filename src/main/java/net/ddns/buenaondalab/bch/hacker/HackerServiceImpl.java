package net.ddns.buenaondalab.bch.hacker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.ddns.buenaondalab.bch.dao.CityDao;
import net.ddns.buenaondalab.bch.dao.CountryDao;
import net.ddns.buenaondalab.bch.dao.PlaceDao;
import net.ddns.buenaondalab.bch.dao.RegionDao;
import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;
import net.ddns.buenaondalab.bch.model.Place;
import net.ddns.buenaondalab.bch.model.Region;
import net.ddns.buenaondalab.bch.serviceImpl.ServiceImpl;


@Stateless
@LocalBean
public class HackerServiceImpl extends ServiceImpl implements HackerService {	
		
	private static final int TIMEOUT = 30000;
		
	private static final String BOOKCROSSING_SITE = "http://www.bookcrossing.com";
	protected static final String SEARCH_URL = "http://www.bookcrossing.com/hunt";
	
	/** Url to the page listing all books in Italy */
	private static final String BOOKCROSSING_IT = "http://www.bookcrossing.com/hunt/19";
	private static final String DIV_BOOKSHELF_HOLDER = "dBookShelfHolder";
	private static final String SPAN_SZONE_HEADER = "sZoneHeader";
	
	public static final long ITALY_ID = 19;
	
	@EJB
	CountryDao countryDao;
	@EJB
	RegionDao regionDao;
	@EJB
	CityDao cityDao;
	@EJB
	PlaceDao placeDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HackerServiceImpl.class);
	
//	private List<Country> countries = new ArrayList<Country>();
//	private List<Region> regions = new ArrayList<Region>();
//	private List<City> cities = new ArrayList<City>();
//	private List<Place> places = new ArrayList<Place>();
//	private List<Book> books = new ArrayList<Book>();
		
//	private String url;
	
	//TODO: ADD DELETION
	@Override
	public void syncCountries() {
		
		try {
		
			LOGGER.info("Getting countries...");
		
			Map<Long,String> countryMap = this.getData(SEARCH_URL);
			
			for (Long id : countryMap.keySet()) {
				
				String value = countryMap.get(id);
				Country dbCountry = countryDao.findById(Country.class, id);
				
				if(dbCountry == null) {
					countryDao.create(new Country(id,value));
					LOGGER.info("Country {} created!", value);
				}
			}
			LOGGER.info("Countries are synchronized!");
			
		} catch (IOException e) {
			LOGGER.error("Problems retrieving countries data", e);
		}
		
	}
	
	//TODO: ADD DELETION
	@Override
	public void syncRegions() {
		
		List<Country> countries = null;
		Country country = null;
		
		try {
		
			LOGGER.info("Getting regions...");
			//countries = countryDao.findAll(Country.class);
			countries = new ArrayList<>();
			country = countryDao.findById(Country.class, ITALY_ID);
			countries.add(country);
		
			for (Country c : countries) {
				
				createNewRegions(c);
				
			}
			LOGGER.info("Regions are synchronized!");
		}
		
		catch (IOException e) {
			LOGGER.error("Problems retrieving regions data", e);
		}
	}
	
	@Override
	public void syncRegions(Country country) {
				
		try {
		
			LOGGER.info("Getting regions for {}", country.getName());
		
			createNewRegions(country);				
			
			LOGGER.info("{}'s regions are synchronized!", country.getName());
		}
		
		catch (IOException e) {
			LOGGER.error("Problems retrieving {}'s regions data", e);
		}
	}


	private void createNewRegions(Country country) throws IOException {
		Map<Long, String> regionMap;			
		regionMap = this.getData(SEARCH_URL + "/" + country.getId());
		
		for (Long id : regionMap.keySet()) {
			String value = regionMap.get(id);
			Region dbRegion = regionDao.findById(Region.class, id);
			if (dbRegion == null) {
				regionDao.create(new Region(id,value,country));
				LOGGER.info("Region {} in country {} created!", value, country.getName());
			}
		}
	}
	
	
	//TODO: ADD DELETION
	@Override
	public void syncCities() {
				
		try {
			
			LOGGER.info("Getting cities...");
			List<Region> regions = regionDao.findAll(Region.class);
		
			for (Region region : regions) {
				
				createNewCities(region);
				
			}
			LOGGER.info("Cities are synchronized!");
		}
		
		catch (IOException e) {
			LOGGER.error("Problems retrieving cities data", e);
		}
	}

	
	private void createNewCities(Region region) throws IOException {
		Map<Long, String> cityMap;
		//TODO: insert url field in entity class...
		cityMap = this.getData(SEARCH_URL + "/" + region.getCountry().getId() + "/" + region.getId());
 
		for (Long id : cityMap.keySet()) {
			String value = cityMap.get(id);
			City dbCity = cityDao.findById(City.class, id);
			if (dbCity == null) {
				LOGGER.info("City {} in country {} created!", value, region.getCountry().getName());
				cityDao.create(new City(id,value,region));
			}
		}
	}
	
	//TODO: ADD DELETION
	@Override
	public void syncPlaces() {
		
		try {
			
			LOGGER.info("Getting places...");
			List<City> cities = cityDao.findAll(City.class);
		
			for (City city : cities) {
				
				List<Place> newPlaces;
				newPlaces = createNewPlaces(city);
				setPlacesInfo(newPlaces);				
			}
			
			LOGGER.info("Places are synchronized!");
		}
		
		catch (IOException e) {
			LOGGER.error("Problems retrieving places data", e);
		}
	}
	
	/**
	 * Persist new places in db and return the list
	 * @param city
	 * @return the list of new places created
	 * @throws IOException
	 */
	private List<Place> createNewPlaces(City city) throws IOException {
		
		Map<Long, String> placeMap;
		List<Place> newPlaces = new ArrayList<>();
		
		//TODO: insert url field in entity class...
		placeMap = this.getData(SEARCH_URL + "/" + 
								city.getRegion().getCountry().getId() + "/" +
								city.getRegion().getId() + "/" +
								city.getId());
 
		for (Long id : placeMap.keySet()) {
			
			String value = placeMap.get(id);
			Place dbPlace = placeDao.findById(Place.class, id);
			if (dbPlace == null) {				
				Place newPlace = placeDao.create(new Place(id,value,city));
				LOGGER.info("Place {} in city {} created!", value, city.getName());
				newPlaces.add(newPlace);
			}
		}
		
		LOGGER.info("Places' list updated");
		return newPlaces;
	}
	
	private void setPlacesInfo(List<Place> places) throws IOException {
		
		System.out.println("Getting places info...");
		
		for (Place place : places) {
			
			String placeUrl = SEARCH_URL + "/" + 
								place.getCity().getRegion().getCountry().getId() + "/" +
								place.getCity().getRegion().getId() + "/" +
								place.getCity().getId() + "/" +
								place.getId();
			
			Document placePage = Jsoup.connect(placeUrl).timeout(10000).get();
			
			Element placeInfo = placePage.getElementById(SPAN_SZONE_HEADER);
			
			//System.out.println(placeInfo.ownText());
			place.setAddress(placeInfo.ownText());
		}
	}

	//TODO: ADD DELETION
	@Override
	public void syncBooks() {
		//TODO: to implement...
		LOGGER.info("Synchronizing books to implement...");
	}
	
	/* (non-Javadoc)
	 * @see net.ddns.buenaondalab.bch.hacker.HackerService#syncAll()
	 */
	@Override
	public void syncAll() {
		
		LOGGER.info("Synchronizing all...");
		
		syncCountries();
		syncRegions();
		syncCities();
//		syncPlaces();
//		syncBooks();
		
	}

	/**
	 * 
	 * @param url BookCrossing page url
	 * @return
	 * @throws IOException
	 */
	 private Map<Long,String> getData(String url) throws IOException{
		
		//System.out.println("Starting retrieving data...");
		Map<Long,String> data = new HashMap<Long,String>();
		Document bcPage = Jsoup.connect(url).timeout(TIMEOUT).get();
		Element bookshelf = bcPage.getElementById(DIV_BOOKSHELF_HOLDER);
		
		if (bookshelf!= null) {
			
			Elements as = new Elements();
			
			as.addAll(bookshelf.getElementsByTag("a"));
			
			for (Element a : as) {			
				String[] link = a.attr("href").split("/");
				Long id = Long.valueOf(link[link.length-1]);
				String value = a.text();
				data.put(id,value);
			}
		}
		
		else {
			LOGGER.warn("No element with id: {} found at url: {}" , DIV_BOOKSHELF_HOLDER, url);
		}
		
		
		return data;
	}

	@Override
	public void syncCities(Country country) {
		
		try {
			
			LOGGER.info("Getting {}'s cities...", country.getName());
			List<Region> regions = country.getRegions();
		
			for (Region region : regions) {
				
				createNewCities(region);
				
			}
			LOGGER.info("{}'s cities are synchronized!", country.getName());
		}
		
		catch (IOException e) {
			LOGGER.error("Problems retrieving {}'s cities data", country.getName(), e);
		}
		
	}

	@Override
	public void syncPlaces(Country country) {
		
		try {
			
			LOGGER.info("Getting places in {}", country.getName());
			
			List<City> cities = cityDao.getCities(country);
		
			for (City city : cities) {
				
				List<Place> newPlaces;
				newPlaces = createNewPlaces(city);
				setPlacesInfo(newPlaces);				
			}
			
			LOGGER.info("Places are synchronized!");
		}
		
		catch (IOException e) {
			LOGGER.error("Problems retrieving places data", e);
		}
		
	}

	@Override
	public void syncBooks(Country country) {
		// TODO Auto-generated method stub
		
	}
	 
//	public String getUrl() {
//		return url;
//	}
//		
//	public void setUrl(String url) {
//		this.url = url;
//	}
	 
	 /** FOR PAGES THAT NEED AUTHENTICATION
	  	  
	 Connection.Response res = Jsoup.connect("http://www.example.com/login.php")
			    .data("username", "myUsername", "password", "myPassword")
			    .method(Method.POST)
			    .execute();

			Document doc = res.parse();
			String sessionId = res.cookie("SESSIONID"); // you will need to check what the right cookie name is
			And then send it on the next request like:

			Document doc2 = Jsoup.connect("http://www.example.com/otherPage")
			    .cookie("SESSIONID", sessionId)
			    .get();
			    
	*/
}