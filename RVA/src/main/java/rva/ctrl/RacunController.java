package rva.ctrl;

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
import rva.model.Racun;
import rva.repositories.RacunRepository;

@CrossOrigin
@RestController
@Api(tags = {"Račun CRUD operacije"})

public class RacunController {

	@Autowired
	private RacunRepository repo;
	
	 @Autowired
	  private JdbcTemplate jdbcTemplate;
	
	@GetMapping ("/racun")
	@ApiOperation(value = "Vraća kolekciju svih računa iz baze podataka")
	 public Collection<Racun> getAllRacun(){
		return repo.findAll();
	}
	
	@GetMapping("/racun/{id}")
	 @ApiOperation(value = "Vraća račun na osnovu prosleđenog ID-a")
	public Racun getRacunById (@PathVariable int id) {
		return repo.getById(id);
	}
	
	 @GetMapping("/racun/nacin_placanja/{nacin_placanja}")
	 @ApiOperation(value = "Vraća kolekciju računa na osnovu prosleđenog načina plaćanja")
	  public Collection <Racun> getRacunBynacin_placanja(@PathVariable String nacin_placanja){
	  return repo.findByNacinPlacanjaContainingIgnoreCase(nacin_placanja);
	 }
	 
	 @PostMapping("/racun")
		@ApiOperation(value = "Dodaje novi račun u bazu podataka")
	  public ResponseEntity <Racun> createRacun(@RequestBody Racun racun){
		  if(repo.existsById(racun.getId())) {
			  return new ResponseEntity <Racun> (HttpStatus.CONFLICT);
		  } else {
			 Racun temp = repo.save(racun);
			  return new ResponseEntity<Racun>(temp,HttpStatus.CREATED);
		  }
	  }
	 
	 @PutMapping("/racun")
	 @ApiOperation(value = "Update-uje račun iz baze podataka")
     public ResponseEntity <Racun> updateRacun(@RequestBody Racun racun){
    	if(repo.existsById(racun.getId())) {
    		repo.save(racun);
  		  return new ResponseEntity <Racun> (HttpStatus.OK);
  	  } else {
  		  return new ResponseEntity<Racun>(HttpStatus.CONFLICT);
  	  }
    }
	 @DeleteMapping("/racun/{id}")
	 @ApiOperation(value = "Briše račun iz baze podataka (na osnovu prosleđene id vrednosti)")
	  public ResponseEntity <Racun> deleteRacun(@PathVariable int id){
	    if (repo.existsById(id)) {
	    	if(id == -100) {
	    		repo.deleteById(id);
	    		jdbcTemplate.execute ("insert into racun (\"id\", \"datum\", \"nacin_placanja\" ) values (-100, '2022-05-04', 'gotovina')");
	    	   return new ResponseEntity <Racun>(HttpStatus.OK);
	    	}else {
	    		repo.deleteById(id);
	    		return new ResponseEntity <Racun>(HttpStatus.OK);
	    	}
	    }
	    else {
	    	return new ResponseEntity <Racun>(HttpStatus.NOT_FOUND);
	    }
	}
	 
}



