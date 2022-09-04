package com.huydev.hibernatetutorial.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String name;

//    add @Transient to pass attribute;

    @Temporal(TemporalType.DATE)
    private Date createDate;
//    @Embedded
//    private Syllabus syllabus;



//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "content", column = @Column(name = "meme_content")),
//            @AttributeOverride(name = "duration", column = @Column(name = "meme_duration")),
//
//    })
//    private Syllabus offlineSyllabus;

    @ElementCollection
    private List<Syllabus> syllabusList;

    public Course(String name, Date createDate, List<Syllabus> syllabusList) {
        this.name = name;
        this.createDate = createDate;
        this.syllabusList = syllabusList;
    }

//    public Course(String name, Date createDate, Syllabus syllabus, Syllabus offlineSyllabus) {
//        this.name = name;
//        this.createDate = createDate;
//        this.syllabus = syllabus;
//        this.offlineSyllabus = offlineSyllabus;
//    }
//    public Course(String name, Date createDate, Syllabus syllabus) {
//        this.name = name;
//        this.createDate = createDate;
//        this.syllabus = syllabus;
//    }

    public Course(String name, Date createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    public Course() {
    }

    public Course( String name) {
        this.name = name;
    }


//
//    public Syllabus getSyllabus() {
//        return syllabus;
//    }
//
//    public void setSyllabus(Syllabus syllabus) {
//        this.syllabus = syllabus;
//    }

    public Date getCreateDate() {
        return createDate;
    }



    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Syllabus getOfflineSyllabus() {
//        return offlineSyllabus;
//    }
//
//    public void setOfflineSyllabus(Syllabus offlineSyllabus) {
//        this.offlineSyllabus = offlineSyllabus;
//    }

    public List<Syllabus> getSyllabusList() {
        return syllabusList;
    }

    public void setSyllabusList(List<Syllabus> syllabusList) {
        this.syllabusList = syllabusList;
    }
}
