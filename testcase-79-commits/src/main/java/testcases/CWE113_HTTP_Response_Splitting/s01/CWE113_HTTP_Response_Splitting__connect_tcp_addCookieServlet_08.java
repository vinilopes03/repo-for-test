package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_08 extends AbstractTestCaseServlet {
    /* The methods below always return the same value, so a tool
     * should be able to figure out that every call to these
     * methods will return true or return false. */
    private boolean privateReturnsTrue() {
        return true;
    }

    private boolean privateReturnsFalse() {
        return false;
    }

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature, implementation to follow
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Method signature, implementation to follow
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}