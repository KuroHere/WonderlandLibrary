// 
// Decompiled by Procyon v0.6.0
// 

package org.newdawn.slick.svg.inkscape;

import org.w3c.dom.Element;
import org.newdawn.slick.svg.NonGeometricData;

public class InkscapeNonGeometricData extends NonGeometricData
{
    private Element element;
    
    public InkscapeNonGeometricData(final String metaData, final Element element) {
        super(metaData);
        this.element = element;
    }
    
    public String getAttribute(final String attribute) {
        String result = super.getAttribute(attribute);
        if (result == null) {
            result = this.element.getAttribute(attribute);
        }
        return result;
    }
    
    public Element getElement() {
        return this.element;
    }
}
