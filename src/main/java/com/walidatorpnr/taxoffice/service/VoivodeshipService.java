package com.walidatorpnr.taxoffice.service;

import com.walidatorpnr.taxoffice.Voivodeship;
import com.walidatorpnr.taxoffice.repository.VoivodeshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class VoivodeshipService {

    @Autowired
    VoivodeshipRepository voivodeshipRepository;

    public List<Voivodeship> findAll(){
        return voivodeshipRepository.findAll();
    }
}
