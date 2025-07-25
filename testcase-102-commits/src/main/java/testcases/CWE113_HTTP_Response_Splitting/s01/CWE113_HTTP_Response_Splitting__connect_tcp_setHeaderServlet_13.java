/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_13.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-13.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: connect_tcp Read data using an outbound tcp connection
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 13 Control flow: if(IO.STATIC_FINAL_FIVE==5) and if(IO.STATIC_FINAL_FIVE!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_13 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for bad() implementation
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodG2B1() implementation
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodG2B2() implementation
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodB2G1() implementation
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodB2G2() implementation
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}