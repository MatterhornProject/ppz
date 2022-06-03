package com.walidatorpnr.pesel;

import com.google.common.collect.Lists;
import com.walidatorpnr.utils.NumbersStringBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PeselServiceTest {

    @Autowired
    NumbersStringBuilder numbersStringBuilder;

    private PeselService peselService;

    @Autowired
    Pesel pesel;

    @BeforeEach
    void init(){
        this.peselService = new PeselService(numbersStringBuilder, pesel);
        pesel.setNumbersList(Lists.newArrayList(8, 8, 1, 0, 1, 1, 0, 3, 9, 6, 3));
    }

    @Test
    void ReadSerialTest(){
        Pesel result = peselService.readSerial(pesel);
        Assertions.assertEquals("0396", result.getSerial_number());
    }
    @Test
    void ReadSexTest(){
        Pesel result = peselService.readSex(pesel);
        Assertions.assertEquals("Kobieta", result.getSex());
    }
    @Test
    void ReadBirthday80(){
        pesel.setNumbersList(Lists.newArrayList(0, 2, 9, 0, 1, 1, 0, 3, 9, 6, 3));
        Pesel result = peselService.readBirthday(pesel);
        Assertions.assertEquals("11.10.1802", result.getBirthday());
    }
    @Test
    void ReadBirthdayTest90(){
        Pesel result = peselService.readBirthday(pesel);
        Assertions.assertEquals("11.10.1988", result.getBirthday());
    }
    @Test
    void ReadBirthday20(){
       pesel.setNumbersList(Lists.newArrayList(0, 2, 3, 0, 1, 1, 0, 3, 9, 6, 3));
        Pesel result = peselService.readBirthday(pesel);
        Assertions.assertEquals("11.10.2002", result.getBirthday());
    }
    @Test
    void ReadBirthday21(){
        pesel.setNumbersList(Lists.newArrayList(0, 0, 5, 0, 1, 1, 0, 3, 9, 6, 3));
        Pesel result = peselService.readBirthday(pesel);
        Assertions.assertEquals("11.10.2100", result.getBirthday());
    }
    @Test
    void ReadBirthday22(){
        pesel.setNumbersList(Lists.newArrayList(0, 0, 7, 0, 1, 1, 0, 3, 9, 6, 3));
        Pesel result = peselService.readBirthday(pesel);
        Assertions.assertEquals("11.10.2200", result.getBirthday());
    }
}
