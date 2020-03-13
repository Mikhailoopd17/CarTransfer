package com.project.Entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="points")
public class Point {
    private Long id;
    private String name;

    private Collection<Car> cars;

    public Point() {
    }
    public Point(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "point", fetch = FetchType.LAZY)
    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
}
