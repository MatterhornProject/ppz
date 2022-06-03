package com.walidatorpnr.regon;

import com.google.common.collect.Lists;
import com.walidatorpnr.utils.NumbersStringBuilder;
import com.walidatorpnr.utils.SumOfProductCounter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RegonService {

   private final NumbersStringBuilder numbersStringBuilder;
   private final SumOfProductCounter sumOfProductCounter;

   private Regon regon9;
   private Regon regon14;

    public RegonService(@Qualifier("Regon9") Regon regon9, @Qualifier("Regon14") Regon regon14, NumbersStringBuilder numbersStringBuilder, SumOfProductCounter sumOfProductCounter) {
        this.regon9 = regon9;
        this.regon14 = regon14;
        this.numbersStringBuilder = numbersStringBuilder;
        this.sumOfProductCounter = sumOfProductCounter;
    }


    public Regon MergeRegonNumbers(Regon regon) {

        List<Integer> numbers = Lists.newArrayList();
        if (regon.getNumbersList14().stream().allMatch(Objects::nonNull)) {
            numbers.addAll(regon.getNumbersList9());
            numbers.addAll(regon.getNumbersList14());
            regon.setNumbersList(numbers);
            regon.setWeights(regon14.getWeights());
            return regon;
        } else {
            regon.setNumbersList(regon.getNumbersList9());
            regon.setWeights(regon9.getWeights());
            return regon;
        }
    }

    public String readVovoivodeshipNumber(Regon regon) { //2 pierwsze cyfry nowego REGONU 9-cyfrowego

        String s = String.valueOf(regon.getNumbersList().get(0)) + String.valueOf(regon.getNumbersList().get(1));
        if (s.equals("00")) {
            return s + " : Brak(stary numer REGON)";
        } else {
            return s;
        }
    }
        public String readSerialNumber(Regon regon){ ////cyfry 3-8 REGONU 9-cyfrowego
            return numbersStringBuilder.Build(2, 8, regon.getNumbersList());
        }

        public String readLegalEntityID(Regon regon){//9 pierwszych cyfr REGONU 14-cyfrowego, Numer identyfikacyjny REGON osoby prawnej, jednostki organizacyjnej nie mającej osobowości prawnej lub osoby fizycznej prowadzącej działalność gospodarczą,
            return numbersStringBuilder.Build(0,9, regon.getNumbersList());
        }

        public String readLocalUnitOrdinalNumber(Regon regon){ //10-13 cyfry REGONU 14-cyfrowego, Liczbą porządkowa przypisaną jednostce lokalnej
            return numbersStringBuilder.Build(9,13, regon.getNumbersList());
        }

        public int readControlNumber(Regon regon, int index){ //cufra kontrolna - ostatnia cyfra każdego regonu
        regon.setControl_number(regon.getNumbersList().get(index));
             return regon.getControl_number();
        }

        public int GetSumOfProducts(Regon regon){  //cyfry 1-8 REGONU 9-cyfrowego * wagi lub cyfry 1-13 REGONU 14-cyfrowego * wagi
            return sumOfProductCounter.Count(regon.getNumbersList(), regon.getWeights());
        }
      public String GetValidationResult(Regon regon, int index){

        if(GetSumOfProducts(regon)%11 == readControlNumber(regon,index)){
            return "poprawny";
        }
        else{
            return "błędny!";
          }

    }
    public Regon Validate(Regon regon){

        regon = MergeRegonNumbers(regon);
        if(regon.getNumbersList().size()==9){
            regon.setSerial_number(readSerialNumber(regon));
            regon.setVoivodeship_code(readVovoivodeshipNumber(regon));
            regon.setResult(GetValidationResult(regon, 8));
            return regon;
        }
        if (regon.getNumbersList().size()==14){
            regon.setLegal_entity_id(readLegalEntityID(regon));
            regon.setLocal_unit_ordinal_number(readLocalUnitOrdinalNumber(regon));
            regon.setResult(GetValidationResult(regon, 13));
            return regon;
        }
        return regon;
    }
    }
