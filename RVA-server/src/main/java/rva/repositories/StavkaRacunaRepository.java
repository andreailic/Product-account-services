package rva.repositories;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rva.model.Proizvod;
import rva.model.Racun;
import rva.model.StavkaRacuna;
public interface StavkaRacunaRepository extends JpaRepository<StavkaRacuna, Integer> {



   Collection<StavkaRacuna> findByRacun(Racun racun);
   
   Collection<StavkaRacuna> findByProizvod(Proizvod proizvod);
   
   Collection<StavkaRacuna> findByCenaLessThanOrderById(BigDecimal cena);
   
   @Query(value = "Select coalesce(max(redni_broj)+1,1) from stavka_racuna where racun = ?1", nativeQuery = true)
   Integer nextRbr(Integer racunId);
   
   
   
}