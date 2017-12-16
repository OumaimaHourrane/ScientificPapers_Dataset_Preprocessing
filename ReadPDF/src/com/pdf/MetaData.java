package com.pdf;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class MetaData {
    public static void main(final String[] args) throws IOException,
                SAXException, TikaException {
          Parser parser = new PDFParser();
          BodyContentHandler handler = new BodyContentHandler();
          Metadata metadata = new Metadata();
          InputStream content = MetaData.class
                      .getResourceAsStream("HoffmanBleiBach2010b.pdf");
          parser.parse(content, handler, metadata, new ParseContext());
          for (String name : metadata.names()) {
                System.out.println(name + ":\t" + metadata.get(name));
          }
          
    }
}
