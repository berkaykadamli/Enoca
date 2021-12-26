package com.example.enoca.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String email;

    @NotNull
    private int salary;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "project_id",referencedColumnName = "id")
    private Company company;

    public Employee(String name, String surname, int salary ,String email) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.email = email;
    }
}
