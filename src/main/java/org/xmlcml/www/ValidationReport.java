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

import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Nodes;

/**
 * Created by IntelliJ IDEA.
 * User: jat45
 * Date: 10-Nov-2010
 * Time: 13:01:32
 * To change this template use File | Settings | File Templates.
 */
public class ValidationReport {

    public static final String reportNS = "http://www.xml-cml.org/report/";
    public static final String infoElementName = "info";
    public static final String warningElementName = "warning";
    public static final String errorElementName = "error";

    private ValidationResult validationResult = null;

    private Document document = null;
    private Element element = null;

    public ValidationReport(String testType) {
        this(testType, ValidationResult.VALID);
    }

    public ValidationReport(String testType, ValidationResult initialValidationResult) {
        document = new Document(new Element("report", reportNS));
        element = new Element(testType, reportNS);
        document.getRootElement().appendChild(element);
        setValidationResult(initialValidationResult);
    }


    public ValidationResult getValidationResult() {
        return this.validationResult;
    }

    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    public void addReport(Document report) {
        Element root = report.getRootElement();
        Elements elements = root.getChildElements();
        for (int i = 0, size = elements.size(); i < size; i++) {
            Element child = elements.get(i);
            child.detach();
            element.appendChild(child);
        }
    }

    public Document getReport() {
        return this.document;
    }

    public void addError(String message) {
        Element e = new Element(errorElementName, reportNS);
        e.appendChild(message);
        element.appendChild(e);
    }

    public void addError(Element error) {
        error.detach();
        element.appendChild(error);
    }


    public void addWarning(String message) {
        Element e = new Element(warningElementName, reportNS);
        e.appendChild(message);
        element.appendChild(e);
    }

     public void addWarning(Element warning) {
        warning.detach();
        element.appendChild(warning);
    }

    public void addInfo(String message) {
        Element e = new Element(infoElementName, reportNS);
        e.appendChild(message);
        element.appendChild(e);
    }

     public void addInfo(Element info) {
        info.detach();
        element.appendChild(info);
    }


    public void addValid(String message) {
        Element e = new Element("valid", reportNS);
        e.appendChild(message);
        element.appendChild(e);
    }

    public boolean containsInfo() {
        Nodes nodes = document.query("//*[namespace-uri()='"+reportNS+"' and local-name()='"+infoElementName+"']");
        return nodes.size() > 0;
    }

}
