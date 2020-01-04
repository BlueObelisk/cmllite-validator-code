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
package org.xmlcml.www.schema.warning;

import nu.xom.Document;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xmlcml.www.*;

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


     @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        report = null;
    }

    @Test
    public void testNoCmlPresent1() {
        String location = "schema/warning/no-cml-present.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        Assert.assertEquals(location + " should be valid with warnings", ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }

    @Test
    public void testNoCmlNamespacePresent1() {
        String location = "schema/warning/no-cml-namespace-present.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        assertEquals(location + " should be valid with warnings", ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }

    @Test
    public void testNumericBondOrdersAreDeprecated1() {
        String location = "schema/warning/numeric-bond-orders-are-deprecated1.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        CmlLiteValidator.print(report.getReport(), System.out);
        assertEquals(location+" should be valid with warnings", ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }

    @Test
    public void testNumericBondOrdersAreDeprecated2() {
        String location = "schema/warning/numeric-bond-orders-are-deprecated2.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        CmlLiteValidator.print(report.getReport(), System.out);
        assertEquals(location+" should be valid with warnings", ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }

    @Test
    public void testNumericBondOrdersAreDeprecated3() {
        String location = "schema/warning/numeric-bond-orders-are-deprecated3.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = schemaValidator.validate(input);
        CmlLiteValidator.print(report.getReport(), System.out);
        assertEquals(location+" should be valid with warnings", ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }

}
