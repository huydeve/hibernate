package com.huydev.hibernatetutorial.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fresher {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    @OneToOne
    private Address address;

    @OneToMany
    private List<Course> courses;
    @ManyToMany
    private Set<Group> groups = new HashSet<>();

    public Fresher(String name, Address address, List<Course> courses) {
        this.name = name;
        this.address = address;
        this.courses = courses;
    }
}

