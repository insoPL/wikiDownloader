package com.company;

import nl.siegmann.epublib.domain.Author;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;

/**
 *  Wrapper for epublib library
 */
public class WriteToEpub {
    String articleName;
    Book book;

    public WriteToEpub(String articleName) {
        this.articleName = articleName;
        this.book = new Book();
        Metadata metadata = book.getMetadata();

        metadata.addTitle(articleName);

        metadata.addAuthor(new Author("Wikipedia"));
    }

    public void addChapter(String chapterTitle, String chapterContent){
        Resource res = new Resource(stringToFilename(chapterTitle)+".html");
        chapterContent = "<h1>"+chapterTitle+"</h1>\n"+chapterContent;
        res.setData(chapterContent.getBytes());
        book.addSection(chapterTitle, res);
    }

    public void writeToFile() throws IOException {
        EpubWriter epubWriter = new EpubWriter();
        epubWriter.write(book, new FileOutputStream(stringToFilename(articleName)+".epub"));
    }

    static public String stringToFilename(String text){
        String ret = Normalizer.normalize(text, Normalizer.Form.NFD);
        ret = ret.replace('ą', 'a').replace('Ą', 'A')
                                            .replace('ć', 'c').replace('Ć', 'C')
                                            .replace('ę', 'e').replace('Ę', 'E')
                                            .replace('ł', 'l').replace('Ł', 'L')
                                            .replace('ń', 'n').replace('Ń', 'N')
                                            .replace('ó', 'o').replace('Ó', 'O')
                                            .replace('ś', 's').replace('Ś', 'S')
                                            .replace('ż', 'z').replace('Ż', 'Z')
                                            .replace('ź', 'z').replace('Ź', 'Z');

        ret = ret.replaceAll("[^A-Za-z0-9]", "");
        return ret;
    }

}
