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
package org.xmlcml.www;

import nu.xom.Builder;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author jat45
 * @author Weerapong Phadungsukanan
 */
public class XmlWellFormednessValidator {

    public static final String reportTitle = "well-formed-test";

    /**
     * An XML document is well-formed if it is not null.
     * Method validate(String xml) and validate(File file) in the CmlLiteValidator
     * try to create XOM document first and pass it as argument to this method. If
     * XOM document cannot be created by any reason it is considered malformed.
     * 
     * @param xml
     * @return
     */
    public ValidationReport validate(String xml) {
        ValidationReport report = new ValidationReport(reportTitle);
        try {
            new Builder().build(IOUtils.toInputStream(xml, "UTF-8"));
            report.setValidationResult(ValidationResult.VALID);
            report.addValid("xml is well formed");
        } catch (Exception ex) {
            report.addError(ex.getMessage());
            report.setValidationResult(ValidationResult.INVALID);
        }
        return report;
    }

//    /**
//     * An XML document is well-formed if it is not null.
//     * Method validate(InputStream xml) and validate(File file) in the CmlLiteValidator
//     * try to create XOM document first and pass it as argument to this method. If
//     * XOM document cannot be created by any reason it is considered malformed.
//     *
//     * @param xml
//     * @return
//     */
//    public ValidationReport validate(InputStream xml) {
//        ValidationReport report = new ValidationReport(reportTitle);
//        try {
//            new Builder().build(xml);
//            report.setValidationResult(ValidationResult.VALID);
//            report.addValid("xml is well formed");
//        } catch (Exception ex) {
//            report.addError(ex.getMessage());
//            report.setValidationResult(ValidationResult.INVALID);
//        }
//        return report;
//    }

}
