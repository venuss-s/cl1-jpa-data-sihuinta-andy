package pe.edu.I202222350.cl1_jpa_data_sihuinta_andy.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pe.edu.I202222350.cl1_jpa_data_sihuinta_andy.entity.Country;

import java.util.Optional;


public interface CountryRepository extends CrudRepository<Country, String> {


}

