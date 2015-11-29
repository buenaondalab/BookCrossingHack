package net.ddns.buenaondalab.bch.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Book database table.
 * 
 */
@Entity
@Table(name="Book")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 4157729363411740171L;
	private String id;
	private String author;
	private String capturedBy_username;
	private Date capturedOn;
	private String genre;
	private String registeredBy_username;
	private Date registeredOn;
	private String releasedBy_username;
	private Date releasedOn;
	private String status;
	private String title;
	private Place place;

	public Book() {
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
	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	@Column(length=255)
	public String getCapturedBy_username() {
		return this.capturedBy_username;
	}

	public void setCapturedBy_username(String capturedBy_username) {
		this.capturedBy_username = capturedBy_username;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getCapturedOn() {
		return this.capturedOn;
	}

	public void setCapturedOn(Date capturedOn) {
		this.capturedOn = capturedOn;
	}


	@Column(length=255)
	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}


	@Column(length=255)
	public String getRegisteredBy_username() {
		return this.registeredBy_username;
	}

	public void setRegisteredBy_username(String registeredBy_username) {
		this.registeredBy_username = registeredBy_username;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegisteredOn() {
		return this.registeredOn;
	}

	public void setRegisteredOn(Date registeredOn) {
		this.registeredOn = registeredOn;
	}


	@Column(length=255)
	public String getReleasedBy_username() {
		return this.releasedBy_username;
	}

	public void setReleasedBy_username(String releasedBy_username) {
		this.releasedBy_username = releasedBy_username;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getReleasedOn() {
		return this.releasedOn;
	}

	public void setReleasedOn(Date releasedOn) {
		this.releasedOn = releasedOn;
	}


	@Column(length=255)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Column(length=255)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	//bi-directional many-to-one association to Place
	@ManyToOne
	@JoinColumn(name="place_id")
	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

}