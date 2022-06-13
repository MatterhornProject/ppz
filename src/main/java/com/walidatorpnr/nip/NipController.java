package com.walidatorpnr.nip;

//import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class NipController {

    private Nip nip2;

    @Qualifier("Nip0")
    @Autowired
    Nip nip;

    private final NipService nipService;

    @Autowired
    public NipController(Nip nip, NipService nipService) {
        this.nip = nip2;
        this.nipService = nipService;
    }



    @GetMapping("/nip_form")
    public String getNip(Model model) {

        model.addAttribute("numbersList", nip);
        return "nip_form";
    }


    @PostMapping("/validate_nip")
    public String submitNIP(@Valid @ModelAttribute("numbersList") Nip nip, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "nip_form";
        }
        else {
            nipService.readTaxOfficeID(nip);
            nipService.readTaxOfficeName(nip);
            nipService.Validate(nip);
            return "/nip_validated";
        }
    }
}
