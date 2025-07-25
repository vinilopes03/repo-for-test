package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;

import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_05 extends AbstractTestCaseServlet {
    private boolean privateTrue = true;
    private boolean privateFalse = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // `bad` implementation remains unchanged
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (privateFalse) {
            data = null; // Dead code
        } else {
            data = "foo"; // Use a hardcoded string
        }

        if (privateTrue) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                response.addCookie(cookieSink); // Potential flaw
            }
        }
    }

    // Other method signatures remain unchanged

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}