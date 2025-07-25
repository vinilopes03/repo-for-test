package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_04 extends AbstractTestCaseServlet {

    private static final boolean PRIVATE_STATIC_FINAL_TRUE = true;
    private static final boolean PRIVATE_STATIC_FINAL_FALSE = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_TRUE) {
            data = System.getenv("ADD");
        } else {
            data = null;
        }

        if (PRIVATE_STATIC_FINAL_TRUE) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (PRIVATE_STATIC_FINAL_FALSE) {
            data = null;
        } else {
            data = "foo";
        }

        if (PRIVATE_STATIC_FINAL_TRUE) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        mainFromParent(args);
    }
}