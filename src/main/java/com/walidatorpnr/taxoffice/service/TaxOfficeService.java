package com.walidatorpnr.taxoffice.service;

import com.walidatorpnr.taxoffice.TaxOffice;
import com.walidatorpnr.taxoffice.repository.TaxOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TaxOfficeService {

    @Autowired
    TaxOfficeRepository taxOfficeRepository;

    public List<TaxOffice> findAllByCity_id(int id){
        return taxOfficeRepository.findAllByCity_id(id);
    }
    public TaxOffice findByID(int id) { return taxOfficeRepository.findById(id); }

}
