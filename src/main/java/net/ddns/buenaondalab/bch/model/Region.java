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
 * The persistent class for the Region database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="Region")
@NamedQueries({
	@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r"),
	@NamedQuery(name="Region.findByName", query="SELECT r FROM Region r WHERE r.name = :name")
})
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private List<City> cities;
	private Country country;

	public Region() {}
	
	public Region(Long id, String name, Country country) {
		this.id = id;
		this.name = name;
		this.country = country;
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
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to City
	@XmlTransient
	@OneToMany(mappedBy="region")
	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public City addCity(City city) {
		getCities().add(city);
		city.setRegion(this);

		return city;
	}

	public City removeCity(City city) {
		getCities().remove(city);
		city.setRegion(null);

		return city;
	}


	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="country_id")
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}