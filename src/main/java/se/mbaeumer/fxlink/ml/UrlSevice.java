package se.mbaeumer.fxlink.ml;

public class UrlSevice {

    public String withoutProtocol(final String url){
        return url.replace("http://","").replace("https://","");
    }

    public String withoutPrefix(final String url){
        String result = (url.startsWith("www.")) ? url.replace("www.","") : url;
        return result;
    }

    public String[] getUrlParts(final String url){
        return url.split("/|\\.|-|_");
    }
}
