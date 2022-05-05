package rva.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
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
import rva.model.Proizvodjac;
import rva.repositories.ProizvodRepository;
import rva.repositories.ProizvodjacRepository;

@RestController
@Api(tags = {"Proizvod CRUD operacije"})


public class ProizvodController {
	@Autowired
	private ProizvodRepository repo;
	
	@Autowired
	private ProizvodjacRepository proizvodjacRepo;
	
    @Autowired 
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/proizvod")
	@ApiOperation(value = "Vraća kolekciju svih proizvoda iz baze podataka")
	public Collection<Proizvod> getAllProizvod(){
		return repo.findAll();
	}
	
	@GetMapping("/proizvod/{id}")
	@ApiOperation(value = "Vraća proizvod na osnovu prosleđenog ID-a")
	public Proizvod getProizvodById(@PathVariable int id) {
		return repo.getById(id);
	}
	
	@GetMapping("/proizvod/naziv/{naziv}")
	@ApiOperation(value = "Vraća kolekciju proizvoda na osnovu prosleđenog naziva")
	public Collection<Proizvod> getProizvodByNaziv(@PathVariable String naziv){
		return repo.findByNazivContainingIgnoreCase(naziv);
	}
	@GetMapping("/proizvod/proizvodjac/{proizvodjac}")
	@ApiOperation(value = "Vraća kolekciju proizvoda na osnovu prosleđenog proizvođača")
	public Collection<Proizvod> getProizvodByProizvodjac(@PathVariable int proizvodjac){
		  Proizvodjac temp = proizvodjacRepo.getById(proizvodjac);
		  return repo.findByProizvodjac(temp);
	  }
	
	@PostMapping("/proizvod")
	@ApiOperation(value = "Dodaje novi proizvod u bazu podataka")
	public ResponseEntity<Proizvod> createStavka(@RequestBody Proizvod proizvod){
		if(repo.existsById(proizvod.getId())) {
			return new ResponseEntity <Proizvod> (HttpStatus.CONFLICT);
		}
		else {
			Proizvod temp = repo.save(proizvod);
			return new ResponseEntity <Proizvod> (temp,HttpStatus.CREATED);
		}
	}
	
	@PutMapping("/proizvod")
	@ApiOperation(value = "Update-uje proizvod iz baze podataka")
    public ResponseEntity <Proizvod> updateProizvod(@RequestBody Proizvod proizvod){
   	if(repo.existsById(proizvod.getId())) {
   		repo.save(proizvod);
 		  return new ResponseEntity <Proizvod> (HttpStatus.OK);
 	  } else {
 		  return new ResponseEntity<Proizvod>(HttpStatus.CONFLICT);
 	  }
   }
	 @DeleteMapping("/proizvod/{id}")
	 @ApiOperation(value = "Briše proizvod iz baze podataka (na osnovu prosleđene id vrednosti)")
	  public ResponseEntity <Proizvod> deleteProizvod(@PathVariable int id){
	    if (repo.existsById(id)) {
	    	if(id == -100) {
	    		repo.deleteById(id);
	    		jdbcTemplate.execute ("insert into proizvod (\"id\", \"naziv\", \"proizvodjac\") values (-100, 'test naziv', 1 )");
	    	   return new ResponseEntity <Proizvod>(HttpStatus.OK);
	    	}else {
	    		repo.deleteById(id);
	    		return new ResponseEntity <Proizvod>(HttpStatus.OK);
	    	}
	    }
	    else {
	    	return new ResponseEntity <Proizvod>(HttpStatus.NOT_FOUND);
	    }
	}
	

}
