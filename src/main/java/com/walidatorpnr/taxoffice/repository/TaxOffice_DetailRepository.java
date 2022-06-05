package com.walidatorpnr.taxoffice.repository;

import com.walidatorpnr.taxoffice.TaxOffice_Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("taxOffice_DetailRepository")
public interface TaxOffice_DetailRepository extends JpaRepository<TaxOffice_Detail, Integer> {


    @Query("select t from TaxOffice_Detail t where t.id = :id")
    List<TaxOffice_Detail> findAllById(int id);


}

