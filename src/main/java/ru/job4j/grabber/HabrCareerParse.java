package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Arrays;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";
    public static final String PREFIX = "/vacancies?page=";
    public static final String SUFFIX = "&q=Java%20developer&type=all";
    public static final int COUNT = 5;

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < COUNT; i++) {
            String fullLink = "%s%s%s%s".formatted(SOURCE_LINK, PREFIX, i, SUFFIX);
            Connection connection = Jsoup.connect(fullLink);
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element linkElement = titleElement.child(0);
                Element dateElement = row.select(".vacancy-card__date").first();
                Element date = dateElement.child(0);
                String vacancyName = titleElement.text();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                System.out.printf("%s %s%n %s", vacancyName, link, date.attr("dateTime"));
            });
        }
    }

    private String retrieveDescription(String link) {
        Connection connection = Jsoup.connect(link);
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element row = document.select(".vacancy-description__text").first();
        return row.text();
    }
}
