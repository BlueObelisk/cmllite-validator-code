/**
 * Copyright (C) 2010 Peter Murray-Rust (pm286@cam.ac.uk)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.xmlcml.www.convention.simpleUnit.valid;

import nu.xom.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xmlcml.www.ConventionValidator;
import org.xmlcml.www.TestUtils;
import org.xmlcml.www.ValidationReport;
import org.xmlcml.www.ValidationResult;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by IntelliJ IDEA.
 * User: jat45
 * Date: 23/12/10
 * Time: 14:49
 */
public class ConventionTest {

    private TestUtils testUtils = new TestUtils();
    private ConventionValidator conventionValidator = new ConventionValidator();
    private ValidationReport report = null;
    private String root = "convention/simpleUnit/valid/";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        report = null;
    }

    @Test
    public void testForeignElementsAreAllowed1() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testForeignElementsAreAllowed2() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testForeignElementsAreAllowed3() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testForeignElementsAreAllowed4() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testForeignElementsAreAllowed5() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testMultipleSimpleUnitInSameDocument() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testUnitListDoesNotHaveToBeRootElement1() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testUnitListDoesNotHaveToBeRootElement2() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testUnitListDoesNotHaveToBeRootElement3() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testUnitListDoesNotHaveToBeRootElement4() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }

    @Test
    public void testValid1() {
        String location = root+"valid1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be valid\n"+report.getReport().toXML() , ValidationResult.VALID, report.getValidationResult());
        assertFalse(location+" should not contain info\n"+report.getReport().toXML(), report.containsInfo());
    }


}
