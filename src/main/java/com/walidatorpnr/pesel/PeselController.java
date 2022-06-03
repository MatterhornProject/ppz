package com.walidatorpnr.pesel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



import javax.validation.Valid;
//import jakarta.validation.Valid;

@Controller
public class PeselController {


    private Pesel pesel2;

    @Qualifier("Pesel0")
  @Autowired
  private Pesel pesel;


    @Autowired
    private final PeselService peselService;
    private final PeselValidationService peselValidationService;


    @Autowired
    public PeselController(PeselService peselService, PeselValidationService peselValidationService, Pesel pesel){
        this.peselService = peselService;
        this.peselValidationService = peselValidationService;
        this.pesel = pesel2;
    }

    @GetMapping("/pesel_form")
    public String getPesel(Model model) {

        model.addAttribute("numbersList", pesel);
        return "pesel_form";
    }

    @PostMapping("/validate_pesel")
    public String submitPesel(@Valid @ModelAttribute("numbersList") Pesel pesel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "pesel_form";
        }
        else {
            peselService.readSerial(pesel);
            peselService.readSex(pesel);
            peselService.readBirthday(pesel);
            peselValidationService.Validate(pesel);

           return "/pesel_validated";
        }
    }
}
