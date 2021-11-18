package me.lzycodr.lab_9.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Document {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String uri;
}
