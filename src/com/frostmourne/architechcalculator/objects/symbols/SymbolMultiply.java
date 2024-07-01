package com.frostmourne.architechcalculator.objects.symbols;

import com.frostmourne.architechcalculator.objects.interfaces.CalculatorSymbol;

public class SymbolMultiply implements CalculatorSymbol {
    @Override
    public char getChar() {
        return '*';
    }

    @Override
    public double eval(double a, double b) {
        return a*b;
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
