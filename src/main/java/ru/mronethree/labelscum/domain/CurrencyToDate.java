package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

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
    private Currency currency;
    @NotNull
    @Column(nullable = false)
    private BigDecimal rateToRub;
}
