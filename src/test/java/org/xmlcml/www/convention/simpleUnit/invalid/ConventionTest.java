package org.xmlcml.www.convention.simpleUnit.invalid;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xmlcml.www.*;

import java.io.File;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by IntelliJ IDEA.
 * User: jat45
 * Date: 23/12/10
 * Time: 14:49
 * To change this template use File | Settings | File Templates.
 */
public class ConventionTest {

    private TestUtils testUtils = new TestUtils();
    private ConventionValidator conventionValidator = new ConventionValidator();
    private ValidationReport report = null;
    private String root = "convention/simpleUnit/invalid/";


    private static Logger log = Logger.getLogger(ConventionTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        report = null;
    }

   @Test
    public void testInvalidAreSchemaValid() {
        Collection<File> files = FileUtils.listFiles(new File("./src/test/resources/org/xmlcml/www/convention/simpleUnit/invalid"), new String[]{"xml"}, false);
        assertFalse("there should be test documents", files.isEmpty());
        for (File file : files) {
            //ValidationReport report = conventionValidator.validate(testUtils.getFileAsDocument(file));
            CmlLiteValidator validator = new CmlLiteValidator();
              ValidationReport report = validator.validate(testUtils.getFileAsDocument(file));

            System.out.println("report for: "+file.getAbsolutePath());
            CmlLiteValidator.print(report.getReport(), System.out);
            assertEquals(file.getAbsolutePath() + " should be convention invalid " + report.getReport().toXML(), ValidationResult.INVALID, report.getValidationResult());
        }
    }

}

