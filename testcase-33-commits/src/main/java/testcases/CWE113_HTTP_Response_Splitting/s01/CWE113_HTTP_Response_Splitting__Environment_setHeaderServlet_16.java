package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_16 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        while (true) {
            data = System.getenv("ADD");
            break;
        }

        while (true) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
            break;
        }
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;

        while (true) {
            // FIX: Use a hardcoded string
            data = "foo";
            break;
        }

        while (true) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
            break;
        }
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature with no implementation
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature with no implementation
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}