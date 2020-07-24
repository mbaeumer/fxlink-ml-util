package se.mbaeumer.fxlink.ml;

import org.junit.Assert;
import org.junit.Test;

public class UrlServiceTest {

    @Test
    public void withoutHttpProtocol() {
        UrlService urlService = new UrlService();

        String actual = urlService.withoutProtocol("http://www.kicker.de");
        Assert.assertTrue("www.kicker.de".equals(actual));
    }

    @Test
    public void withoutHttpsProtocol() {
        UrlService urlService = new UrlService();

        String actual = urlService.withoutProtocol("https://www.kicker.de");
        Assert.assertTrue("www.kicker.de".equals(actual));
    }

    @Test
    public void withoutPrefix() {
        UrlService urlService = new UrlService();

        String actual = urlService.withoutPrefix("www.kicker.de");
        Assert.assertTrue("kicker.de".equals(actual));
    }

    @Test
    public void getUrlParts() {
        UrlService urlService = new UrlService();

        String[] parts = urlService.getUrlParts("mkaz.blog/code/python-string-format_cookbook/");
        Assert.assertTrue(parts.length == 7);
        Assert.assertTrue("mkaz".equals(parts[0]));

    }
}