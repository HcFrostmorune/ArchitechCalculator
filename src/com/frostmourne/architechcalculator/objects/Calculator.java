package com.frostmourne.architechcalculator.objects;

import com.frostmourne.architechcalculator.objects.interfaces.CalculatorSymbol;
import com.frostmourne.architechcalculator.objects.managers.SymbolManager;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Calculator {

    public static String eval(String line){

        line = line.replace(" ","");
        while (line.contains("(") && line.contains(")")){
            String s = subString(line, "(", ")");
            line = line.replace("(" + s + ")",String.valueOf(calculate(s)));
        }

        line = String.valueOf(calculate(line));

        return line;
    }

    public static String evalAsync(String input){

        try {
            return CompletableFuture.supplyAsync(
                    ()->{
                        String line = input;
                        line = line.replace(" ","");
                        while (line.contains("(") && line.contains(")")){
                            String s = subString(line, "(", ")");
                            line = line.replace("(" + s + ")",String.valueOf(calculate(s)));
                        }

                        line = String.valueOf(calculate(line));

                        return line;
                    }
            ).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static double calculate(String input){


        input = input.replace(" ","");
        if (input.contains("(") && input.contains(")")){
            String sub = subString(input, "(", ")");
            input = input.replace("(" + sub + ")",String.valueOf(calculate(sub)));
        }
        Map<Integer, CalculatorSymbol> symbols = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (SymbolManager.getSymbolByChar(c) != null){
                symbols.put(i,SymbolManager.getSymbolByChar(c));
            }
        }
        while (SymbolManager.containsSymbol(input)){
            Map<Integer, CalculatorSymbol> finalSymbols = symbols;
            int index = symbols.keySet().parallelStream()
                    .min(Comparator.comparingInt(i -> finalSymbols.get(i).getPriority()))
                    .orElse(-1);

            if (index != -1){

                CalculatorSymbol calculatorSymbol = symbols.get(index);

                String afterNumber = getAfterNumber(input, index);
                String beforeNumber = getBeforeNumber(input, index);

                double result = calculatorSymbol.eval(Double.parseDouble(beforeNumber), Double.parseDouble(afterNumber));
                input = input.replace(beforeNumber + calculatorSymbol.getChar() + afterNumber,String.valueOf(result));

                symbols = new HashMap<>();
                for (int i = 0; i < input.length(); i++) {
                    char c = input.charAt(i);
                    if (SymbolManager.getSymbolByChar(c) != null){
                        symbols.put(i,SymbolManager.getSymbolByChar(c));
                    }
                }
            }

        }
        return Double.parseDouble(input);
    }


    private static String getAfterNumber(String str,int symbolIndex){

        boolean number = true;
        symbolIndex += 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (number){
            if (symbolIndex == str.length() ){
                break;
            }
            char c = str.charAt(symbolIndex++);
            if (Character.isDigit(c) || c == '.'){
                stringBuilder.append(c);
            }else{
                number = false;
            }
        }
        return stringBuilder.toString();

    }

    private static String getBeforeNumber(String str,int symbolIndex){

        symbolIndex -= 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (true){
            if (symbolIndex < 0){
                break;
            }
            char c = str.charAt(symbolIndex);
            if (Character.isDigit(c) || c == '.'){
                stringBuilder.append(c);
            }else{
                break;
            }
            symbolIndex--;
        }
        return stringBuilder.reverse().toString();

    }


    public static String subString(String input , String prefix , String suffix){
        Stack<Integer> stack = new Stack<>();
        String text = "";

        for (int i = 0; i < input.length(); i++) {
            String c = String.valueOf(input.charAt(i));


            if (c.equalsIgnoreCase(prefix)){
                stack.push(i);
            }
            if (c.equalsIgnoreCase(suffix)){
                int prefixIndex = stack.pop();
                text = input.substring(prefixIndex + 1 , i);
            }
        }

        return text;
    }

}
