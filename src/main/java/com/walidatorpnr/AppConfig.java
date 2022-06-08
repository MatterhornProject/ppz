package com.walidatorpnr;

import com.google.common.collect.Lists;
import com.walidatorpnr.nip.Nip;
import com.walidatorpnr.pesel.Pesel;
import com.walidatorpnr.regon.Regon;
import com.walidatorpnr.taxoffice.City;
import com.walidatorpnr.taxoffice.TaxOffice;
import com.walidatorpnr.taxoffice.TaxOffice_Detail;
import com.walidatorpnr.taxoffice.Voivodeship;
import com.walidatorpnr.utils.NumbersStringBuilder;
import com.walidatorpnr.utils.SumOfProductCounter;
import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    @Bean(name = "Regon9") //REGON 9-cyfrowy
    public Regon createRegon9(){
        List<Integer> numbersList9 = Lists.newArrayList(1,2,3,4,5,6,7,8,5);
        List<Integer> numbersList14 = Lists.newArrayList(null,null,null,null,null);
        int[] weights = new int[]{8, 9, 2, 3, 4, 5, 6, 7};
        return new Regon(numbersList9, numbersList14, weights);
    }
    @Bean(name = "Regon14") //REGON 14-cyfrowy
    public Regon createRegon14(){
        List<Integer> numbersList9 = Lists.newArrayList(1,2,3,4,5,6,7,8,5);
        List<Integer> numbersList14 = Lists.newArrayList(1, 2, 3, 4, 7);
        int[] weights = new int[]{2,4,8,5,0,9,7,3,6,1,2,4,8};
        return new Regon(numbersList9, numbersList14, weights);
    }
    @Bean(name = "Regon0")
    public Regon createRegon0(){
        List<Integer> numbersList9 = Lists.newArrayList(0,0,0,0,0,0,0,0,0);
        List<Integer> numbersList14 = Lists.newArrayList(null, null, null, null, null);
        int[] weights = new int[]{2,4,8,5,0,9,7,3,6,1,2,4,8};
        return new Regon(numbersList9, numbersList14,weights);
    }
    @Bean(name = "Pesel0")
    //@Profile("Test")
    public Pesel CreateTestPesel(){
       // Pesel peselTest = new Pesel(Lists.newArrayList(2,1,2,2,2,2,2,2,2,2,2));
       // Pesel peselTest =
       // peselTest.setNumbersList(Lists.newArrayList(2,1,2,2,2,2,2,2,2,2,2));
        //peselTest.setWeights(new int[]{1, 3, 7, 9, 1, 3, 7, 9, 1, 3});
       // peselTest.setControl_number(2);
        return new Pesel(Lists.newArrayList(0,0,0,0,0,0,0,0,0,0,0));
    }

    @Bean(name = "Nip0")
    public Nip Nip0(){

        Nip nip0 = new Nip();
        nip0.setNumbersList(Lists.newArrayList(0,0,0,0,0,0,0,0,0,0));
        return nip0;
    }
    @Bean
    public NumbersStringBuilder createNumbersStringBuilder(){
        return new NumbersStringBuilder();
    }

    @Bean
    public SumOfProductCounter createSumOfProductsCounter(){
        return new SumOfProductCounter();
    }

    @Bean
    public Voivodeship createVoivodeship(){
        return new Voivodeship();
    }
    @Bean
    public City createCity(){
        return new City();
    }
    @Bean
    public TaxOffice createTaxOffice(){
        return new TaxOffice();
    }
    @Bean
    public TaxOffice_Detail createTaxOffice_Detail(){
        return new TaxOffice_Detail();
    }

    @Bean
    public PhysicalNamingStrategy physical() {
        return new PhysicalNamingStrategyStandardImpl();
    }

    @Bean
    public ImplicitNamingStrategy implicit() {
        return new ImplicitNamingStrategyLegacyJpaImpl();
    }
}
