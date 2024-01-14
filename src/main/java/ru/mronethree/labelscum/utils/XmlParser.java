package ru.mronethree.labelscum.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mronethree.labelscum.domain.Curr;
import ru.mronethree.labelscum.domain.CurrencyToDate;
import ru.mronethree.labelscum.domain.cbr.ValCurs;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author Kirill Popov
 */
@Component
@RequiredArgsConstructor
public class XmlParser {

    public List<CurrencyToDate> parseCurrencies(String xmlString, LocalDate date){
        XmlMapper mapper = new XmlMapper();
        ValCurs valCurs;
        try {
            valCurs = mapper.readValue(xmlString, ValCurs.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return valCurs.getValutes().stream()
                .filter(valute -> Arrays.stream(Curr.values()).anyMatch(e -> e.name().equals(valute.getCharCode())))
                .map(v -> CurrencyToDate.builder()
                        .currency(Curr.valueOf(v.getCharCode()))
                                .date(date)
                                .rateToRub(getBigDecimalFormatNumber(v.getValue()))
                                .build()
                        )
                .toList();
    }

    private BigDecimal getBigDecimalFormatNumber(String value) {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number;
        try {
            number = format.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return BigDecimal.valueOf(number.doubleValue());
    }
}
