package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class CurrencyToDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Column(nullable = false)
    private BigDecimal rateToRub;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    @JdbcTypeCode(SqlTypes.NVARCHAR)
    private Curr currency;

}
