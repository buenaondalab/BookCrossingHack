package net.ddns.buenaondalab.bch.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
 * The persistent class for the City database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="City")
@NamedQueries({
	@NamedQuery(name="City.findAll", query="SELECT c FROM City c"),
	@NamedQuery(name="City.findByName", query="SELECT c FROM City c WHERE c.name = :name"),
	@NamedQuery(name="City.findByCountry", query="SELECT c FROM City c WHERE c.region.country = :country")
})
public class City implements Serializable {
	
	private static final long serialVersionUID = -5893226262331396075L;
	private Long id;
	private String name;
	private Region region;
	private List<Place> places;

	public City() {}
	
	public City(Long id, String name, Region r) {
		this.id = id;
		this.name = name;
		this.region = r;
	}


	@Id
	@Column(unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(nullable=false, length=255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Region
	@ManyToOne
	@JoinColumn(name="region_id")
	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}


	//bi-directional many-to-one association to Place
	@XmlTransient
	@OneToMany(mappedBy="city")
	public List<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Place addPlace(Place place) {
		getPlaces().add(place);
		place.setCity(this);

		return place;
	}

	public Place removePlace(Place place) {
		getPlaces().remove(place);
		place.setCity(null);

		return place;
	}

}