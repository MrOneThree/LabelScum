package ru.mronethree.labelscum.domain.cbr;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kirill Popov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valute {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("NumCode")
    private Integer numCode;
    @JsonProperty("CharCode")
    private String charCode;
    @JsonProperty("Nominal")
    private Integer nominal;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private String value;
    @JsonProperty("VunitRate")
    private String vUnitRate;

}
