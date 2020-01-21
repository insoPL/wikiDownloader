package com.company;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WikiArticleConverter {
    public static WriteToEpub getWikiArticleFromDoc(Document doc){
        String title = doc.select("h1").text();
        WriteToEpub writeToEpub = new WriteToEpub(title);

        Elements newsHeadlines = doc.select("#mw-content-text p,h2");

        String lastHeadline = title;
        String lastArticleText = "";
        for (Element headline : newsHeadlines) {
            if (headline.tag().toString().equals("p")){

                if (headline.text().length()!=0)
                    lastArticleText += " " + text_conversions(headline.text());
            }
            else if (headline.tag().toString().equals("h2")){
                if (lastArticleText.length() != 0)
                    writeToEpub.addChapter(lastHeadline,lastArticleText);
                lastHeadline = text_conversions(headline.text());
                lastArticleText = "";
            }
        }
        return writeToEpub;
    }
    private static String text_conversions(String str){
        str = str.replaceAll("\\[(.*?)\\]","");
        return str;
    }

}
