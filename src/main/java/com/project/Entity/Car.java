package com.project.Entity;

import javax.persistence.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Car(String mark, String number, Point point, Tariff tariff) {
        this.mark = mark;
        this.number = number;
        this.point = point;
        this.tariff = tariff;
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

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "points_id")
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    public Collection<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(Collection<Transfer> transfers) {
        this.transfers = transfers;
    }

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tariffs_id")
    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }


    //метод для получения среднего времени проката по той или иной точке
    public Set<String> MediumTransferTime() {
        List<Transfer> listT = new ArrayList<>(transfers);
        Set<String> message = new HashSet<>();
        if(!listT.isEmpty()) {
            for (Transfer var1 : listT) {
                int sum = 0;
                float rezult = 0f;
                List<Integer> temp = new ArrayList<>();
                for (Transfer var2 : listT) {
                    if (var1.getPointBegin().equals(var2.getPointBegin())) {
                        temp.add(var2.dayTransfer());
                    }
                }
                if (!temp.isEmpty()) {
                    for (int i : temp) {
                        sum += i;
                    }
                    rezult = sum / temp.size();
                }
                message.add(rezult + " дней в точке " + var1.getPointBegin());
            }
        }
//        else
//            message.add("Машина не участвовала в прокате");
        return message;
    }
    public void toTransfer(Point end){
        this.point = end;
    }

    public Float calculateProfitCar(){
        float summ = 0f;
        if(transfers.size()>0){
            for (Transfer t:transfers) {
                summ+=t.getSumm();
            }
        }
        return summ;
    }

    public String toString(){
        return this.mark + " " + this.number;
    }
}
