package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "sub_label_id", nullable = false)
    private SubLabel subLabel;

    @Column(nullable = false)
    @NotBlank
    private String releaseCode;
    @Column(nullable = false)
    @NotBlank
    private String releaseName;
    @Column(nullable = false)
    @NotNull
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "release", orphanRemoval = true)
    private Set<ReleaseArtists> releaseArtists = new HashSet<>();

    @OneToMany(mappedBy = "release", orphanRemoval = true)
    private Set<ReleaseExpense> releaseExpenses = new HashSet<>();

}
