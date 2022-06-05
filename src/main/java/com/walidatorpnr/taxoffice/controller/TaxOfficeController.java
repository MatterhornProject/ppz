package com.walidatorpnr.taxoffice.controller;

import com.walidatorpnr.taxoffice.City;
import com.walidatorpnr.taxoffice.TaxOffice;
import com.walidatorpnr.taxoffice.TaxOffice_Detail;
import com.walidatorpnr.taxoffice.Voivodeship;
import com.walidatorpnr.taxoffice.service.CityService;
import com.walidatorpnr.taxoffice.service.TaxOfficeService;
import com.walidatorpnr.taxoffice.service.TaxOffice_DetailService;
import com.walidatorpnr.taxoffice.service.VoivodeshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class TaxOfficeController {

    @Autowired
    private Voivodeship voivodeship;

    @Autowired
    private City city;

    @Autowired
    private TaxOffice taxOffice;

    @Autowired
    private TaxOffice_Detail taxOffice_detail;

    VoivodeshipService voivodeshipService;
    CityService cityService;
    TaxOfficeService taxOfficeService;
    TaxOffice_DetailService taxOffice_detailService;

    public TaxOfficeController(VoivodeshipService voivodeshipService, CityService cityService,
                               TaxOfficeService taxOfficeService, TaxOffice_DetailService taxOffice_detailService) {
        this.voivodeshipService = voivodeshipService;
        this.cityService = cityService;
        this.taxOfficeService = taxOfficeService;
        this.taxOffice_detailService = taxOffice_detailService;
    }

    @GetMapping("/taxoffice")
    public String getVoivodeship(Model model) {

        List<Voivodeship> voivodeships = voivodeshipService.findAll();
        model.addAttribute("voivodeships", voivodeships);
        return "taxoffice";
    }


    @RequestMapping(value="/select_voivodeship", method = RequestMethod.POST)
    public String selectVoivodeship (int voivodeship, Model model) {

        List<Voivodeship> voivodeships = voivodeshipService.findAll();
        model.addAttribute("voivodeships", voivodeships);
        model.addAttribute("voivodeship_selected", voivodeship);

        List<City> cities = cityService.getAllCitiesByVoivodeship(voivodeship);
        model.addAttribute("cities", cities);
        System.out.println("@@@@@@@@@@@@@@@@@@" + voivodeship);
        return "taxoffice";
    }

    @RequestMapping(value="/select_city", method = RequestMethod.POST)
    public String selectCity (int city,  Model model) {

        City c = cityService.findByID(city);
        List<City> cities = cityService.getAllCitiesByVoivodeship(c.getVoivodeship().getId());
        model.addAttribute("cities", cities);
        model.addAttribute("city_selected", city);

        List<Voivodeship> voivodeships = voivodeshipService.findAll();
        model.addAttribute("voivodeships", voivodeships);
        model.addAttribute("voivodeship_selected", c.getVoivodeship().getId());

        List<TaxOffice> taxOffices = taxOfficeService.findAllByCity_id(city);
        List<TaxOffice_Detail> taxOffice_details = taxOffice_detailService.findAllByIDs(taxOffices);
        List<TaxOffice_Detail> taxOfficeDetailsWithNames = taxOffice_detailService.taxOffice_detailListWithNames(taxOffices, taxOffice_details);
        model.addAttribute("taxoffices_details", taxOfficeDetailsWithNames);

        System.out.println("^%%%%%%%%%%" + city);
        return "taxoffice";
    }
}
