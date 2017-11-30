/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burattoelezi.lo54projet.core.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="COURSE_SESSION")
public class Course_Session {
    @Id @GeneratedValue
    private Integer id;
    @Column(name="START_DATE")
    private Date startDate;
    @Column(name="END_DATE")
    private Date endDate;
    @ManyToOne
    private Course fkCourse;
    @ManyToOne
    private Location fkLocation;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the fkCourse
     */
    public Course getFkCourse() {
        return fkCourse;
    }

    /**
     * @param fkCourse the fkCourse to set
     */
    public void setFkCourse(Course fkCourse) {
        this.fkCourse = fkCourse;
    }

    /**
     * @return the fkLocation
     */
    public Location getFkLocation() {
        return fkLocation;
    }

    /**
     * @param fkLocation the fkLocation to set
     */
    public void setFkLocation(Location fkLocation) {
        this.fkLocation = fkLocation;
    }
}
