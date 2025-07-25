/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_11.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-11.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: connect_tcp Read data using an outbound tcp connection
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 11 Control flow: if(IO.staticReturnsTrue()) and if(IO.staticReturnsFalse())
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_11 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commits
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commits
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commits
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commits
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in the next commits
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}