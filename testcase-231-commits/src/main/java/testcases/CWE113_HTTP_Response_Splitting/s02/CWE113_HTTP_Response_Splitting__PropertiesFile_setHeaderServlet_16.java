/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__PropertiesFile_setHeaderServlet_16.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-16.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: PropertiesFile Read data from a .properties file (in property named data)
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 16 Control flow: while(true)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s02;
import testcasesupport.*;

import javax.servlet.http.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__PropertiesFile_setHeaderServlet_16 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Initial implementation for the bad method
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Initial implementation for the good method
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}