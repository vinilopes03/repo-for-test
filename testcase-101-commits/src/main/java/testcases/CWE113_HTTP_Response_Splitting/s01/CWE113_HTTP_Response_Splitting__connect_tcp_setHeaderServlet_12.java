package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.AbstractTestCaseServlet;
import testcasesupport.IO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_12 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // ... (same as previous commit)
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            // FIX: Use a hardcoded string
            data = "foo";
        } else {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        } else {
            if (data != null) {
                // POTENTIAL FLAW: Input not verified before inclusion in header
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}