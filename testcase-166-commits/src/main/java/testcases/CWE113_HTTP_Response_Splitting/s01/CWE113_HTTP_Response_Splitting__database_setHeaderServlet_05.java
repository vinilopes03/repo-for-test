/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__database_setHeaderServlet_05.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-05.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: database Read data from a database
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 05 Control flow: if(privateTrue) and if(privateFalse)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__database_setHeaderServlet_05 extends AbstractTestCaseServlet
{
    private boolean privateTrue = true;
    private boolean privateFalse = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will be added in later commits
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will be added in later commits
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will be added in later commits
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will be added in later commits
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will be added in later commits
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation will be added in later commits
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}