package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

import java.util.logging.Level;

import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__connect_tcp_addCookieServlet_16 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for bad case
    }

    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodG2B (Good Source, Bad Sink)
    }

    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Method signature for goodB2G (Bad Source, Good Sink)
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}