/**********************************************************************
 * Copyright (c) 2012 Ericsson
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Bernd Hufmann - Initial API and implementation
 **********************************************************************/
package org.eclipse.linuxtools.lttng2.core.tests.control.model.impl;

import junit.framework.TestCase;

import org.eclipse.linuxtools.internal.lttng2.core.control.model.IFieldInfo;
import org.eclipse.linuxtools.internal.lttng2.core.control.model.impl.FieldInfo;

/**
 * The class <code>FieldInfoTest</code> contains test for the class <code>{@link FieldInfo}</code>.
 */
@SuppressWarnings({"nls", "javadoc"})
public class FieldInfoTest extends TestCase {

    // ------------------------------------------------------------------------
    // Test data
    // ------------------------------------------------------------------------
    private IFieldInfo fFieldInfo1 = null;
    private IFieldInfo fFieldInfo2 = null;

    // ------------------------------------------------------------------------
    // Housekeeping
    // ------------------------------------------------------------------------
    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
     *
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
        ModelImplFactory factory = new ModelImplFactory();
        fFieldInfo1 = factory.getFieldInfo1();
        fFieldInfo2 = factory.getFieldInfo2();
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception if the clean-up fails for some reason
     *
     */
    @Override
    public void tearDown() throws Exception {
    }

    // ------------------------------------------------------------------------
    // Tests
    // ------------------------------------------------------------------------

    /**
     * Run the BaseEventInfo() constructor test.
     *
     */
    public void testFiledInfo() {
        FieldInfo fixture = new FieldInfo("field");
        assertNotNull(fixture);

        assertEquals("field", fixture.getName());
        assertNull(fixture.getFieldType());
    }

    /**
     * Test Copy Constructor
     */
    public void testEventInfoCopy() {
        FieldInfo info = new FieldInfo((FieldInfo)fFieldInfo1);

        assertEquals(fFieldInfo1.getName(), info.getName());
        assertEquals(fFieldInfo1.getFieldType(), info.getFieldType());
    }

    /**
     * Test Copy Constructor
     */
    public void testEventCopy2() {
        try {
            FieldInfo info = null;
            new FieldInfo(info);
            fail("null copy");
        }
        catch (IllegalArgumentException e) {
            // Success
        }
    }

    /**
     * Run the TraceEventType getEventType() method test.
     *
     * @throws Exception
     *
     */
    public void testSetFieldType() {
        FieldInfo info = new FieldInfo((FieldInfo)fFieldInfo1);

        info.setFieldType("string");
        assertEquals("string", info.getFieldType());
    }

    public void testToString() {
        String result = fFieldInfo1.toString();

        // add additional test code here
        assertEquals("[FieldInfo([TraceInfo(Name=intfield)],type=int", result);
    }

    // ------------------------------------------------------------------------
    // equals
    // ------------------------------------------------------------------------

    public void testEqualsReflexivity() {
        assertTrue("equals", fFieldInfo1.equals(fFieldInfo1));
        assertTrue("equals", fFieldInfo2.equals(fFieldInfo2));

        assertTrue("equals", !fFieldInfo1.equals(fFieldInfo2));
        assertTrue("equals", !fFieldInfo2.equals(fFieldInfo1));
    }

    public void testEqualsSymmetry() {
        FieldInfo info1 = new FieldInfo((FieldInfo)fFieldInfo1);
        FieldInfo info2 = new FieldInfo((FieldInfo)fFieldInfo2);

        assertTrue("equals", info1.equals(fFieldInfo1));
        assertTrue("equals", fFieldInfo1.equals(info1));

        assertTrue("equals", info2.equals(fFieldInfo2));
        assertTrue("equals", fFieldInfo2.equals(info2));
    }

    public void testEqualsTransivity() {
        FieldInfo info1 = new FieldInfo((FieldInfo)fFieldInfo1);
        FieldInfo info2 = new FieldInfo((FieldInfo)fFieldInfo1);
        FieldInfo info3 = new FieldInfo((FieldInfo)fFieldInfo1);

        assertTrue("equals", info1.equals(info2));
        assertTrue("equals", info2.equals(info3));
        assertTrue("equals", info1.equals(info3));
    }

    public void testEqualsNull() {
        assertTrue("equals", !fFieldInfo1.equals(null));
        assertTrue("equals", !fFieldInfo2.equals(null));
    }

    // ------------------------------------------------------------------------
    // hashCode
    // ------------------------------------------------------------------------

    public void testHashCode() {
        FieldInfo info1 = new FieldInfo((FieldInfo)fFieldInfo1);
        FieldInfo info2 = new FieldInfo((FieldInfo)fFieldInfo2);

        assertTrue("hashCode", fFieldInfo1.hashCode() == info1.hashCode());
        assertTrue("hashCode", fFieldInfo2.hashCode() == info2.hashCode());

        assertTrue("hashCode", fFieldInfo1.hashCode() != info2.hashCode());
        assertTrue("hashCode", fFieldInfo2.hashCode() != info1.hashCode());
    }
}