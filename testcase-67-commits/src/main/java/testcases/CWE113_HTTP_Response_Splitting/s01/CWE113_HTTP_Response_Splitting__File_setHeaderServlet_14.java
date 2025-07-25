package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_14 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation from previous commits
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation from previous commits
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            /* FIX: Use a hardcoded string */
            data = "foo";
        } else {
            /* INCIDENTAL: CWE 561 Dead Code */
            data = null;
        }
        if (IO.staticFive == 5) {
            if (data != null) { 
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    // Placeholder for other good methods
    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {}
}