package com.walidatorpnr.taxoffice;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "TaxOffice_Details")
public class TaxOffice_Detail {

    public TaxOffice_Detail() {}

    public TaxOffice_Detail(int id, String street, String zipcode, String city, String phone, String fax, String email, String www) {
        this.id = id;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.www = www;
    }

    @Id
    private int id;

    @Column(name = "street")
    private String street;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "www")
    private String www;

    @Transient
    private String taxoffice_name;

    @OneToMany(mappedBy = "taxOffice_details")
    private List<TaxOffice> taxOffices;

}
