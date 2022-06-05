package com.walidatorpnr.taxoffice;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "City")
public class City {


    public City() {

    }

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "voivodeship_id")
    private
    Voivodeship voivodeship;

    @OneToMany(mappedBy = "city")
    private List<TaxOffice> taxOffices;





}
