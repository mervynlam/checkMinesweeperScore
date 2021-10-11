package com.mervynlam.checkminesweeperscore.core;

import com.mervynlam.checkminesweeperscore.utils.CaclUtils;

import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        int begNum = 0;
        int intNum = 0;
        int expNum = 0;
        if (args != null) {
            for (String arg : args) {
                if (arg.startsWith("-b")) {
                    begNum = Integer.valueOf(arg.substring(2));
                }
                if (arg.startsWith("-i")) {
                    intNum = Integer.valueOf(arg.substring(2));
                }
                if (arg.startsWith("-e")) {
                    expNum = Integer.valueOf(arg.substring(2));
                }
            }
        }
        Scanner sc = new Scanner(System.in);
        do {
            CaclUtils.calcScore(begNum, intNum, expNum);
        } while(sc.nextLine()!=null);
    }
}
