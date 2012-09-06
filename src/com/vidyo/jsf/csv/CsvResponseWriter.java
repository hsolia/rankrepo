package com.vidyo.jsf.csv;

import java.io.IOException;
import java.io.Writer;
import javax.faces.component.UIComponent;
import javax.faces.context.ResponseWriter;

public class CsvResponseWriter extends ResponseWriter {

    /**
     * Stores the writer.
     */
    private Writer writer;

    /**
     * Constructor.
     */
    public CsvResponseWriter() {
        this.writer = null;
    }

    /**
     * Constructor.
     * 
     * @param writer the writer.
     */
    public CsvResponseWriter(Writer writer) {
        this.writer = writer;
    }

    /**
     * Clone with writer.
     * 
     * @param writer the writer.
     * @return the response writer.
     */
    @Override
    public ResponseWriter cloneWithWriter(Writer writer) {
        return new CsvResponseWriter(writer);
    }

    /**
     * End the document.
     * 
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void endDocument() throws IOException {
    }

    /**
     * End the element.
     * 
     * @param name the name.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void endElement(String name) throws IOException {
    }

    /**
     * Flush the writer.
     * 
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void flush() throws IOException {
        writer.flush();
    }

    /**
     * Get the character encoding.
     * 
     * @return the character encoding.
     */
    @Override
    public String getCharacterEncoding() {
        return null;
    }

    /**
     * Get the content type.
     * 
     * @return the content type.
     */
    @Override
    public String getContentType() {
        return "text/csv";
    }

    /**
     * Start the document.
     * 
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void startDocument() throws IOException {
    }

    /**
     * Start the element.
     * 
     * @param name the name.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void startElement(String name, UIComponent component) throws IOException {
    }

    /**
     * Write attribute.
     * 
     * @param name the name.
     * @param value the value.
     * @param property the property.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeAttribute(String name, Object value, String property) throws IOException {
    }

    /**
     * Write the comment.
     * 
     * @param comment the comment.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeComment(Object comment) throws IOException {
    }

    /**
     * Write to the buffer.
     * 
     * @param text the text.
     * @param off the offset.
     * @param len the length.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void write(char[] text, int off, int len) throws IOException {
        writer.write(text, off, len);
    }

    /**
     * Write the text.
     * 
     * @param text the text.
     * @param property the property.
     * @throws IOException when a serious error occurs.
     */
    @Override
    public void writeText(Object text, String property) throws IOException {
    }

    /**
     * Write the text.
     * 
     * @param text the text.
     * @param off the offset.
     * @param len the length.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeText(char[] text, int off, int len) throws IOException {
    }

    /**
     * Write the URI attribute.
     * 
     * @param name the name.
     * @param value the value.
     * @param property the property.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void writeURIAttribute(String name, Object value, String property) throws IOException {
    }

    /**
     * Close the writer.
     * 
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }
}
