package com.example.bookillustrator.repository;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public abstract class IllustratableEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;

    @ManyToMany(mappedBy = "linkedEntities")
    private Set<Illustration> illustrations = new HashSet<>();
}
