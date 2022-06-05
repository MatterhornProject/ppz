package com.walidatorpnr.taxoffice.repository;

import com.walidatorpnr.taxoffice.TaxOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taxOfficeRepository")
public interface TaxOfficeRepository extends JpaRepository<TaxOffice,Integer> {

    @Query("select t from TaxOffice t where t.city.id = :id")
    List<TaxOffice> findAllByCity_id(int id);

    @Query("select t from TaxOffice t where t.id = :id")
    TaxOffice findById(int id);
}
