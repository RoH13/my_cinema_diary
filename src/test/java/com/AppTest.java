package com;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Connection;
import com.cinema.util.DataBaseConnection;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /*public void shouldGetJdbcConnection() {
        try(Connection connection = com.cinema.util.DataBaseConnection.getBaseConnection()){
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        };
    }*/
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
