package com.mervynlam.checkminesweeperscore.utils;

import com.zy.minesweeperStudio.bean.VideoDisplayBean;
import com.zy.minesweeperStudio.core.Minesweeper;

import java.io.File;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CaclUtils {
    public static void calcScore(int begNum, int intNum, int expNum) {
        File dir = new File(System.getProperty("user.dir"));
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("avf");
            }
        });
        List<Double> begTime = new ArrayList<Double>();
        List<Double> intTime = new ArrayList<Double>();
        List<Double> expTime = new ArrayList<Double>();
        List<Double> begBVS = new ArrayList<Double>();
        List<Double> intBVS = new ArrayList<Double>();
        List<Double> expBVS = new ArrayList<Double>();
        for (File file : files) {
            VideoDisplayBean bean = Minesweeper.parseVideo(file);
            String level = bean.getLevel();
            String bbbv = bean.getBbbv();
            String bbbvs = bean.getBbbvs();
            String time = bean.getTime();
            switch (level) {
                case "Beg":
                    if (Integer.valueOf(bbbv) < 10) break;
                    begTime.add(Double.valueOf(time));
                    begBVS.add(Double.valueOf(bbbvs));
                    break;
                case "Int":
                    intTime.add(Double.valueOf(time));
                    intBVS.add(Double.valueOf(bbbvs));
                    break;
                case "Exp":
                    expTime.add(Double.valueOf(time));
                    expBVS.add(Double.valueOf(bbbvs));
                    break;
            }
        }
        Comparator<Double> timeComparator = new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o1, o2);
            }
        };
        Comparator<Double> bvsComparator = new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return Double.compare(o2, o1);
            }
        };
        begTime.sort(timeComparator);
        intTime.sort(timeComparator);
        expTime.sort(timeComparator);
        begBVS.sort(bvsComparator);
        intBVS.sort(bvsComparator);
        expBVS.sort(bvsComparator);

        double begTimeTotal = 0.0;
        double intTimeTotal = 0.0;
        double expTimeTotal = 0.0;
        double begBVSTotal = 0.0;
        double intBVSTotal = 0.0;
        double expBVSTotal = 0.0;
        double begTimeEdge = 0.0;
        double intTimeEdge = 0.0;
        double expTimeEdge = 0.0;
        double begBVSEdge = 0.0;
        double intBVSEdge = 0.0;
        double expBVSEdge = 0.0;

        int begCnt = 1;
        int intCnt = 1;
        int expCnt = 1;
        System.out.print(String.format("%3s%4s%1s","Beg", "Time", "："));
        for (double begT : begTime) {
            System.out.print(String.format("%6.2f  ",begT));
            begTimeTotal = add(begTimeTotal, begT);
            if (begCnt++ >= begNum) {
                begTimeEdge = begT;
                break;
            }
        }
        System.out.println();
        System.out.print(String.format("%3s%4s%1s","Int", "Time", "："));
        for (double intT : intTime) {
            System.out.print(String.format("%6.2f  ",intT));
            intTimeTotal = add(intTimeTotal, intT);
            if (intCnt++ >= intNum) {
                intTimeEdge = intT;
                break;
            }
        }
        System.out.println();
        System.out.print(String.format("%3s%4s%1s","Exp", "Time", "："));
        for (double expT : expTime) {
            System.out.print(String.format("%6.2f  ",expT));
            expTimeTotal = add(expTimeTotal, expT);
            if (expCnt++ >= expNum) {
                expTimeEdge = expT;
                break;
            }
        }
        System.out.println();

        begCnt = 1;
        intCnt = 1;
        expCnt = 1;
        System.out.print(String.format("%3s%4s%1s","Beg", "Bvs", "："));
        for (double begB : begBVS) {
            System.out.print(String.format("%6.2f  ",begB));
            begBVSTotal = add(begBVSTotal, begB);
            if (begCnt++ >= begNum) {
                begBVSEdge = begB;
                break;
            }
        }
        System.out.println();
        System.out.print(String.format("%3s%4s%1s","Int", "Bvs", "："));
        for (double intB : intBVS) {
            System.out.print(String.format("%6.2f  ",intB));
            intBVSTotal = add(intBVSTotal, intB);
            if (intCnt++ >= intNum) {
                intBVSEdge = intB;
                break;
            }
        }
        System.out.println();
        System.out.print(String.format("%3s%4s%1s","Exp", "Bvs", "："));
        for (double expB : expBVS) {
            System.out.print(String.format("%6.2f  ",expB));
            expBVSTotal = add(expBVSTotal, expB);
            if (expCnt++ >= expNum) {
                expBVSEdge = expB;
                break;
            }
        }
        System.out.println();
        begCnt--;
        intCnt--;
        expCnt--;
        if (begNum - begCnt > 0)
            begTimeTotal = add(begTimeTotal, 10.0*(begNum - begCnt));
        if (intNum - intCnt > 0)
            intTimeTotal = add(intTimeTotal, 60.0*(intNum - intCnt));
        if (expNum - expCnt > 0)
            expTimeTotal = add(expTimeTotal, 240.0*(expNum - expCnt));

        String tTotal = String.format("%7s：%6.2f + %6.2f + %6.2f = %6.2f", "Time", begTimeTotal, intTimeTotal, expTimeTotal, add(begTimeTotal, add(intTimeTotal, expTimeTotal)));
        String tEdgeTotal = String.format("%7s：%6.2f   %6.2f   %6.2f", "Edge", begTimeEdge, intTimeEdge, expTimeEdge);
        String bTotal = String.format("%7s：%6.2f + %6.2f + %6.2f = %6.2f", "Bvs", begBVSTotal, intBVSTotal, expBVSTotal, add(begBVSTotal, add(intBVSTotal, expBVSTotal)));
        String bEdgeTotal = String.format("%7s：%6.2f   %6.2f   %6.2f", "Edge", begBVSEdge, intBVSEdge, expBVSEdge);
        System.out.println(tTotal);
        System.out.println(tEdgeTotal);
        System.out.println(bTotal);
        System.out.println(bEdgeTotal);
    }

    private static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
}