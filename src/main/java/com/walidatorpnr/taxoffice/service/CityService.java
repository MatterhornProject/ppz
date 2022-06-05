package com.walidatorpnr.taxoffice.service;

import com.walidatorpnr.taxoffice.City;
import com.walidatorpnr.taxoffice.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public List<City> getAllCitiesByVoivodeship(int id){

        return cityRepository.findAllByVoivodeship_id(id);
    }
    public List<City> findAll(){
        return cityRepository.findAll();
    }
    public City findByID(int id){ return cityRepository.findById(id);}
}
