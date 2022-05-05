

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
import rva.model.Proizvodjac;
import rva.repositories.ProizvodjacRepository;

@RestController
@Api(tags = {"Proizvođač CRUD operacije"})

public class ProizvodjacController {

	 @Autowired
	 private ProizvodjacRepository repo;
	 
	 
	 @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @GetMapping ("/proizvodjac" )
  @ApiOperation(value = "Vraća kolekciju svih proizvođača iz baze podataka")
    public Collection<Proizvodjac> getAllProizvodjac(){
	    return repo.findAll();
  }
  
  @GetMapping("/proizvodjac/{id}")
  @ApiOperation(value = "Vraća proizvođača na osnovu prosleđenog ID-a")
	  public Proizvodjac getProizvodjacById (@PathVariable int id) {
	     return repo.getById(id);
  }
  
  
  @GetMapping("/proizvodjac/naziv/{naziv}")
  @ApiOperation(value = "Vraća kolekciju proizvođača na osnovu prosleđenog naziva")
  public Collection <Proizvodjac> getProizvodjacByNaziv(@PathVariable String naziv){
  return repo.findByNazivContainingIgnoreCase(naziv);
}
  
  @PostMapping("/proizvodjac")
  @ApiOperation(value = "Dodaje novog proizvođača u bazu podataka")
  public ResponseEntity <Proizvodjac> createProizvodjac(@RequestBody Proizvodjac proizvodjac){
	  if(repo.existsById(proizvodjac.getId())) {
		  return new ResponseEntity <Proizvodjac> (HttpStatus.CONFLICT);
	  } else {
		 Proizvodjac temp = repo.save(proizvodjac);
		  return new ResponseEntity<Proizvodjac>(temp,HttpStatus.CREATED);
	  }
  }
  
    @PutMapping("/proizvodjac")
	@ApiOperation(value = "Update-uje proizvođača iz baze podataka")
     public ResponseEntity <Proizvodjac> updateProizvodjac(@RequestBody Proizvodjac proizvodjac){
    	if(repo.existsById(proizvodjac.getId())) {
    		repo.save(proizvodjac);
  		  return new ResponseEntity <Proizvodjac> (HttpStatus.OK);
  	  } else {
  		  return new ResponseEntity<Proizvodjac>(HttpStatus.CONFLICT);
  	  }
    }
  @DeleteMapping("/proizvodjac/{id}")
  @ApiOperation(value = "Briše proizvođača iz baze podataka (na osnovu prosleđene id vrednosti)")
  public ResponseEntity <Proizvodjac> deleteProizvodjac(@PathVariable int id){
    if (repo.existsById(id)) {
    	if(id == -100) {
    		repo.deleteById(id);
    		jdbcTemplate.execute ("insert into proizvodjac (\"id\", \"naziv\", \"adresa\", \"kontakt\"	) values (-100, 'test naziv', 'test adresa', 'test kontakt')");
    	   return new ResponseEntity <Proizvodjac>(HttpStatus.OK);
    	}else {
    		repo.deleteById(id);
    		return new ResponseEntity <Proizvodjac>(HttpStatus.OK);
    	}
    }
    else {
    	return new ResponseEntity <Proizvodjac>(HttpStatus.NOT_FOUND);
    }
}
}
