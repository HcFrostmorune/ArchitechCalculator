package com.frostmourne.architechcalculator.objects.interfaces;

import com.frostmourne.architechcalculator.objects.managers.SymbolManager;

public interface CalculatorSymbol {

    char getChar();
    double eval(double a,double b);
    int getPriority();
    default void register(){
        SymbolManager.register(this);
    }

}
