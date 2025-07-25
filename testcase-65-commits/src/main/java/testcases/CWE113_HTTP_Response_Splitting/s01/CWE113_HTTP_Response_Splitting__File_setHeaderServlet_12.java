package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.*;

import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_setHeaderServlet_12 extends AbstractTestCaseServlet {
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            data = ""; // Initialize data
            // Placeholder for reading from file
        } else {
            data = "foo"; // Use a hardcoded string
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        } else {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            data = "foo"; // Use a hardcoded string
        } else {
            data = "foo"; // Use a hardcoded string
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        } else {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrueOrFalse()) {
            data = ""; // Initialize data
            // Placeholder for reading from file
        } else {
            data = ""; // Initialize data
            // Placeholder for reading from file
        }

        if (IO.staticReturnsTrueOrFalse()) {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        } else {
            if (data != null) {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}