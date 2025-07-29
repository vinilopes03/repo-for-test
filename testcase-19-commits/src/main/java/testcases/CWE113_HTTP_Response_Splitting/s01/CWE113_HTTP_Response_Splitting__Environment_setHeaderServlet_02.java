public class CWE113_HTTP_Response_Splitting__Environment_setHeaderServlet_02 extends AbstractTestCaseServlet
{
    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (true)
        {
            /* get environment variable ADD */
            /* POTENTIAL FLAW: Read data from an environment variable */
            data = System.getenv("ADD");
        }
        else
        {
            data = null;
        }

        if (true)
        {
            if (data != null)
            {
                /* POTENTIAL FLAW: Input not verified before inclusion in header */
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }
    // Other methods remain unchanged
}

private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (false)
    {
        data = null;
    }
    else
    {
        /* FIX: Use a hardcoded string */
        data = "foo";
    }

    if (true)
    {
        if (data != null)
        {
            /* POTENTIAL FLAW: Input not verified before inclusion in header */
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }
}

private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (true)
    {
        /* FIX: Use a hardcoded string */
        data = "foo";
    }
    else
    {
        data = null;
    }

    if (true)
    {
        if (data != null)
        {
            /* POTENTIAL FLAW: Input not verified before inclusion in header */
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }
}

private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (true)
    {
        /* get environment variable ADD */
        /* POTENTIAL FLAW: Read data from an environment variable */
        data = System.getenv("ADD");
    }
    else
    {
        data = null;
    }

    if (false)
    {
        IO.writeLine("Benign, fixed string");
    }
    else
    {
        if (data != null)
        {
            /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
            data = URLEncoder.encode(data, "UTF-8");
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }
}

private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
{
    String data;
    if (true)
    {
        /* get environment variable ADD */
        /* POTENTIAL FLAW: Read data from an environment variable */
        data = System.getenv("ADD");
    }
    else
    {
        data = null;
    }

    if (true)
    {
        if (data != null)
        {
            /* FIX: use URLEncoder.encode to hex-encode non-alphanumerics */
            data = URLEncoder.encode(data, "UTF-8");
            response.setHeader("Location", "/author.jsp?lang=" + data);
        }
    }
}