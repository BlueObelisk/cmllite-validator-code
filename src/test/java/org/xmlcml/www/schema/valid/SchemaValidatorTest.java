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
package org.xmlcml.www.schema.valid;

import nu.xom.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xmlcml.www.SchemaValidator;
import org.xmlcml.www.TestUtils;
import org.xmlcml.www.ValidationReport;
import org.xmlcml.www.ValidationResult;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: jat45
 * Date: 22/12/10
 * Time: 10:12
 */
public class SchemaValidatorTest {

    TestUtils testUtils = new TestUtils();
    SchemaValidator schemaValidator = new SchemaValidator();
    ValidationReport report = null;
    private String root = "schema/valid/";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        report = null;
    }

    @Test
    public void testForeignNamespacedAttributes1() {
        String location = root + "foreign-namespaced-attributes-1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        Assert.assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }

    @Test
    public void testMixedCmlAndNonCml1() {
        String location = root + "mixed-cml-and-non-cml-1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }

    @Test
    public void testMixedCmlAndNonCml2() {
        String location = root + "mixed-cml-and-non-cml-2.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }

    @Test
    public void testMixedCmlAndNonCml3() {
        String location = root + "mixed-cml-and-non-cml-3.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }

    @Test
    public void testMixedCmlAndNonCml4() {
        String location = root + "mixed-cml-and-non-cml-4.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }

    @Test
    public void testMixedCmlAndNonCml5() {
        String location = root + "mixed-cml-and-non-cml-5.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }


    @Test
    public void testNamespaceCanEndInSlash() {
        String location = root + "namespace-can-end-in-slash.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }

    @Test
    public void testNamespaceCanEndInHash() {
        String location = root + "namespace-can-end-in-hash.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }

    @Test
    public void testNamespaceDoesNotHaveToEndInSlash() {
        String location = root + "namespace-does-not-have-to-end-in-slash.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid "+report.getReport().toXML(), ValidationResult.VALID, report.getValidationResult());
    }
}
