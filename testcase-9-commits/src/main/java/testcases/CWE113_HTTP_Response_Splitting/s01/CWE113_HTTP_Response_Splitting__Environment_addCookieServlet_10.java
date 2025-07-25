// Continuing from the last state, adding goodB2G1 and goodB2G2 method implementations

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10 extends AbstractTestCaseServlet
{
    // bad, goodG2B1, and goodG2B2 methods as implemented in previous commits

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticTrue)
        {
            /* get environment variable ADD */
            /* POTENTIAL FLAW: Read data from an environment variable */
            data = System.getenv("ADD");
        }
        else
        {
            data = null; // Dead code for compiler safety
        }

        if (IO.staticFalse)
        {
            IO.writeLine("Benign, fixed string"); // Dead code
        }
        else
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticTrue)
        {
            /* get environment variable ADD */
            /* POTENTIAL FLAW: Read data from an environment variable */
            data = System.getenv("ADD");
        }
        else
        {
            data = null; // Dead code for compiler safety
        }

        if (IO.staticTrue)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", URLEncoder.encode(data, "UTF-8"));
                /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
                response.addCookie(cookieSink);
            }
        }
    }

    // Other methods remain as in the previous commit
}