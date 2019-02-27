package com.crccalc;

import java.util.Scanner;

import static java.lang.System.out;

public class TestApp {

    public static void main(String[] args) {
        out.print("Calculate CRC checksums for test data 0x");
        for (int i=0; i<CrcCalculator.TestBytes.length; i++) {
            out.print(String.format("%02x", CrcCalculator.TestBytes[i]));
        }
        out.println();
        
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
        }
    }
}
