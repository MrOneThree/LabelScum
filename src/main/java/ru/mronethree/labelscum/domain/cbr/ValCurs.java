package ru.mronethree.labelscum.domain.cbr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Kirill Popov
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValCurs {
    @JsonProperty("Date")
    private String date;
    private String name;
    @JsonProperty("Valutes")
    private List<Valute> valutes = new ArrayList<>();
}
