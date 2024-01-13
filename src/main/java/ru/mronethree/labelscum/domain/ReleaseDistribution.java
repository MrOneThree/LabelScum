package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
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
public class ReleaseDistribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "release_id", nullable = false)
    private Release release;

    @ManyToOne
    @JoinColumn(name = "music_distributor_id")
    private MusicDistributor musicDistributor;

}
