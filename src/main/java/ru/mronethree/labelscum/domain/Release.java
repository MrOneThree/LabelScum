package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.DETACH, optional = false)
    @JoinColumn(name = "sub_label_id", nullable = false)
    private Label label;

    @Column(nullable = false)
    @NotBlank
    private String catalog;
    @Column(nullable = false)
    @NotBlank
    private String releaseName;
    @Column(nullable = false)
    @NotNull
    private LocalDate releaseDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "release", orphanRemoval = true)
    private Set<ReleaseArtists> releaseArtists = new HashSet<>();

    @OneToMany(mappedBy = "release", orphanRemoval = true)
    private Set<ReleaseExpense> releaseExpenses = new HashSet<>();

}
