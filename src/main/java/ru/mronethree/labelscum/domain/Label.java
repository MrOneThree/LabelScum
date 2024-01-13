package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author Kirill Popov
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @NotEmpty
    private String name;
}
