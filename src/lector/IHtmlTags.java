package lector;

import java.util.regex.*;

public interface IHtmlTags {

    String PARAGRAPH_REGEX = "<\\s*p>(.*?)<\\s*/\\s*p>";
    String INNER_URL_REGEX = "href=(\"http:|\"https:)(.*?)>";
    String HREF_REGEX = "<\\s*a>(.*?)<\\s*/\\s*a>";
    String WORD_REGEX = "[a-zA-ZÁÉÍÓÚáéíóú]*";



}
