package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_13 extends AbstractTestCaseServlet {

    // Bad method
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature, implementation will be added in the next commit
    }

    // Good method variants
    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature, implementation will be added in the next commit
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature, implementation will be added in the next commit
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature, implementation will be added in the next commit
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature, implementation will be added in the next commit
    }

    // Good method calling all good variants
    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}