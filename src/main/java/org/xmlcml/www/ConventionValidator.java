package org.xmlcml.www;

import nu.xom.*;
import nu.xom.xslt.XSLException;
import nu.xom.xslt.XSLTransform;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jat45
 * Date: 28-Oct-2010
 * Time: 17:15:55
 */
public class ConventionValidator {

    private static Logger log = Logger.getLogger(ConventionValidator.class);

    private Builder builder = new Builder();

    private static Map<URI, XSLTransform> knownConventions = null;

    private final static String conventionNS = "http://www.xml-cml.org/convention/";

    private ConventionValidator() {
        // prevent public initialisation
    }

    public static ConventionValidator newInstance() {
        if (knownConventions == null) {
            knownConventions = createKnownConventionLocations();
        }
        return new ConventionValidator();
    }

    private static Map<URI, XSLTransform> createKnownConventionLocations() {
        knownConventions = new HashMap<URI, XSLTransform>();
        try {
            knownConventions.put(new URI(conventionNS + "molecular"), createTransform("molecular-rules.xsl"));
            knownConventions.put(new URI(conventionNS + "cmlcomp"), createTransform("cmlcomp-rules.xsl"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            log.fatal("can't create uris");
            throw new RuntimeException(e);
        }
        return knownConventions;
    }

    private static XSLTransform createTransform(String name) {
        Builder builder = new Builder();
        URL xslt = Thread.currentThread().getContextClassLoader().getResource(name);
        /* set up to use saxon 9 */
        System.setProperty("javax.xml.transform.TransformerFactory",
                "net.sf.saxon.TransformerFactoryImpl");
        try {
            Document stylesheet = builder.build(xslt.openStream());
            return new XSLTransform(stylesheet);
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    public boolean validate(String input) {
        boolean valid = false;
        Document document = build(IOUtils.toInputStream(input));
        if (document != null) {
            Map<URI, List<Element>> conventionsMap = findConventions(document);
            if (conventionsMap != null) {
                if (conventionsMap.isEmpty()) {
                    log.warn("no conventions found");
                    valid = true;
                } else {
                    return validate(conventionsMap);
                }
            }
        }
        return valid;
    }

    private Document build(InputStream input) {
        Document document = null;
        try {
            document = builder.build(input);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    private boolean validate(Map<URI, List<Element>> conventionToElement) {
        for (Map.Entry<URI, List<Element>> entry : conventionToElement.entrySet()) {
            for (Element element : entry.getValue()) {
                boolean valid = validate(entry.getKey(), element);
                if (!valid) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validate(URI convention, Element start) {
        if (knownConventions.containsKey(convention)) {
            XSLTransform xslt = knownConventions.get(convention);
            Nodes nodes;
            try {
                String absoluteXPathToStartElement = generateFullPath(start);
                xslt.setParameter("absoluteXPathToStartElement", absoluteXPathToStartElement);
                nodes = xslt.transform(start.getDocument());
            } catch (XSLException e) {
                log.info(e);
                return false;
            }
            Document result = XSLTransform.toDocument(nodes);
            print(result, System.out);
            Nodes failures = result.query("//*[local-name()='error' and namespace-uri()='http://www.xml-cml.org/report']");
            for (int index = 0, n = failures.size(); index < n; index++) {
                Node node = failures.get(index);
                //log.error("\n"+node.toXML());
            }
            return failures.size() == 0;
        } else {
            log.info("the validation of convention: '" + convention + "' is not supported by this service");
        }
        return false;
    }

    /**
     * Prints a XOM document to an OutputStream without having to remember the
     * serializer voodoo. The encoding is always UTF-8.
     *
     * @param doc the XOM Document to print
     * @param out where to print to
     */
    public static void print(Document doc, OutputStream out) {
        Serializer serializer;
        try {
            serializer = new Serializer(out, "UTF-8");
            serializer.setIndent(2);
            serializer.write(doc);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private HashMap<URI, List<Element>> findConventions(Document document) {
        HashMap<URI, List<Element>> map = new HashMap<URI, List<Element>>();
        Nodes nodes = document
                .query("//*[namespace-uri()='" + Validator.CmlNS + "']/@convention");
        for (int i = 0, length = nodes.size(); i < length; i++) {
            Attribute attribute = (Attribute) nodes.get(i);
            Element element = (Element) attribute.getParent();
            String[] value = attribute.getValue().split(":");
            String ns = element.getNamespaceURI(value[0]);
            URI convention;
            try {
                convention = new URI(ns + value[1]);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                log.error("Not a valid convention value, it should be a URI: '" + ns + value[1] + "'");
                return null;
            } catch (Exception e) {
                log.error(e);
                return null;
            }
            if (convention != null) {
                if (map.containsKey(convention)) {
                    map.get(convention).add(element);
                } else {
                    List<Element> list = new ArrayList<Element>();
                    list.add(element);
                    map.put(convention, list);
                }
            }
        }
        return map;
    }


    private String generateFullPath(Element element) {
        String name = "*[local-name()='" + element.getLocalName() + "' and namespace-uri()='" +
                element.getNamespaceURI() + "']";
        Nodes nodes =  element.query("preceding-sibling::*[local-name()=local-name(.) and namespace-uri() = namespace-uri(.)]");
        int position = nodes.size();
        if (element.getDocument().getRootElement() == element) {
            return "/" + name + "[" + (1 + position) + "]";
        }
        return generateFullPath(element.getParent()) + "/" + name + "[" + (1 + position) + "]";
    }

    private String generateFullPath(Node node) {
        if (node instanceof Attribute) {
            Attribute attribute = (Attribute) node;
            String name = "@*[local-name()='" + attribute.getLocalName() + "' and namespace-uri()='" + attribute.getNamespaceURI() + "']";
            return generateFullPath((Element) attribute.getParent()) + "/" + name;
        }
        Element element = (Element) node;
        return generateFullPath(element);
    }
}
