package rva.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the proizvod database table.
 * 
 */
@Entity
@NamedQuery(name="Proizvod.findAll", query="SELECT p FROM Proizvod p")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Proizvod implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PROIZVOD_ID_GENERATOR", sequenceName="PROIZVOD_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROIZVOD_ID_GENERATOR")
	private Integer id;

	private String naziv;

	//bi-directional many-to-one association to Proizvodjac
	@ManyToOne
	@JoinColumn(name="proizvodjac")
	private Proizvodjac proizvodjac;

	//bi-directional many-to-one association to StavkaRacuna
	@JsonIgnore
	@OneToMany(mappedBy="proizvod")
	private List<StavkaRacuna> stavkaRacunas;

	public Proizvod() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Proizvodjac getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public List<StavkaRacuna> getStavkaRacunas() {
		return this.stavkaRacunas;
	}

	public void setStavkaRacunas(List<StavkaRacuna> stavkaRacunas) {
		this.stavkaRacunas = stavkaRacunas;
	}

	public StavkaRacuna addStavkaRacuna(StavkaRacuna stavkaRacuna) {
		getStavkaRacunas().add(stavkaRacuna);
		stavkaRacuna.setProizvod(this);

		return stavkaRacuna;
	}

	public StavkaRacuna removeStavkaRacuna(StavkaRacuna stavkaRacuna) {
		getStavkaRacunas().remove(stavkaRacuna);
		stavkaRacuna.setProizvod(null);

		return stavkaRacuna;
	}

}