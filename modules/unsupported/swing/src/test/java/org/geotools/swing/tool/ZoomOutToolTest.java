/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2011, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */

package org.geotools.swing.tool;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.swing.event.MapPaneEvent;
import org.geotools.swing.testutils.GraphicsTestRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

/**
 * Tests for the zoom-out cursor tool.
 * 
 * @author Michael Bedward
 * @since 8.0
 * @source $URL$
 * @version $Id$
 */
@RunWith(GraphicsTestRunner.class)
public class ZoomOutToolTest extends CursorToolTestBase {
    
    @Test
    public void clickZoomAtCentreOfMap() throws Exception {
        ReferencedEnvelope startEnv = mapPane.getDisplayArea();
        mapPane.setCursorTool(new ZoomOutTool());
        
        listener.setExpected(MapPaneEvent.Type.DISPLAY_AREA_CHANGED);
        mapPaneFixture.click();
        
        assertTrue( listener.await(MapPaneEvent.Type.DISPLAY_AREA_CHANGED, EVENT_TIMEOUT) );
        ReferencedEnvelope endEnv = mapPane.getDisplayArea();
        
        double xZoom = endEnv.getWidth() / startEnv.getWidth();
        double yZoom = endEnv.getHeight() / startEnv.getHeight();
        
        assertEquals(AbstractZoomTool.DEFAULT_ZOOM_FACTOR, xZoom, TOL);
        assertEquals(AbstractZoomTool.DEFAULT_ZOOM_FACTOR, yZoom, TOL);
    }

}