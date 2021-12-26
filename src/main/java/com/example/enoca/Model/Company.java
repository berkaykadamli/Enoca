package com.example.enoca.Model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String website;

    @NotNull
    private String location;

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name = "project_id")
    private List<Employee> employees;

    public Company(String name, String website, String location) {
        this.name = name;
        this.website = website;
        this.location = location;
    }
}

