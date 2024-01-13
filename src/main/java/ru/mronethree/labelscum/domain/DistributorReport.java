package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Kirill Popov
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DistributorReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "music_distributor_id", nullable = false)
    private MusicDistributor musicDistributor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @NotNull
    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "curr", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private Curr currency;

}
