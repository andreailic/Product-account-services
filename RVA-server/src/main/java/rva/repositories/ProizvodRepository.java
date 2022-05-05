package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Proizvod;
import rva.model.Proizvodjac;


public interface ProizvodRepository extends JpaRepository<Proizvod, Integer>{
	Collection<Proizvod> findByNazivContainingIgnoreCase(String proizvod);
	
	Collection<Proizvod> findByProizvodjac(Proizvodjac proizvodjac);
}
