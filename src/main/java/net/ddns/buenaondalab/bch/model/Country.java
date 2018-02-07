package net.ddns.buenaondalab.bch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 * The persistent class for the Country database table.
 * 
 */
@XmlRootElement
@Entity
@Table(name="Country")
@NamedQueries({
	@NamedQuery(name="Country.findByName", query="SELECT c FROM Country c WHERE c.name = :name"),
	@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
})

public class Country implements Serializable {
	
	
	private static final long serialVersionUID = 7606942467209988550L;
	private Long id;
	private String name;
	private List<Region> regions;

	public Country() {}


	public Country(Long id, String name) {
		this.id = id;
		this.name = name;
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


	//bi-directional many-to-one association to Region
	@XmlTransient
	@OneToMany(mappedBy="country")
	public List<Region> getRegions() {
		if(this.regions != null) {
			return this.regions;
		}
		else {
			return new ArrayList<>();
		}
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

	public Region addRegion(Region region) {
		getRegions().add(region);
		region.setCountry(this);

		return region;
	}

	public Region removeRegion(Region region) {
		getRegions().remove(region);
		region.setCountry(null);

		return region;
	}

}