package com.mervynlam.checkminesweeperscore.utils;

import com.zy.minesweeperStudio.bean.VideoDisplayBean;
import com.zy.minesweeperStudio.core.Minesweeper;

import java.io.File;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CaclUtils {
    public static void calcScore(int begNum, int intNum, int expNum) {
        File dir = new File(System.getProperty("user.dir"));
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".avf");
            }
        });

        //录像list
        List<VideoDisplayBean> begVideos = new ArrayList<>();
        List<VideoDisplayBean> intVideos = new ArrayList<>();
        List<VideoDisplayBean> expVideos = new ArrayList<>();
        for (File file : files) {
            VideoDisplayBean bean = Minesweeper.parseVideo(file);
            String level = bean.getLevel();
            String bbbv = bean.getBbbv();
            switch (level) {
                case "Beg":
                    if (Integer.valueOf(bbbv) < 10) break;
                    begVideos.add(bean);
                    break;
                case "Int":
                    intVideos.add(bean);
                    break;
                case "Exp":
                    expVideos.add(bean);
                    break;
            }
        }

        //标识set
        Set<String> userIdSet = new HashSet<>();
        userIdSet.addAll(getUserId(begVideos));
        userIdSet.addAll(getUserId(intVideos));
        userIdSet.addAll(getUserId(expVideos));

        //time bvs list
        List<Double> begTime = getTimeList(begVideos, begNum);
        List<Double> begBVS = getBvsList(begVideos, begNum);
        List<Double> intTime = getTimeList(intVideos, intNum);
        List<Double> intBVS = getBvsList(intVideos, intNum);
        List<Double> expTime = getTimeList(expVideos, expNum);
        List<Double> expBVS = getBvsList(expVideos, expNum);

        //total edge
        double begTimeTotal = getTotalTime(begTime, begNum, 10);
        double intTimeTotal = getTotalTime(intTime, intNum, 60);
        double expTimeTotal = getTotalTime(expTime, expNum, 240);
        double begBVSTotal = getTotalBvs(begBVS);
        double intBVSTotal = getTotalBvs(intBVS);
        double expBVSTotal = getTotalBvs(expBVS);
        double begTimeEdge = getEdge(begTime, begNum);
        double intTimeEdge = getEdge(intTime, intNum);
        double expTimeEdge = getEdge(expTime, expNum);
        double begBVSEdge = getEdge(begBVS, begNum);
        double intBVSEdge = getEdge(intBVS, intNum);
        double expBVSEdge = getEdge(expBVS, expNum);

        //打印信息
        System.out.println("包含标识：");
        for (String userId : userIdSet) {
            System.out.println(userId);
        }

        System.out.print(String.format("%3s%4s%1s","Beg", "Time", "："));
        for (double begT : begTime) {
            System.out.print(String.format("%6.2f  ",begT));
        }
        System.out.println();
        System.out.print(String.format("%3s%4s%1s","Int", "Time", "："));
        for (double intT : intTime) {
            System.out.print(String.format("%6.2f  ",intT));
        }
        System.out.println();
        System.out.print(String.format("%3s%4s%1s","Exp", "Time", "："));
        for (double expT : expTime) {
            System.out.print(String.format("%6.2f  ",expT));
        }
        System.out.println();

        System.out.print(String.format("%3s%4s%1s","Beg", "Bvs", "："));
        for (double begB : begBVS) {
            System.out.print(String.format("%6.2f  ",begB));
        }
        System.out.println();
        System.out.print(String.format("%3s%4s%1s","Int", "Bvs", "："));
        for (double intB : intBVS) {
            System.out.print(String.format("%6.2f  ",intB));
        }
        System.out.println();
        System.out.print(String.format("%3s%4s%1s","Exp", "Bvs", "："));
        for (double expB : expBVS) {
            System.out.print(String.format("%6.2f  ",expB));
        }
        System.out.println();

        String games = String.format("%6s：%6d %6d %6d", "局数", begVideos.size(), intVideos.size(), expVideos.size());
        String tTotal = String.format("%7s：%6.2f + %6.2f + %6.2f = %6.2f", "Time", begTimeTotal, intTimeTotal, expTimeTotal, add(begTimeTotal, add(intTimeTotal, expTimeTotal)));
        String tEdgeTotal = String.format("%7s：%6.2f   %6.2f   %6.2f", "Edge", begTimeEdge, intTimeEdge, expTimeEdge);
        String bTotal = String.format("%7s：%6.2f + %6.2f + %6.2f = %6.2f", "Bvs", begBVSTotal, intBVSTotal, expBVSTotal, add(begBVSTotal, add(intBVSTotal, expBVSTotal)));
        String bEdgeTotal = String.format("%7s：%6.2f   %6.2f   %6.2f", "Edge", begBVSEdge, intBVSEdge, expBVSEdge);
        System.out.println(games);
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

    private static int doubleCompare(double v1, double v2) {
        return Double.compare(v1, v2);
    }

    private static List<Double> getTimeList(List<VideoDisplayBean> sourceList, int maxNum) {
        return sourceList.stream().map(bean -> Double.valueOf(bean.getTime()))
                .sorted((double1, double2) -> doubleCompare(double1, double2))
                .limit(maxNum)
                .collect(Collectors.toList());
    }

    private static List<Double> getBvsList(List<VideoDisplayBean> sourceList, int maxNum) {
        return sourceList.stream().map(bean -> Double.valueOf(bean.getBbbvs()))
                .sorted((double1, double2) -> doubleCompare(double2, double1))
                .limit(maxNum)
                .collect(Collectors.toList());
    }

    private static Double getTotalTime(List<Double> timeList, int maxNum, int defaultNum) {
        double total = timeList.stream().mapToDouble(time -> time).sum();
        if (timeList.size() < maxNum) {
            total += (defaultNum * (maxNum - timeList.size()));
        }
        return total;
    }

    private static Double getTotalBvs(List<Double> bvsList) {
        return bvsList.stream().mapToDouble(bvs -> bvs).sum();
    }

    private static Double getEdge(List<Double> list, int maxNum) {
        return list.size()>=maxNum?list.get(maxNum-1):0.0;
    }

    private static Set<String> getUserId(List<VideoDisplayBean> videoList) {
        return videoList.stream().map(video -> video.getUserID()).collect(Collectors.toSet());
    }

}