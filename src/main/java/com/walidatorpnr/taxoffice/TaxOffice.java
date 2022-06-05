package com.walidatorpnr.taxoffice;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "TaxOffice")
public class TaxOffice {

    public TaxOffice(){}

    public TaxOffice(int id, String name, int city_id, int voivodeship_id, int details_id){
        this.id = id;
        this.name = name;
        this.voivodeship_id = voivodeship_id;
    }

    @Id
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="voivodeship_id")
    private int voivodeship_id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "details_id")
    private TaxOffice_Detail taxOffice_details;

}
