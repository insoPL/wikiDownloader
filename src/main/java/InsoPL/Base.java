package InsoPL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Base extends Thread {
    private TextArea terminal;
    private JTextField url;
    private boolean stopped;
    private JComboBox<String> lang;

    public Base(TextArea terminal, JTextField url, JComboBox<String> lang){
        this.lang = lang;
        this.terminal = terminal;
        this.url = url;
        this.stopped = true;
        this.start();
    }

    public void run() {
        try {
            while (true) {
                while (stopped) Thread.sleep(500);
                while (!stopped) {
                    Document doc = null;
                    String lang_name = (String) lang.getSelectedItem();
                    assert lang_name != null;
                    switch (lang_name) {
                        case "Polski":
                            doc = Jsoup.connect("https://pl.wikipedia.org/wiki/" + url.getText()).get();
                            break;
                        case "Angielski":
                            doc = Jsoup.connect("https://en.wikipedia.org/wiki/" + url.getText()).get();
                            break;
                        case "Niemiecki":
                            doc = Jsoup.connect("https://de.wikipedia.org/wiki/" + url.getText()).get();
                            break;
                    }

                    assert doc != null;
                    String title = doc.select("h1").text();
                    terminal.append("Processing article\n");
                    WriteToEpub writeToEpub = WikiArticleConverter.getWikiArticleFromDoc(doc);
                    writeToEpub.writeToFile();
                    stopped = true;
                    terminal.append("File saved as '" + WriteToEpub.stringToFilename(title) + ".epub'\n");
                }
            }
        }
        catch (InterruptedException | FileNotFoundException e) {
            terminal.append( "Zlapalem wyjatek - koncze prace metody run" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Start(){
        if(stopped){
            terminal.append( "Downloading Wikipedia article '"+url.getText()+"'...\n");
            stopped = false;
        }
        else{
            terminal.append( "Download canceled. \n");
           stopped = true;
        }
    }
}
