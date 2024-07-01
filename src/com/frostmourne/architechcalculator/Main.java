package com.frostmourne.architechcalculator;

import com.frostmourne.architechcalculator.objects.*;
import com.frostmourne.architechcalculator.objects.symbols.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        new SymbolAdd().register();
        new SymbolDivide().register();
        new SymbolEquals().register();
        new SymbolLargeThan().register();
        new SymbolLowThan().register();
        new SymbolModular().register();
        new SymbolMultiply().register();
        new SymbolReduce().register();

        long startTime = new Date().getTime();
        System.out.println("example formula expression result = " +

                        Calculator.eval("5 + 5 * (10 * 10)")

                );

        System.out.println("example expression check = " +

                        Calculator.eval("50 > 40")

                );

        System.out.println("use time = " + (new Date().getTime() - startTime) + "ms");

    }
}