package com.vidyo.jsf.csv;

import java.io.OutputStream;
import java.io.Writer;
import java.util.HashMap;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;
import javax.faces.render.Renderer;
import javax.faces.render.ResponseStateManager;


public class CsvRenderKit extends RenderKit {

    /**
     * Stores the renderers.
     */
    private HashMap<String, Renderer> rendererMap = new HashMap<String, Renderer>();

    /**
     * Add the renderer.
     * 
     * @param family the family.
     * @param rendererType the renderer type.
     * @param renderer the renderer.
     */
    @Override
    public void addRenderer(String family, String rendererType, Renderer renderer) {
        this.rendererMap.put(rendererType, renderer);
    }

    /**
     * Get the renderer.
     * 
     * @param family the family.
     * @param rendererType the renderer type.
     * @return the renderer.
     */
    @Override
    public Renderer getRenderer(String family, String rendererType) {
        return rendererMap.get(rendererType);
    }

    /**
     * Get the response state manager.
     * 
     * @return null.
     */
    @Override
    public ResponseStateManager getResponseStateManager() {
        return null;
    }

    /**
     * Create the response writer.
     * 
     * @param writer the writer.
     * @param contentTypeList the content-type list.
     * @param characterEncoding the character encoding.
     * @return the response writer.
     */
    @Override
    public ResponseWriter createResponseWriter(
            Writer writer, String contentTypeList, String characterEncoding) {
        return new CsvResponseWriter(writer);
    }

    /**
     * Create the response stream.
     * 
     * @param out the output stream.
     * @return the response stream.
     */
    @Override
    public ResponseStream createResponseStream(OutputStream out) {
        return null;
    }
}
