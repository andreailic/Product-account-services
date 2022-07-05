package rva.ctrl;

import java.math.BigDecimal;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.model.Proizvod;
import rva.model.Racun;
import rva.model.StavkaRacuna;
import rva.repositories.ProizvodRepository;
import rva.repositories.RacunRepository;
import rva.repositories.StavkaRacunaRepository;

@CrossOrigin
@RestController
@Api(tags = {"Stavka računa CRUD operacije"})

public class StavkaRacunaController {
	
	
  @Autowired 
  private StavkaRacunaRepository repo;
  @Autowired
  private RacunRepository racunRepo;
  @Autowired
  private ProizvodRepository proizvodRepo;
  
  @Autowired 
  private JdbcTemplate jdbcTemplate;
  
  @GetMapping("/stavkaRacuna")
  @ApiOperation(value = "Vraća kolekciju svih stavki računa iz baze podataka")
  public Collection<StavkaRacuna> getAllStavkaRacuna() {
	    return repo.findAll();
  }    
  
  @GetMapping("/stavkaRacuna/{id}")
  @ApiOperation(value = "Vraća stavku računa na osnovu prosleđenog ID-a")
	  public StavkaRacuna getStavkaRacunaById (@PathVariable int id ) {
	  return repo.getById(id);
  }
	 
  @GetMapping("/stavkaRacuna/racun/{racun}") 
  @ApiOperation(value = "Vraća kolekciju stavki računa na osnovu prosleđenog računa")
  public Collection<StavkaRacuna> getStavkeByRacun(@PathVariable int racun){
	  Racun temp = racunRepo.getById(racun);
	  return repo.findByRacun(temp);
  }
  @GetMapping("/stavkaRacuna/proizvod/{proizvod}") 
  @ApiOperation(value = "Vraća kolekciju stavki računa na osnovu prosleđenog proizvoda")
  public Collection<StavkaRacuna> getStavkeByProizvod(@PathVariable int proizvod){
	  Proizvod temp = proizvodRepo.getById(proizvod);
	  return repo.findByProizvod(temp);
  }
     @GetMapping ("stavkaRacuna/cena/{cena}")
     @ApiOperation(value = "Vraća kolekciju stavki računa na osnovu prosleđene cene")
	  public Collection <StavkaRacuna> getStavkeByCena (@PathVariable BigDecimal cena){
	  return repo.findByCenaLessThanOrderById(cena);
	  }
	  
	@PostMapping("/stavkaRacuna")
	@ApiOperation(value = "Dodaje novu stavku računa u bazu podataka")
	public ResponseEntity<StavkaRacuna> createStavka(@RequestBody StavkaRacuna racun){
		if(repo.existsById(racun.getId())) {
			return new ResponseEntity <StavkaRacuna> (HttpStatus.CONFLICT);
		}
		else {
			racun.setRedniBroj(repo.nextRbr(racun.getRacun().getId()));
			StavkaRacuna temp = repo.save(racun);
			return new ResponseEntity <StavkaRacuna> (temp,HttpStatus.CREATED);
		}
	}
	@PutMapping ("/stavkaRacuna")
	 @ApiOperation(value = "Update-uje stavku računa iz baze podataka")
	public ResponseEntity <StavkaRacuna> updateStavka(@RequestBody StavkaRacuna racun){
		if(repo.existsById(racun.getId())){
			repo.save(racun);
			return new ResponseEntity <StavkaRacuna> (HttpStatus.OK);
		}
		else {
			return new ResponseEntity <StavkaRacuna> (HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/stavkaRacuna/{id}")
	 @ApiOperation(value = "Briše stavku račun iz baze podataka (na osnovu prosleđene id vrednosti)")
	public ResponseEntity<StavkaRacuna> deleteRacun (@PathVariable int id) {
		if (repo.existsById(id)) {
			if(id == -100)
			{repo.deleteById(id);
			jdbcTemplate.execute("Insert into stavka_racuna(\"id\",\"redni_broj\", \"kolicina\", \"jedinica_mere\", \"cena\", \"racun\", \"proizvod\")"
					+ "values (-100, '7', '5', 'kom', '1500',1,6)");
			return new ResponseEntity <StavkaRacuna> (HttpStatus.OK);
			}
			else {
				repo.deleteById(id);
				return new ResponseEntity <StavkaRacuna> (HttpStatus.OK);
			     }                  
		}
		else {
			return new ResponseEntity <StavkaRacuna> (HttpStatus.NOT_FOUND);
		}
			
	}
	
}
