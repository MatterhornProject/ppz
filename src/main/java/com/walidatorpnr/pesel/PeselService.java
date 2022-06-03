package com.walidatorpnr.pesel;

import com.walidatorpnr.utils.NumbersStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PeselService {

    private final NumbersStringBuilder numbersStringBuilder;

    private Pesel pesel;

    @Autowired
    public PeselService(NumbersStringBuilder numbersStringBuilder, Pesel pesel) {
        this.numbersStringBuilder = numbersStringBuilder;
        this.pesel = pesel;
    }

    public Pesel readSerial(Pesel pesel){//cyfry 7-10 oznaczają numer seryjny
        pesel.setSerial_number(numbersStringBuilder.Build(6,10, pesel.getNumbersList()));
        return pesel;
    }

    public Pesel readSex(Pesel pesel) { //10 cyfra oznacza płeć

        if (pesel.getNumbersList().get(9) % 2 == 0) {  //1,3,5,7,9 = mężczyzna,
            pesel.setSex("Kobieta");
            return pesel;
        }
        if (pesel.getNumbersList().get(9) % 2 != 0) { //  0,2,4,6,8 = kobieta
            pesel.setSex("Mężczyzna");
            return pesel;
        }
        return null;
    }
    public Pesel readBirthday(Pesel pesel){ //cyfry 1-6 oznaczają datę urodzenia

        List<Integer> numbersList = pesel.getNumbersList();

        pesel.setDay((numbersList.get(4)*10) + numbersList.get(5)); //5 i 6 cyfra peselu oznaczają dzień

        //3 i 4 cyfra peselu oznaczają miesiąc
        if(numbersList.get(2)==8 || numbersList.get(2)==9){ //jeśli pierwsza cyfra miesiąca to 8 lub 9
            pesel.setYear(1800 + (numbersList.get(0)*10) + numbersList.get(1)); //rok urodzenia to 1800 + dwie pierwsze cyfry peselu(liczba dwucyfrowa)
            pesel.setMonth(((numbersList.get(2)*10) + numbersList.get(3)) - 80); //miesiąc to 3 i 4 cyfra peselu(liczba dwucyfrowa)-80
        }
        if(numbersList.get(2)==0 || numbersList.get(2)==1){ //jeśli pierwsza cyfra miesiąca to 0 lub 1
            pesel.setYear(1900 + (numbersList.get(0)*10) + numbersList.get(1)); //rok urodzenia to 1900 + dwie pierwsze cyfry peselu(liczba dwucyfrowa)
            pesel.setMonth((numbersList.get(2)*10) + numbersList.get(3)); //miesiąc to 3 i 4 cyfra peselu(liczba dwucyfrowa)
        }
        if(numbersList.get(2)==2 || numbersList.get(2)==3){ //jeśli pierwsza cyfra miesiąca to 2 lub 3
            pesel.setYear(2000 + (numbersList.get(0)*10) + numbersList.get(1)); //rok urodzenia to 2000 + dwie pierwsze cyfry peselu(liczba dwucyfrowa)
            pesel.setMonth(((numbersList.get(2)*10) + numbersList.get(3)) - 20); //miesiąc to 3 i 4 cyfra peselu(liczba dwucyfrowa)-20
        }
        if(numbersList.get(2)==4 || numbersList.get(2)==5){ //jeśli pierwsza cyfra miesiąca to 4 lub 5
            pesel.setYear(2100 + (numbersList.get(0)*10)+ numbersList.get(1)); //rok urodzenia to 2100 + dwie pierwsze cyfry peselu(liczba dwucyfrowa)
            pesel.setMonth(((numbersList.get(2)*10) + numbersList.get(3)) - 40); //miesiąc to 3 i 4 cyfra peselu(liczba dwucyfrowa)-40
        }
        if(numbersList.get(2)==6 || numbersList.get(2)==7){ //jeśli pierwsza cyfra miesiąca to 6 lub 7
            pesel.setYear(2200 + (numbersList.get(0)*10) + numbersList.get(1)); //rok urodzenia to 2200 + dwie pierwsze cyfry peselu(liczba dwucyfrowa)
            pesel.setMonth(((numbersList.get(2)*10) + numbersList.get(3)) - 60);//miesiąc to 3 i 4 cyfra peselu(liczba dwucyfrowa)-60
        }

        String DatePattern = "dd.MM.yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DatePattern);
        pesel.setBirthday(dateFormatter.format(LocalDate.of(pesel.getYear(), pesel.getMonth(), pesel.getDay())));

        return pesel;
    }



}
