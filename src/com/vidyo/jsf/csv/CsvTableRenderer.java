package com.vidyo.jsf.csv;

import java.io.IOException;
import java.util.Iterator;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;


public class CsvTableRenderer extends Renderer {

    /**
     * Encode begin.
     * 
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    /**
     * Encode children.
     * 
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        if (hasHeaderFacets(component)) {
            Iterator<UIComponent> children = component.getChildren().iterator();
            while (children.hasNext()) {
                UIComponent child = children.next();
                if (child instanceof UIColumn) {
                    UIColumn column = (UIColumn) child;
                    if (column.getFacet("header") != null) {
                        UIComponent header = column.getFacet("header");
                        header.encodeAll(context);
                        if (children.hasNext()) {
                            writer.write(",");
                        }
                    } else {
                        if (children.hasNext()) {
                            writer.write(",");
                        }
                    }
                } else {
                    if (children.hasNext()) {
                        writer.write(",");
                    }
                }
            }
            writer.write("\n");
        }

        UIData data = (UIData) component;
        int processed = 0;
        int rows = data.getRows();
        int rowIndex = data.getFirst() - 1;
        while (true) {
            if ((rows > 0) && (++processed > rows)) {
                break;
            }
            data.setRowIndex(++rowIndex);
            if (!data.isRowAvailable()) {
                break;
            }
            Iterator<UIComponent> children = component.getChildren().iterator();
            while (children.hasNext()) {
                UIComponent child = children.next();
                if (child instanceof UIColumn) {
                    UIColumn column = (UIColumn) child;
                    column.encodeAll(context);
                    if (children.hasNext()) {
                        writer.write(",");
                    }
                }
            }
            writer.write("\n");
        }
        data.setRowIndex(-1);
    }

    /**
     * Has header facets.
     * 
     * @return true if it has header facets, false otherwise.
     */
    public boolean hasHeaderFacets(UIComponent component) {
        boolean result = false;
        Iterator<UIComponent> children = component.getChildren().iterator();
        while (children.hasNext()) {
            UIComponent child = children.next();
            if (child instanceof UIColumn) {
                UIColumn column = (UIColumn) child;
                if (column.getFacet("header") != null) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Get renders children flag.
     * 
     * @return true.
     */
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    /**
     * Encode end.
     * 
     * @param context the Faces context.
     * @param component the UI component.
     * @throws IOException when an I/O error occurs.
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
    }
}
