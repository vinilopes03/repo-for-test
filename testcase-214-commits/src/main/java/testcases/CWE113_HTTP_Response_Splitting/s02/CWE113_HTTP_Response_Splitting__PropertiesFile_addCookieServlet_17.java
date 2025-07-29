/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__PropertiesFile_addCookieServlet_17.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-17.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: PropertiesFile Read data from a .properties file (in property named data)
* GoodSource: A hardcoded string
* Sinks: addCookieServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to addCookie()
* Flow Variant: 17 Control flow: for loops
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s02;
import testcasesupport.*;

import javax.servlet.http.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__PropertiesFile_addCookieServlet_17 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // (same as previous implementation)
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        data = "foo"; // FIX: Use a hardcoded string

        if (data != null)
        {
            Cookie cookieSink = new Cookie("lang", data);
            response.addCookie(cookieSink); // Still potentially insecure but valid
        }
    }
}