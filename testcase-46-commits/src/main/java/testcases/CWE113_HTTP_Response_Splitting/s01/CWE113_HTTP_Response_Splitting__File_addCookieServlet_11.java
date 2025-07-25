package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;

import java.util.logging.Level;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_11 extends AbstractTestCaseServlet {

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrue()) {
            // To be implemented
        } else {
            data = null;
        }

        if (IO.staticReturnsTrue()) {
            // To be implemented
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsFalse()) {
            data = null;
        } else {
            // To be implemented
        }

        if (IO.staticReturnsTrue()) {
            // To be implemented
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrue()) {
            // To be implemented
        } else {
            data = null;
        }

        if (IO.staticReturnsTrue()) {
            // To be implemented
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrue()) {
            // To be implemented
        } else {
            data = null;
        }

        if (IO.staticReturnsFalse()) {
            IO.writeLine("Benign, fixed string");
        } else {
            // To be implemented
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticReturnsTrue()) {
            // To be implemented
        } else {
            data = null;
        }

        if (IO.staticReturnsTrue()) {
            // To be implemented
        }
    }

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