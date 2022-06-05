package com.walidatorpnr.taxoffice;


import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "Voivodeship")
public class Voivodeship {


    public Voivodeship(){}

    public Voivodeship(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "voivodeship")
    private List<City> cities;


}
