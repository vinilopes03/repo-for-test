package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_02 extends AbstractTestCaseServlet {
    
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        data = System.getenv("ADD");
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            response.addCookie(cookieSink);
        }
    }
    
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = "foo"; // Hardcoded string
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            response.addCookie(cookieSink);
        }
    }
    
    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data = "foo"; // Hardcoded string
        if (data != null) {
            Cookie cookieSink = new Cookie("lang", data);
            response.addCookie(cookieSink);
        }
    }
    
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in future commits
    }
    
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in future commits
    }
    
    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method implementation will be added in future commits
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}