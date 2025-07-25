package testcases.CWE113_HTTP_Response_Splitting.s01;

import testcasesupport.AbstractTestCaseServlet;
import testcasesupport.IO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CWE113_HTTP_Response_Splitting__File_addCookieServlet_13 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        // Existing implementation of bad method
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_FIVE != 5) {
            data = null; // Dead code, but ensures data is initialized
        } else {
            // FIX: Use a hardcoded string
            data = "foo";
        }

        if (IO.STATIC_FINAL_FIVE == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.STATIC_FINAL_FIVE == 5) {
            // FIX: Use a hardcoded string
            data = "foo";
        } else {
            data = null; // Dead code, but ensures data is initialized
        }

        if (IO.STATIC_FINAL_FIVE == 5) {
            if (data != null) {
                Cookie cookieSink = new Cookie("lang", data);
                // POTENTIAL FLAW: Input not verified before inclusion in the cookie
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable { }
    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable { }

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