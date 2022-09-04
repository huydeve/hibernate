package com.huydev.hibernatetutorial.model;

import com.huydev.hibernatetutorial.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor

@Entity
@Table(name = "Groups")
@NamedQueries({
        @NamedQuery(name = Constants.GROUP_BY_NAME, query = "FROM Group WHERE name = :name")
})
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY) //second level caching will cache data
// from first query so that when second query just get query from cache
public class Group {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String name;
    @ManyToMany
    private Set<Fresher> freshers = new HashSet<>();

    public Group() {
        System.out.println("Create Group");
    }

    public Group(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", freshers=" + freshers +
                '}';
    }
}
