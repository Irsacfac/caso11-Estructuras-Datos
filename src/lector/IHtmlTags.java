package lector;

import java.util.regex.*;

public interface IHtmlTags {

    String PARAGRAPH_REGEX = "<p>([])*</p>";
    String HREF_REGEX = "<a href=(\"[^\"]*\"|'[^']*'|[^'\">])*</p>";

}
