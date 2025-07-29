/*
* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_13.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-13.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: getCookies_Servlet Read data from the first cookie using getCookies()
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 13 Control flow: if(IO.STATIC_FINAL_FIVE==5) and if(IO.STATIC_FINAL_FIVE!=5)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s02;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__getCookies_Servlet_setHeaderServlet_13 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_FIVE == 5) {
            data = ""; 
            Cookie cookieSources[] = request.getCookies();
            if (cookieSources != null) {
                data = cookieSources[0].getValue();
            }
        } else {
            data = null;
        }

        if (IO.STATIC_FINAL_FIVE == 5) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_FIVE != 5) {
            data = null;
        } else {
            data = "foo"; 
        }

        if (IO.STATIC_FINAL_FIVE == 5) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_FIVE == 5) {
            data = ""; 
            Cookie cookieSources[] = request.getCookies();
            if (cookieSources != null) {
                data = cookieSources[0].getValue();
            }
        } else {
            data = null;
        }

        if (IO.STATIC_FINAL_FIVE != 5) {
            IO.writeLine("Benign, fixed string");
        } else {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_FIVE == 5) {
            data = ""; 
            Cookie cookieSources[] = request.getCookies();
            if (cookieSources != null) {
                data = cookieSources[0].getValue();
            }
        } else {
            data = null;
        }

        if (IO.STATIC_FINAL_FIVE == 5) {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}