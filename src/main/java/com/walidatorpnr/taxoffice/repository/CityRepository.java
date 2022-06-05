package com.walidatorpnr.taxoffice.repository;

import com.walidatorpnr.taxoffice.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Integer> {

    @Query("select c from City c where c.voivodeship.id = :id")
    List<City> findAllByVoivodeship_id(int id);

    City findById(int id);
}
