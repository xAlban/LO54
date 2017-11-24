/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burattoelezi.lo54projet.core;

/**
 *
 * @author aelez
 */
public class Course_Session {
    private Integer id;
    private String title;
    private String type;
    private Integer numcopie;
    private Course fkCourse = new Course();
    private Location fkLocation = new Location();

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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the numcopie
     */
    public Integer getNumcopie() {
        return numcopie;
    }

    /**
     * @param numcopie the numcopie to set
     */
    public void setNumcopie(Integer numcopie) {
        this.numcopie = numcopie;
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
