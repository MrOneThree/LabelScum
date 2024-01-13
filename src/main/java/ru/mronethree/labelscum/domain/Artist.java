package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
/**
 * @author Kirill Popov
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String alias;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "artist", orphanRemoval = true)
    private Set<ReleaseArtists> releases = new HashSet<>();

}
