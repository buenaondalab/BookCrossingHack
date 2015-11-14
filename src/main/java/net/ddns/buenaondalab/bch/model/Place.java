package net.ddns.buenaondalab.bch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the Place database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="Place")
@NamedQueries({
	@NamedQuery(name="Place.findAll", query="SELECT p FROM Place p"),
	@NamedQuery(name="Place.findByCountry", query="SELECT p FROM Place p WHERE p.city.region.country = :country"),
	@NamedQuery(name="Place.findByRegion", query="SELECT p FROM Place p WHERE p.city.region = :region"),
	@NamedQuery(name="Place.findByCity", query="SELECT p FROM Place p WHERE p.city = :city"),
	@NamedQuery(name="Place.findAround", query="SELECT p FROM Place p WHERE p.lat BETWEEN :minLat AND :maxLat AND p.lng BETWEEN :minLng AND :maxLng")
})
public class Place implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String address;
	private double lat;
	private double lng;
	private String name;
	private City city;

	public Place() {
	}


	@Id
	@Column(unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

}