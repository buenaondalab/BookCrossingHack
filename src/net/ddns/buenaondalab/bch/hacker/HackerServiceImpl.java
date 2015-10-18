package net.ddns.buenaondalab.bch.hacker;

import java.io.IOException;
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

import net.ddns.buenaondalab.bch.dao.PlaceDao;
import net.ddns.buenaondalab.bch.model.City;
import net.ddns.buenaondalab.bch.model.Country;
import net.ddns.buenaondalab.bch.model.Region;

@Stateless
@LocalBean
public class HackerServiceImpl implements HackerService {	
		
	private static final int TIMEOUT = 10000;
		
	private static final String BOOKCROSSING_SITE = "http://www.bookcrossing.com";
	protected static final String SEARCH_URL = "http://www.bookcrossing.com/hunt";
	
	/** Url to the page listing all books in Italy */
	private static final String BOOKCROSSING_IT = "http://www.bookcrossing.com/hunt/19";
	private static final String DIV_BOOKSHELF_HOLDER = "dBookShelfHolder";
	private static final String SPAN_SZONE_HEADER = "sZoneHeader";
	
	public static final long ITALY_ID = 19;
	
	@EJB
	PlaceDao countryDao;
	@EJB
	PlaceDao regionDao;
	@EJB
	PlaceDao cityDao;
	@EJB
	PlaceDao placeDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HackerServiceImpl.class);
	
//	private List<Country> countries = new ArrayList<Country>();
//	private List<Region> regions = new ArrayList<Region>();
//	private List<City> cities = new ArrayList<City>();
//	private List<Place> places = new ArrayList<Place>();
//	private List<Book> books = new ArrayList<Book>();
		
//	private String url;
	
	private void syncCountries() {
		
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
		} catch (IOException e) {
			LOGGER.error("Problems retrieving countries data", e);
		}
		
	}
	
	private void syncRegions() {
		
		try {
		
			LOGGER.info("Getting regions...");
			List<Country> countries = countryDao.findAll(Country.class);
		
			for (Country country : countries) {
				
				Map<Long, String> regionMap;			
				regionMap = this.getData(SEARCH_URL + "/" + country.getId());
			 
				for (Long id : regionMap.keySet()) {
					String value = regionMap.get(id);
					Region dbRegion = regionDao.findById(Region.class, id);
					if (dbRegion == null) {
						regionDao.create(new Region(id,value,country));
						LOGGER.info("Region {} created!", value);
					}
				}
			}
		}
		
		catch (IOException e) {
			LOGGER.error("Problems retrieving regions data", e);
		}
	}
	
	private void syncCities() {
		//TODO: to implement!
		
		try {
			
			LOGGER.info("Getting cities...");
			List<Region> regions = regionDao.findAll(Region.class);
		
			for (Region region : regions) {
				
				Map<Long, String> cityMap;			
				cityMap = this.getData(SEARCH_URL + "/" + region.getCountry().getId() + "/" + region.getId());
			 
				for (Long id : cityMap.keySet()) {
					String value = cityMap.get(id);
					City dbCity = cityDao.findById(City.class, id);
					if (dbCity == null) {
						LOGGER.info("City {} created!", value);
						cityDao.create(new City(id,value,region));
					}
				}
			}
		}
		
		catch (IOException e) {
			LOGGER.error("Problems retrieving cities data", e);
		}
	}
	
	private void syncPlaces() {
		//TODO: to implement!
	}
	
	private void syncBooks() {
		//TODO: to implement!
	}
	
	/* (non-Javadoc)
	 * @see net.ddns.buenaondalab.bch.hacker.HackerService#syncAll()
	 */
	@Override
	public void syncAll() {
		
		LOGGER.info("Synchronizing all...");
		
//		syncCountries();
//		syncRegions();
//		syncCities();
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
		Elements as = new Elements();
		
		as.addAll(bookshelf.getElementsByTag("a"));
		
		for (Element a : as) {			
			String[] link = a.attr("href").split("/");
			Long id = Long.valueOf(link[link.length-1]);
			String value = a.text();
			data.put(id,value);
		}
		
		return data;
	}
	 
//	public String getUrl() {
//		return url;
//	}
//		
//	public void setUrl(String url) {
//		this.url = url;
//	}
}