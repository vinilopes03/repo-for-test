/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__database_setHeaderServlet_01.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-01.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: database Read data from a database
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 01 Baseline
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

public class CWE113_HTTP_Response_Splitting__database_setHeaderServlet_01 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation from the previous commit
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
    }

    /* goodG2B() - use goodsource and badsink */
    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        /* FIX: Use a hardcoded string */
        data = "foo";

        if (data != null) {
            /* POTENTIAL FLAW: Input not verified before inclusion in header */
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}