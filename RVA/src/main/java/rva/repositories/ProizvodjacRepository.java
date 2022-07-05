package rva.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.model.Proizvodjac;

public interface ProizvodjacRepository extends JpaRepository<Proizvodjac, Integer> {
	
	Collection <Proizvodjac> findByNazivContainingIgnoreCase(String naziv);

}