package com.project.Entity;

import javax.persistence.*;
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

    private Point pointBegin;
    private Point pointEnd;
    private Float summ;

    public Transfer() {
    }

    public Transfer(String tenant, Car car, Date dateBegin,
                    Date dateEnd, Point pointEnd) {
        this.tenant = tenant;
        this.car = car;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.pointBegin = car.getPoint();
        this.pointEnd = pointEnd;
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

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "cars_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Point gPointBegin() {
        return pointBegin;
    }

    public void sPointBegin() {
        this.pointBegin = car.getPoint();
    }

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "points_id")
    public Point getPointEnd() {
        return pointEnd;
    }

    public void setPointEnd(Point pointEnd) {
        this.pointEnd = pointEnd;
    }
}
