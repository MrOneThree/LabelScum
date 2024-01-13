package ru.mronethree.labelscum.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mronethree.labelscum.domain.CurrencyToDate;
import ru.mronethree.labelscum.domain.cbr.ValCurs;

import java.util.List;

/**
 * @author Kirill Popov
 */
@Component
@RequiredArgsConstructor
public class XmlParser {

    public List<CurrencyToDate> parseCurrencies(String xmlString){
        XmlMapper mapper = new XmlMapper();
        ValCurs valCurs;
        try {
            valCurs = mapper.readValue(xmlString, ValCurs.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
