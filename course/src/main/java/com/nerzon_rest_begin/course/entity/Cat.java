package com.nerzon_rest_begin.course.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cats")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    private int age;

    private double weight;

    public Cat(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

}





