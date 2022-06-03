package com.walidatorpnr.regon;

//import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RegonController {

    final Logger logger= LoggerFactory.getLogger(RegonController.class);
    private final RegonService regonService;

    @Qualifier("Regon14")
    @Autowired
    private Regon regon;

    @Qualifier("Regon0")
    @Autowired
    private Regon regon2;


    public RegonController(RegonService regonService) {
        this.regonService = regonService;
        this.regon = regon2;
      //  this.regon2 = regon2;

    }

    @GetMapping("/regon_form")
    public String getRegon(Model model) {

        model.addAttribute("numbersList", regon);
        return "regon_form";
    }

    @PostMapping("/validate_regon")
    public String submitRegon(@Valid @ModelAttribute("numbersList") Regon regon, BindingResult bindingResult)  {

        System.out.println("Regon9: "+ regon.getNumbersList9() +  "Regon14 " + regon.getNumbersList14() );
        logger.debug("BindingResult: " + bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println("There were errors");
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getObjectName() + " " + error.getDefaultMessage());
            });
            return "regon_form";

        }else {
            regonService.Validate(regon);
            return "/regon_validated";
        }
    }
}
