/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__database_setHeaderServlet_31.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-31.tmpl.java
*/
/*
 * @description
 * CWE: 113 HTTP Response Splitting
 * BadSource: database Read data from a database
 * GoodSource: A hardcoded string
 * Sinks: setHeaderServlet
 *    GoodSink: URLEncode input
 *    BadSink : querystring to setHeader()
 * Flow Variant: 31 Data flow: make a copy of data within the same method
 *
 * */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__database_setHeaderServlet_31 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation will go here
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method implementation will go here
    }
    
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}