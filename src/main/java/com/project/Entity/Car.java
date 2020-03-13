package com.project.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    private Long id;
    private String mark;
    private String number;
    private Point point;
    private Tariff tariff;

    private Collection<Transfer> transfers;

    public Car() {
    }

    public Car(String mark, String number, Point point) {
        this.mark = mark;
        this.number = number;
        this.point = point;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "points_id")
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    public Collection<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(Collection<Transfer> transfers) {
        this.transfers = transfers;
    }



    public String MediumTransferTime(){
        if(transfers.size()>0){
            long period = 0L;
            for (Transfer tr:transfers) {
                period += (tr.getDateEnd().getTime() - tr.getDateBegin().getTime());
            }
            float rezult = period/(transfers.size()*60000);
            int hours = (int) Math.floor(rezult/60);
            int minutes = (int) Math.floor(rezult%60);
            return "Средняя продолжительность проката автомобиля марки "+
                    this.mark+" составила "+hours+"часов и "+minutes+"минут";
        }
        else
            return "Машина не бралась в прокат";

    }
}
