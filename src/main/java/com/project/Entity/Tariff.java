package com.project.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tariffs")
public class Tariff {
    private Long id;
    private Float value;
    private String description;

    private Collection<Car> cars;

    public Tariff() {
    }

    public Tariff(Float value, String description) {
        this.value = value;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "tariff", fetch = FetchType.LAZY)
    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
