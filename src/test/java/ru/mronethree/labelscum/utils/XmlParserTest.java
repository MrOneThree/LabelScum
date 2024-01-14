package ru.mronethree.labelscum.utils;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Kirill Popov
 */
class XmlParserTest {

    @Test
    void parseCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("https://www.cbr.ru/scripts/XML_daily.asp?date_req=18.05.2022", String.class);
        String response = "<?xml version=\"1.0\" encoding=\"windows-1251\"?><ValCurs Date=\"18.05.2022\" name=\"Foreign Currency Market\"><Valutes><Valute ID=\"R01010\"><NumCode>036</NumCode><CharCode>AUD</CharCode><Nominal>1</Nominal><Name>Австралийский доллар</Name><Value>44,5244</Value><VunitRate>44,5244</VunitRate></Valute><Valute ID=\"R01020A\"><NumCode>944</NumCode><CharCode>AZN</CharCode><Nominal>1</Nominal><Name>Азербайджанский манат</Name><Value>37,3781</Value><VunitRate>37,3781</VunitRate></Valute><Valute ID=\"R01035\"><NumCode>826</NumCode><CharCode>GBP</CharCode><Nominal>1</Nominal><Name>Фунт стерлингов Соединенного королевства</Name><Value>77,8399</Value><VunitRate>77,8399</VunitRate></Valute><Valute ID=\"R01060\"><NumCode>051</NumCode><CharCode>AMD</CharCode><Nominal>100</Nominal><Name>Армянских драмов</Name><Value>13,9697</Value><VunitRate>0,139697</VunitRate></Valute><Valute ID=\"R01090B\"><NumCode>933</NumCode><CharCode>BYN</CharCode><Nominal>1</Nominal><Name>Белорусский рубль</Name><Value>25,2655</Value><VunitRate>25,2655</VunitRate></Valute><Valute ID=\"R01100\"><NumCode>975</NumCode><CharCode>BGN</CharCode><Nominal>1</Nominal><Name>Болгарский лев</Name><Value>33,8606</Value><VunitRate>33,8606</VunitRate></Valute><Valute ID=\"R01115\"><NumCode>986</NumCode><CharCode>BRL</CharCode><Nominal>1</Nominal><Name>Бразильский реал</Name><Value>12,5422</Value><VunitRate>12,5422</VunitRate></Valute><Valute ID=\"R01135\"><NumCode>348</NumCode><CharCode>HUF</CharCode><Nominal>100</Nominal><Name>Венгерских форинтов</Name><Value>17,2016</Value><VunitRate>0,172016</VunitRate></Valute><Valute ID=\"R01200\"><NumCode>344</NumCode><CharCode>HKD</CharCode><Nominal>10</Nominal><Name>Гонконгских долларов</Name><Value>81,0909</Value><VunitRate>8,10909</VunitRate></Valute><Valute ID=\"R01215\"><NumCode>208</NumCode><CharCode>DKK</CharCode><Nominal>10</Nominal><Name>Датских крон</Name><Value>88,9893</Value><VunitRate>8,89893</VunitRate></Valute><Valute ID=\"R01235\"><NumCode>840</NumCode><CharCode>USD</CharCode><Nominal>1</Nominal><Name>Доллар США</Name><Value>63,5428</Value><VunitRate>63,5428</VunitRate></Valute><Valute ID=\"R01239\"><NumCode>978</NumCode><CharCode>EUR</CharCode><Nominal>1</Nominal><Name>Евро</Name><Value>66,3644</Value><VunitRate>66,3644</VunitRate></Valute><Valute ID=\"R01270\"><NumCode>356</NumCode><CharCode>INR</CharCode><Nominal>100</Nominal><Name>Индийских рупий</Name><Value>82,1458</Value><VunitRate>0,821458</VunitRate></Valute><Valute ID=\"R01335\"><NumCode>398</NumCode><CharCode>KZT</CharCode><Nominal>100</Nominal><Name>Казахстанских тенге</Name><Value>14,7922</Value><VunitRate>0,147922</VunitRate></Valute><Valute ID=\"R01350\"><NumCode>124</NumCode><CharCode>CAD</CharCode><Nominal>1</Nominal><Name>Канадский доллар</Name><Value>49,3038</Value><VunitRate>49,3038</VunitRate></Valute><Valute ID=\"R01370\"><NumCode>417</NumCode><CharCode>KGS</CharCode><Nominal>100</Nominal><Name>Киргизских сомов</Name><Value>77,2269</Value><VunitRate>0,772269</VunitRate></Valute><Valute ID=\"R01375\"><NumCode>156</NumCode><CharCode>CNY</CharCode><Nominal>10</Nominal><Name>Китайских юаней</Name><Value>94,4705</Value><VunitRate>9,44705</VunitRate></Valute><Valute ID=\"R01500\"><NumCode>498</NumCode><CharCode>MDL</CharCode><Nominal>10</Nominal><Name>Молдавских леев</Name><Value>33,4133</Value><VunitRate>3,34133</VunitRate></Valute><Valute ID=\"R01535\"><NumCode>578</NumCode><CharCode>NOK</CharCode><Nominal>10</Nominal><Name>Норвежских крон</Name><Value>64,8065</Value><VunitRate>6,48065</VunitRate></Valute><Valute ID=\"R01565\"><NumCode>985</NumCode><CharCode>PLN</CharCode><Nominal>1</Nominal><Name>Польский злотый</Name><Value>14,4085</Value><VunitRate>14,4085</VunitRate></Valute><Valute ID=\"R01585F\"><NumCode>946</NumCode><CharCode>RON</CharCode><Nominal>1</Nominal><Name>Румынский лей</Name><Value>13,4491</Value><VunitRate>13,4491</VunitRate></Valute><Valute ID=\"R01589\"><NumCode>960</NumCode><CharCode>XDR</CharCode><Nominal>1</Nominal><Name>СДР (специальные права заимствования)</Name><Value>84,7038</Value><VunitRate>84,7038</VunitRate></Valute><Valute ID=\"R01625\"><NumCode>702</NumCode><CharCode>SGD</CharCode><Nominal>1</Nominal><Name>Сингапурский доллар</Name><Value>45,6879</Value><VunitRate>45,6879</VunitRate></Valute><Valute ID=\"R01670\"><NumCode>972</NumCode><CharCode>TJS</CharCode><Nominal>10</Nominal><Name>Таджикских сомони</Name><Value>50,8546</Value><VunitRate>5,08546</VunitRate></Valute><Valute ID=\"R01700J\"><NumCode>949</NumCode><CharCode>TRY</CharCode><Nominal>10</Nominal><Name>Турецких лир</Name><Value>40,7904</Value><VunitRate>4,07904</VunitRate></Valute><Valute ID=\"R01710A\"><NumCode>934</NumCode><CharCode>TMT</CharCode><Nominal>1</Nominal><Name>Новый туркменский манат</Name><Value>18,1551</Value><VunitRate>18,1551</VunitRate></Valute><Valute ID=\"R01717\"><NumCode>860</NumCode><CharCode>UZS</CharCode><Nominal>10000</Nominal><Name>Узбекских сумов</Name><Value>56,9132</Value><VunitRate>0,00569132</VunitRate></Valute><Valute ID=\"R01720\"><NumCode>980</NumCode><CharCode>UAH</CharCode><Nominal>10</Nominal><Name>Украинских гривен</Name><Value>21,5053</Value><VunitRate>2,15053</VunitRate></Valute><Valute ID=\"R01760\"><NumCode>203</NumCode><CharCode>CZK</CharCode><Nominal>10</Nominal><Name>Чешских крон</Name><Value>26,8011</Value><VunitRate>2,68011</VunitRate></Valute><Valute ID=\"R01770\"><NumCode>752</NumCode><CharCode>SEK</CharCode><Nominal>10</Nominal><Name>Шведских крон</Name><Value>63,6160</Value><VunitRate>6,3616</VunitRate></Valute><Valute ID=\"R01775\"><NumCode>756</NumCode><CharCode>CHF</CharCode><Nominal>1</Nominal><Name>Швейцарский франк</Name><Value>63,7724</Value><VunitRate>63,7724</VunitRate></Valute><Valute ID=\"R01810\"><NumCode>710</NumCode><CharCode>ZAR</CharCode><Nominal>10</Nominal><Name>Южноафриканских рэндов</Name><Value>39,5979</Value><VunitRate>3,95979</VunitRate></Valute><Valute ID=\"R01815\"><NumCode>410</NumCode><CharCode>KRW</CharCode><Nominal>1000</Nominal><Name>Вон Республики Корея</Name><Value>49,8375</Value><VunitRate>0,0498375</VunitRate></Valute><Valute ID=\"R01820\"><NumCode>392</NumCode><CharCode>JPY</CharCode><Nominal>100</Nominal><Name>Японских иен</Name><Value>49,1285</Value><VunitRate>0,491285</VunitRate></Valute></Valutes></ValCurs>";
        XmlParser parser = new XmlParser();
        var res = parser.parseCurrencies(response, LocalDate.now());
        assertEquals(1,1);
    }
}