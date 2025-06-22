package com.example.bookillustrator.repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Sentence {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "paragraph_id")
    private Paragraph paragraph;

    @OneToMany(mappedBy = "sentence")
    private List<Illustration> illustrations = new ArrayList<>();
}
