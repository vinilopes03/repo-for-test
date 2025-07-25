package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

public class CWE113_HTTP_Response_Splitting__connect_tcp_setHeaderServlet_14 extends AbstractTestCaseServlet {

    // bad and goodG2B1 methods omitted for brevity

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String data;
        if (IO.staticFive == 5) {
            data = "foo";
        } else {
            data = null;
        }

        if (IO.staticFive == 5) {
            if (data != null) {
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    // Other methods omitted for brevity

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