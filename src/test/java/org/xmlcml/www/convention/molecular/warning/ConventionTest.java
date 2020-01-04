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
package org.xmlcml.www.convention.molecular.warning;

import nu.xom.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xmlcml.www.ConventionValidator;
import org.xmlcml.www.TestUtils;
import org.xmlcml.www.ValidationReport;
import org.xmlcml.www.ValidationResult;

import static org.junit.Assert.assertEquals;

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
    private String root = "convention/molecular/warning/";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        report = null;
    }

    @Test
    public void testNonMolecularElementsShouldHaveDifferentConvention() {
        String location = root+"non-molecular-elements-should-have-different-convention.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be warning "+report.getReport().toXML(), ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }
    @Test
    public void testBondOrderOtherShouldHaveDictRef() {
        String location = root+"bond-order-other-should-have-dictRef.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be warning "+report.getReport().toXML(), ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }
    @Test
    public void testBondStereoOtherShouldHaveDictRef() {
        String location = root+"bondStereo-other-should-have-dictRef.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be warning "+report.getReport().toXML(), ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }
    @Test
    public void testPropertyShouldHaveTitle() {
        String location = root+"property-should-have-title.cml";
        Document input = testUtils.getFileAsDocument(location);
        report = conventionValidator.validate(input);
        assertEquals(location+" should be warning "+report.getReport().toXML(), ValidationResult.VALID_WITH_WARNINGS, report.getValidationResult());
    }
}
