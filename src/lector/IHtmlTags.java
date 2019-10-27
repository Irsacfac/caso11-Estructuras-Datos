package lector;

import java.util.regex.*;

public interface IHtmlTags {

    String PARAGRAPH_REGEX = "<\\s*p>(.*?)<\\s*/\\s*p>";
    String HREF_REGEX = "<\\s*a[^>]*>(.*?)<\\s*/\\s*a>";

}
