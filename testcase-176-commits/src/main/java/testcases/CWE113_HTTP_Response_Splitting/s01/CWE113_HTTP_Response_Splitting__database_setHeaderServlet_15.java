/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__database_setHeaderServlet_15.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-15.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: database Read data from a database
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 15 Control flow: switch(6) and switch(7)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__database_setHeaderServlet_15 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // (same code as previous commits)
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodB2G1(request, response);
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        // Use a hardcoded string
        data = "foo";

        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "foo"; // Hardcoded good source

        if (data != null)
        {
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        // Read data from a database
        // (same code as previous commit)
        // ...
        // Use URLEncoder for encoding
        if (data != null)
        {
            String encodedData = java.net.URLEncoder.encode(data, "UTF-8");
            response.setHeader("Location", "/author.jsp?lang=" + encodedData);
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        // Read data from a database
        // (same code as previous commit)
        // ...
        // Use URLEncoder for encoding
        if (data != null)
        {
            String encodedData = java.net.URLEncoder.encode(data, "UTF-8");
            response.setHeader("Location", "/author.jsp?lang=" + encodedData);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}