package com.walidatorpnr.taxoffice.service;

import com.google.common.collect.Lists;
import com.walidatorpnr.taxoffice.TaxOffice;
import com.walidatorpnr.taxoffice.TaxOffice_Detail;
import com.walidatorpnr.taxoffice.repository.TaxOffice_DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class TaxOffice_DetailService {

    @Autowired
    TaxOffice_DetailRepository taxOffice_detailRepository;

    public List<TaxOffice_Detail> findAllByID(int id) {
        return taxOffice_detailRepository.findAllById(id);
    }

    public List<TaxOffice_Detail> findAllByIDs(List<TaxOffice> taxOffices) {
        List<TaxOffice_Detail> taxOffice_details = Lists.newArrayList();
        for(int i = 0; i<taxOffices.size(); i++){
            taxOffice_details.addAll(taxOffice_detailRepository.findAllById(taxOffices.get(i).getTaxOffice_details().getId()));
        }
        return taxOffice_details;
    }

    public List<TaxOffice_Detail> taxOffice_detailListWithNames(List<TaxOffice> taxOffices, List<TaxOffice_Detail> taxOffice_details) {
        for(int i = 0; i<taxOffice_details.size(); i++){
            if(taxOffice_details.get(i).getId() == taxOffices.get(i).getTaxOffice_details().getId()){
                taxOffice_details.get(i).setTaxoffice_name(taxOffices.get(i).getName());
            }
        }
        return taxOffice_details.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}