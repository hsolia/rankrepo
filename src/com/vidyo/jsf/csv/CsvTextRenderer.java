
package com.vidyo.jsf.csv;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;


public class CsvTextRenderer extends Renderer {

    /**
     * Encode end.
     * 
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        if (component == null) {
            throw new NullPointerException("Component cannot be null");
        }
        ResponseWriter writer = context.getResponseWriter();
        if (component instanceof UIOutput) {
            UIOutput output = (UIOutput) component;
            if (output.getValue() instanceof Number) {
                writer.write(output.getValue().toString());
            } else {
                writer.write("\"" + output.getValue().toString() + "\"");
            }
        }
    }
}
