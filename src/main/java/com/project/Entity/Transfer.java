package com.project.Entity;

import org.hibernate.annotations.NotFound;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "transfers")
public class Transfer {
    private Long id;

    /*поле Арендатор(tenant) лучше сделать через отдельный объект
     и хранить в собственной таблице, в данном проекте
     эта реализация опущена для упрощения */
    private String tenant;
    private Car car;
    private Date dateBegin;
    private Date dateEnd;
    private String pointBegin;
    private String pointEnd;
    private Float summ;

    public Transfer() {
    }

    public Transfer(String tenant, Car car, String dateBegin,
                    String dateEnd, String pointEnd) throws ParseException {
        this.tenant = tenant;
        this.car = car;
        setDateBegin(dateBegin);
        setDateEnd(dateEnd);
        this.pointBegin = car.getPoint().getName();
        this.pointEnd = pointEnd;
        setSumm();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cars_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDateBegin() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.dateBegin);
    }

    public void setDateBegin(String dateBegin) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.dateBegin = dateFormat.parse(dateBegin);
    }

    public String getDateEnd() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.dateEnd);
    }

    public void setDateEnd(String dateEnd) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.dateEnd = dateFormat.parse(dateEnd);
    }

    public String getPointBegin() {
        return pointBegin;
    }

    public void setPointBegin(String pointBegin) {
        this.pointBegin = pointBegin;
    }

    public String getPointEnd() {
        return pointEnd;
    }

    public void setPointEnd(String pointEnd) {
        this.pointEnd = pointEnd;
    }

    public Float getSumm() {
        return summ;
    }

    public void setSumm(float summ) {
        this.summ = summ;
    }

    public void setSumm() {
        this.summ = this.car.getTariff().getValue() * this.dayTransfer();
    }

    public int dayTransfer() {
        long period = this.dateEnd.getTime() - this.dateBegin.getTime();
        return (int) Math.floor(period / 86400000);
    }
}
