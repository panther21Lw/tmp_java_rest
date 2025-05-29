package com.nerzon_rest_begin.course.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cats")
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

    public Cat() {}

    @Override
    public String toString() {
        return "Cat [id=" + id + ", name=" + name + ", age=" + age + ", weight=" + weight + "]";
    }
}





