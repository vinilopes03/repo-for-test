package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_09 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Implementation from previous commit
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_FALSE) {
            data = null;
        } else {
            data = "foo"; // FIX: Use a hardcoded string
        }

        if (IO.STATIC_FINAL_TRUE) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_TRUE) {
            data = "foo"; // FIX: Use a hardcoded string
        } else {
            data = null;
        }

        if (IO.STATIC_FINAL_TRUE) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data); // POTENTIAL FLAW
            }
        }
    }

    // Remaining methods to be implemented
}