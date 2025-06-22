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
public class Illustration {
    @Id
    @GeneratedValue
    private Long id;

    private byte[] thumbnail;
    private String storagePath;
    private String prompt;
    private String style;

    @ManyToOne
    @JoinColumn(name = "sentence_id")
    private Sentence sentence;

    @ManyToMany
    @JoinTable(
            name = "illustration_entities",
            joinColumns = @JoinColumn(name = "illustration_id"),
            inverseJoinColumns = @JoinColumn(name = "entity_id")
    )
    private Set<IllustratableEntity> linkedEntities = new HashSet<>();
}
