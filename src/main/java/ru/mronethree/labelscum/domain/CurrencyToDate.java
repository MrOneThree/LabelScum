package ru.mronethree.labelscum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Kirill Popov
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"date", "currency"})
})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CurrencyToDate that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(rateToRub, that.rateToRub) && currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, rateToRub, currency);
    }

    @Override
    public String toString() {
        return "CurrencyToDate{" +
                "id=" + id +
                ", date=" + date +
                ", rateToRub=" + rateToRub +
                ", currency=" + currency +
                '}';
    }
}
