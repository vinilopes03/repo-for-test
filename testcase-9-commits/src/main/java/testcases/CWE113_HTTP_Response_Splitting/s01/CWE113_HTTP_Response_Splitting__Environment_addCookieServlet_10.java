// Continuing from the last state, adding goodG2B1 and goodG2B2 method implementations

public class CWE113_HTTP_Response_Splitting__Environment_addCookieServlet_10 extends AbstractTestCaseServlet
{
    // bad method as implemented in the previous commit

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticFalse)
        {
            data = null; // Dead code for compiler safety
        }
        else
        {
            /* FIX: Use a hardcoded string */
            data = "foo";
        }

        if (IO.staticTrue)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                response.addCookie(cookieSink);
            }
        }
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (IO.staticTrue)
        {
            /* FIX: Use a hardcoded string */
            data = "foo";
        }
        else
        {
            data = null; // Dead code for compiler safety
        }

        if (IO.staticTrue)
        {
            if (data != null)
            {
                Cookie cookieSink = new Cookie("lang", data);
                /* POTENTIAL FLAW: Input not verified before inclusion in the cookie */
                response.addCookie(cookieSink);
            }
        }
    }

    // Other methods remain as in the previous commit
}