package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Racun;

public interface RacunRepository extends JpaRepository<Racun, Integer>{
	
	Collection <Racun> findByNacinPlacanjaContainingIgnoreCase(String nacin_placanja);
}
