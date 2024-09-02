package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class HabrCareerDateTimeParserTest {

    @Test
    void parseOne() {
        String date = "2024-09-02T13:30:30+03:00";
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        LocalDateTime rsl = parser.parse(date);
        assertThat(rsl).isEqualTo("2024-09-02T13:30:30");
    }

    @Test
    void parseTwo() {
        String date = "2024-08-31T19:05:25+02:00";
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        LocalDateTime rsl = parser.parse(date);
        assertThat(rsl).isEqualTo("2024-08-31T19:05:25");
    }

    @Test
    void parseThree() {
        String date = "2024-07-15T08:45:37+01:00";
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        LocalDateTime rsl = parser.parse(date);
        assertThat(rsl).isEqualTo("2024-07-15T08:45:37");
    }
}