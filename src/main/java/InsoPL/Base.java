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
    private JComboBox lang;

    public Base(TextArea terminal, JTextField url, JComboBox lang){
        this.lang = lang;
        this.terminal = terminal;
        this.url = url;
        this.stopped = true;
        this.start();
    };

    public void run() {
        try {
            while( true ) {
                while (stopped) Thread.sleep(500);
                while (!stopped){
                    String lang_name = (String) lang.getSelectedItem();
                    Document doc = null;
                    terminal.append("asdsadasdasdasd\n");

                    if(lang_name.equals("Polski"))
                        doc = Jsoup.connect("https://pl.wikipedia.org/wiki/"+url.getText()).get();
                    else if (lang_name.equals("Angielski"))
                        doc = Jsoup.connect("https://en.wikipedia.org/wiki/"+url.getText()).get();

                    else if (lang_name.equals("Niemiecki"))
                        doc = Jsoup.connect("https://de.wikipedia.org/wiki/"+url.getText()).get();
                    terminal.append("asdsget2adasdasdasd\n");


                    String title = doc.select("h1").text();
                    terminal.append("Processing article\n");
                    WriteToEpub writeToEpub = WikiArticleConverter.getWikiArticleFromDoc(doc);
                    writeToEpub.writeToFile();
                    stopped = true;
                    terminal.append("File saved as \'"+ WriteToEpub.stringToFilename(title)+".epub\'\n");
                }
            }
        }
        catch (InterruptedException | FileNotFoundException e) {
            terminal.append( "Zlapalem wyjatek - koncze prace metody run" );
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Start(){
        terminal.append( "Downloading Wikipedia article \""+url.getText()+"\"\n");
        stopped = !stopped;
    }
}
