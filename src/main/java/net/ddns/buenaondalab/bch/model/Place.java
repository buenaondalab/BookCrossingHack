package net.ddns.buenaondalab.bch.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the Place database table.
 * TODO: check findAround query... not so clear...
 */
@XmlRootElement
@Entity
@Table(name="Place")
@NamedQueries({
	@NamedQuery(name="Place.findAll", query="SELECT p FROM Place p"),
	@NamedQuery(name="Place.findByCountry", query="SELECT p FROM Place p WHERE p.city.region.country = :country"),
	@NamedQuery(name="Place.findByRegion", query="SELECT p FROM Place p WHERE p.city.region = :region"),
	@NamedQuery(name="Place.findByCity", query="SELECT p FROM Place p WHERE p.city = :city"),
	@NamedQuery(name="Place.findAround", query="SELECT p FROM Place p WHERE ((:west < :east AND p.lng BETWEEN :west AND :east )" +
																		    " OR (:east < :west AND (p.lng > :east OR p.lng < :west )))" +
																	  " AND p.lat BETWEEN :south AND :north")
	
	})
public class Place implements GeoPosition, Serializable {
	
	private static final long serialVersionUID = 5404046414068333473L;
	private String id;
	private String address;
	private double lat;
	private double lng;
	private String name;
	private City city;
	private List<Book> books;

	public Place() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@Column(length=255)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}


	public double getLng() {
		return this.lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}


	@Column(nullable=false, length=255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//bi-directional many-to-one association to City
	@ManyToOne
	@JoinColumn(name="city_id")
	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}


	//bi-directional many-to-one association to Book
	@XmlTransient
	@OneToMany(mappedBy="place")
	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book addBook(Book book) {
		getBooks().add(book);
		book.setPlace(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setPlace(null);

		return book;
	}

}