package com.walidatorpnr.taxoffice.repository;

import com.walidatorpnr.taxoffice.Voivodeship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("voivodeshipRepository")
public interface VoivodeshipRepository extends JpaRepository<Voivodeship, Integer> {
}
