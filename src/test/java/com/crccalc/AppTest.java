package com.crccalc;

import static java.lang.System.out;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for CRC Calc.
 */
public class AppTest extends TestCase
{
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

    public void testApp()
    {
        Check(Crc8.Params);
        Check(Crc16.Params);
        Check(Crc32.Params);
        Check(Crc64.Params);
    }
    
    private static void Check(AlgoParams[] params)
    {
        for (int i = 0; i < params.length; i++) {
            CrcCalculator calculator = new CrcCalculator(params[i]);
            long result = calculator.Calc(CrcCalculator.TestBytes, 0, CrcCalculator.TestBytes.length);
            out.print(String.format("%-20s : checksum 0x%-20s -> ", calculator.Parameters.Name, Long.toHexString(result).toUpperCase()));
            if (result == calculator.Parameters.Check) {
                out.println("OK");
            } else {
                out.println("FAILED");
            }
            assertEquals(calculator.Parameters.Check, result);
        }
    }
}
