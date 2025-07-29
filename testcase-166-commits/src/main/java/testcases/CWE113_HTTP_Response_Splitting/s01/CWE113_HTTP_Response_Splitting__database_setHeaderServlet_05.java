/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE113_HTTP_Response_Splitting__database_setHeaderServlet_05.java
Label Definition File: CWE113_HTTP_Response_Splitting.label.xml
Template File: sources-sinks-05.tmpl.java
*/
/*
* @description
* CWE: 113 HTTP Response Splitting
* BadSource: database Read data from a database
* GoodSource: A hardcoded string
* Sinks: setHeaderServlet
*    GoodSink: URLEncode input
*    BadSink : querystring to setHeader()
* Flow Variant: 05 Control flow: if(privateTrue) and if(privateFalse)
*
* */

package testcases.CWE113_HTTP_Response_Splitting.s01;
import testcasesupport.*;

import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.net.URLEncoder;

public class CWE113_HTTP_Response_Splitting__database_setHeaderServlet_05 extends AbstractTestCaseServlet
{
    private boolean privateTrue = true;
    private boolean privateFalse = false;

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation from previous commits...
    }

    private void goodG2B1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation from previous commits...
    }

    private void goodG2B2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        // Implementation from previous commits...
    }

    private void goodB2G1(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (privateTrue)
        {
            data = ""; /* Initialize data */
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                connection = IO.getDBConnection();
                preparedStatement = connection.prepareStatement("select name from users where id=0");
                resultSet = preparedStatement.executeQuery();
                data = resultSet.getString(1);
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error with SQL statement", exceptSql);
            }
            finally
            {
                // Close database objects
                try { if (resultSet != null) resultSet.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing ResultSet", exceptSql); }
                try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql); }
                try { if (connection != null) connection.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql); }
            }
        }
        else
        {
            data = null;
        }

        if (privateFalse)
        {
            IO.writeLine("Benign, fixed string");
        }
        else
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    private void goodB2G2(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data;
        if (privateTrue)
        {
            data = ""; /* Initialize data */
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                connection = IO.getDBConnection();
                preparedStatement = connection.prepareStatement("select name from users where id=0");
                resultSet = preparedStatement.executeQuery();
                data = resultSet.getString(1);
            }
            catch (SQLException exceptSql)
            {
                IO.logger.log(Level.WARNING, "Error with SQL statement", exceptSql);
            }
            finally
            {
                // Close database objects
                try { if (resultSet != null) resultSet.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing ResultSet", exceptSql); }
                try { if (preparedStatement != null) preparedStatement.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing PreparedStatement", exceptSql); }
                try { if (connection != null) connection.close(); } catch (SQLException exceptSql) { IO.logger.log(Level.WARNING, "Error closing Connection", exceptSql); }
            }
        }
        else
        {
            data = null;
        }

        if (privateTrue)
        {
            if (data != null)
            {
                data = URLEncoder.encode(data, "UTF-8");
                response.setHeader("Location", "/author.jsp?lang=" + data);
            }
        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B1(request, response);
        goodG2B2(request, response);
        goodB2G1(request, response);
        goodB2G2(request, response);
    }

    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}