package com.example.bookillustrator.repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Paragraph {
    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @JsonManagedReference
    @OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL)
    private List<Sentence> sentences = new ArrayList<>();
}
