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

	private static final Logger LOGGER = LoggerFactory.getLogger(HackerServiceImpl.class);
	@EJB
	CountryDao countryDao;
	@EJB
	RegionDao regionDao;
	@EJB
	CityDao cityDao;

	@EJB
	PlaceDao placeDao;

	private void synchCities(final Region region) throws IOException {
		
		Map<Long, String> cityMap;
		LOGGER.info("Syncronizing cities for {} region in {}...", region, region.getCountry());
		// TODO: insert url field in entity class...
		cityMap = this.getData(SEARCH_URL + "/" + region.getCountry().getId() + "/" + region.getId());

		for (final Long id : cityMap.keySet()) {
			final String value = cityMap.get(id);
			final City dbCity = cityDao.findById(City.class, id);
			if (dbCity == null) {
				LOGGER.info("City {} in country {} created!", value, region.getCountry().getName());
				cityDao.create(new City(id, value, region));
			}
		}
		LOGGER.info("Cities for region {} in {} syncronized.", region, region.getCountry());
	}

	/**
	 * Persist new places in db and return the list
	 *
	 * @param city
	 * @return the list of new places created
	 * @throws IOException
	 */
	private List<Place> createNewPlaces(final City city) throws IOException {

		Map<Long, String> placeMap;
		final List<Place> newPlaces = new ArrayList<>();

		// TODO: insert url field in entity class...
		placeMap = this.getData(SEARCH_URL + "/" + city.getRegion().getCountry().getId() + "/"
				+ city.getRegion().getId() + "/" + city.getId());

		for (final Long id : placeMap.keySet()) {

			final String value = placeMap.get(id);
			final Place dbPlace = placeDao.findById(Place.class, id);
			if (dbPlace == null) {
				final Place newPlace = placeDao.create(new Place(id, value, city));
				LOGGER.info("Place {} in city {} created!", value, city.getName());
				newPlaces.add(newPlace);
			}
		}

		return newPlaces;
	}

	private void createNewRegions(final Country country) throws IOException {
		Map<Long, String> regionMap;
		regionMap = this.getData(SEARCH_URL + "/" + country.getId());

		for (final Long id : regionMap.keySet()) {
			final String value = regionMap.get(id);
			final Region dbRegion = regionDao.findById(Region.class, id);
			if (dbRegion == null) {
				Region newRegion = regionDao.create(new Region(id, value, country));
				country.addRegion(newRegion);
				LOGGER.info("Region {} in country {} created!", value, country.getName());
			}
		}
	}

	/**
	 *
	 * @param url
	 *            BookCrossing page url
	 * @return
	 * @throws IOException
	 */
	private Map<Long, String> getData(final String url) throws IOException {

		// System.out.println("Starting retrieving data...");
		final Map<Long, String> data = new HashMap<Long, String>();
		final Document bcPage = Jsoup.connect(url).timeout(TIMEOUT).get();
		final Element bookshelf = bcPage.getElementById(DIV_BOOKSHELF_HOLDER);

		if (bookshelf != null) {

			final Elements as = new Elements();

			as.addAll(bookshelf.getElementsByTag("a"));

			for (final Element a : as) {
				final String[] link = a.attr("href").split("/");
				final Long id = Long.valueOf(link[link.length - 1]);
				final String value = a.text();
				data.put(id, value);
			}
		}

		else {
			LOGGER.warn("No element with id: {} found at url: {}", DIV_BOOKSHELF_HOLDER, url);
		}

		return data;
	}

	private void setPlacesInfo(final List<Place> places) throws IOException {

		for (final Place place : places) {

			final String placeUrl = SEARCH_URL + "/" + place.getCity().getRegion().getCountry().getId() + "/"
					+ place.getCity().getRegion().getId() + "/" + place.getCity().getId() + "/" + place.getId();

			final Document placePage = Jsoup.connect(placeUrl).timeout(10000).get();

			final Element placeInfo = placePage.getElementById(SPAN_SZONE_HEADER);

			// System.out.println(placeInfo.ownText());
			place.setAddress(placeInfo.ownText());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.ddns.buenaondalab.bch.hacker.HackerService#syncAll()
	 */
	@Override
	public void syncAll() {

		LOGGER.info("Synchronizing all...");

		syncCountries();
		syncRegions();
		syncCities();
		// syncPlaces();
		// syncBooks();

	}

	// TODO: ADD DELETION
	@Override
	public void syncBooks() {
		// TODO: to implement...
		LOGGER.info("Synchronizing books to implement...");
	}

	@Override
	public void syncBooks(final Country country) {
		// TODO Auto-generated method stub

	}

	// TODO: ADD DELETION
	@Override
	public void syncCities() {

		try {

			LOGGER.info("Synchronizing cities...");
			final List<Region> regions = regionDao.findAll(Region.class);

			for (final Region region : regions) {

				synchCities(region);

			}
			LOGGER.info("Cities are synchronized!");
		}

		catch (final IOException e) {
			LOGGER.error("Problems retrieving cities data", e);
		}
	}

	@Override
	public void syncCities(final Country country) {

		try {

			LOGGER.info("Getting {}'s cities...", country.getName());
			final List<Region> regions = country.getRegions();

			for (final Region region : regions) {

				synchCities(region);

			}
			LOGGER.info("{}'s cities are synchronized!", country.getName());
		}

		catch (final IOException e) {
			LOGGER.error("Problems retrieving {}'s cities data", country.getName(), e);
		}

	}

	// TODO: ADD DELETION
	@Override
	public void syncCountries() {

		try {

			LOGGER.info("Getting countries...");

			final Map<Long, String> countryMap = this.getData(SEARCH_URL);

			for (final Long id : countryMap.keySet()) {

				final String value = countryMap.get(id);
				final Country dbCountry = countryDao.findById(Country.class, id);

				if (dbCountry == null) {
					countryDao.create(new Country(id, value));
					LOGGER.info("Country {} created!", value);
				}
			}
			LOGGER.info("Countries are synchronized!");

		} catch (final IOException e) {
			LOGGER.error("Problems retrieving countries data", e);
		}

	}

	// TODO: ADD DELETION
	@Override
	public void syncPlaces() {

		try {

			LOGGER.info("Getting places...");
			final List<City> cities = cityDao.findAll(City.class);

			for (final City city : cities) {

				List<Place> newPlaces;
				newPlaces = createNewPlaces(city);
				setPlacesInfo(newPlaces);
			}

			LOGGER.info("Places are synchronized!");
		}

		catch (final IOException e) {
			LOGGER.error("Problems retrieving places data", e);
		}
	}

	@Override
	public void syncPlaces(final Country country) {

		try {

			LOGGER.info("Getting places in {}", country.getName());

			final List<City> cities = cityDao.getCities(country);

			for (final City city : cities) {

				List<Place> newPlaces;
				newPlaces = createNewPlaces(city);
				setPlacesInfo(newPlaces);
			}

			LOGGER.info("Places are synchronized!");
		}

		catch (final IOException e) {
			LOGGER.error("Problems retrieving places data", e);
		}

	}

	// TODO: ADD DELETION
	@Override
	public void syncRegions() {

		List<Country> countries = null;
		Country country = null;

		try {

			LOGGER.info("Getting regions...");
			// countries = countryDao.findAll(Country.class);
			countries = new ArrayList<>();
			country = countryDao.findById(Country.class, ITALY_ID);
			countries.add(country);

			for (final Country c : countries) {

				createNewRegions(c);

			}
			LOGGER.info("Regions are synchronized!");
		}

		catch (final IOException e) {
			LOGGER.error("Problems retrieving regions data", e);
		}
	}

	@Override
	public void syncRegions(final Country country) {

		try {

			LOGGER.info("Getting regions for {}", country.getName());

			createNewRegions(country);

			LOGGER.info("{}'s regions are synchronized!", country.getName());
		}

		catch (final IOException e) {
			LOGGER.error("Problems retrieving {}'s regions data", e);
		}
	}

	// public String getUrl() {
	// return url;
	// }
	//
	// public void setUrl(String url) {
	// this.url = url;
	// }

	/**
	 * FOR PAGES THAT NEED AUTHENTICATION
	 *
	 * Connection.Response res =
	 * Jsoup.connect("http://www.example.com/login.php") .data("username",
	 * "myUsername", "password", "myPassword") .method(Method.POST) .execute();
	 *
	 * Document doc = res.parse(); String sessionId = res.cookie("SESSIONID");
	 * // you will need to check what the right cookie name is And then send it
	 * on the next request like:
	 *
	 * Document doc2 = Jsoup.connect("http://www.example.com/otherPage")
	 * .cookie("SESSIONID", sessionId) .get();
	 *
	 */
}