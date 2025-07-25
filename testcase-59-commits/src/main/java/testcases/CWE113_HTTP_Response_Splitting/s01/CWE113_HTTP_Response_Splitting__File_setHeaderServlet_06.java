package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_06 extends AbstractTestCaseServlet {

    private static final int PRIVATE_STATIC_FINAL_FIVE = 5;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation as in Commit 2
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_FIVE != 5) {
            data = null; // Dead code
        } else {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (PRIVATE_STATIC_FINAL_FIVE == 5) {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // placeholder
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // placeholder
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // placeholder
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // placeholder
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}