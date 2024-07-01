package com.frostmourne.architechcalculator.objects.managers;


import com.frostmourne.architechcalculator.objects.interfaces.CalculatorSymbol;

import java.util.HashMap;
import java.util.Map;

public class SymbolManager {

    public static final Map<Character, CalculatorSymbol> SYMBOL_MAP = new HashMap<>();

    public static void register(CalculatorSymbol calculatorSymbol){
        SYMBOL_MAP.put(calculatorSymbol.getChar(),calculatorSymbol);
    }
    public static CalculatorSymbol getSymbolByChar(char c){
        return SYMBOL_MAP.get(c);
    }
    public static boolean containsSymbol(String line){
        return SYMBOL_MAP.values().parallelStream().anyMatch(calculatorSymbol -> line.contains(String.valueOf(calculatorSymbol.getChar())));
    }

}
