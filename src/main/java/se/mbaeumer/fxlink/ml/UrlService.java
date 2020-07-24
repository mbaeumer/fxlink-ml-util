package se.mbaeumer.fxlink.ml;

public class UrlService {

    public String withoutProtocol(final String url){
        return url.replace("http://","").replace("https://","");
    }

    public String withoutPrefix(final String url){
        return  (url.startsWith("www.")) ? url.replace("www.","") : url;
    }

    public String[] getUrlParts(final String url){
        return url.split("/|\\.|-|_");
    }
}
