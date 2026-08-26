package $Expertise.DataStructures.implemented.Problems;

import java.util.ArrayList;
import java.util.HashMap;

class RomanToIntegerSolution{

    void printRoman(int num){

        String[] symbols = {
                "M", "CM", "D", "CD",
                "C", "XC", "L", "XL",
                "X", "IX", "V", "IV", "I"
        };

        int[] values = {
                1000, 900, 500, 400,
                100, 90, 50, 40,
                10, 9, 5, 4, 1
        };

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <values.length ; i++) {

            while (num >= values[i]){
                num-=values[i];
                stringBuilder.append(symbols[i]);
            }
        }

        System.out.println(stringBuilder);

    }
}

public class RomanToInteger {

    static void main() {
        RomanToIntegerSolution romanToInteger = new RomanToIntegerSolution();
        romanToInteger.printRoman(3749);
    }
}
