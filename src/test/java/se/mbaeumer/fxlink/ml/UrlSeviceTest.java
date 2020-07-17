package se.mbaeumer.fxlink.ml;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlSeviceTest {

    @Test
    public void withoutHttpProtocol() {
        UrlSevice urlSevice = new UrlSevice();

        String actual = urlSevice.withoutProtocol("http://www.kicker.de");
        Assert.assertTrue("www.kicker.de".equals(actual));
    }

    @Test
    public void withoutHttpsProtocol() {
        UrlSevice urlSevice = new UrlSevice();

        String actual = urlSevice.withoutProtocol("https://www.kicker.de");
        Assert.assertTrue("www.kicker.de".equals(actual));
    }

    @Test
    public void withoutPrefix() {
        UrlSevice urlSevice = new UrlSevice();

        String actual = urlSevice.withoutPrefix("www.kicker.de");
        Assert.assertTrue("kicker.de".equals(actual));
    }

    @Test
    public void getUrlParts() {
        UrlSevice urlSevice = new UrlSevice();

        String[] parts = urlSevice.getUrlParts("mkaz.blog/code/python-string-format_cookbook/");
        Assert.assertTrue(parts.length == 7);
        Assert.assertTrue("mkaz".equals(parts[0]));

    }
}