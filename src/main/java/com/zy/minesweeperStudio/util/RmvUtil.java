package com.zy.minesweeperStudio.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zy.minesweeperStudio.bean.MVFInfo;
import com.zy.minesweeperStudio.bean.VideoCheckBean;
import com.zy.minesweeperStudio.bean.VideoDisplayBean;

/**
 * 拆分方法该方法主要内容为对rmv文件解析 关于rmv 介绍 rmv是用VSweeper 扫的，一般用在线上比赛上 早期版本后缀名为umv
 * 
 * @author zhangye
 * @version 2013-1120
 */
public class RmvUtil implements VideoUtil
{

    /** 类型rmv */
    private String TYPE_RMV = "rmv";
    /** 版本2.0 */
    private String VERSION2 = "2.0";

    /**
     * 检查录像版本 扫雷网录像以97为主 偶见97之前版本 初版不保证健壮性 即认为传入文件一定为录像文件
     * <table style="color: blue; margin-left: 20px" border="1" >
     * <tr>
     * <th>条件1</th>
     * <th>条件2</th>
     * <th >判定结果</th>
     * </tr>
     * <tr>
     * <td rowspan="4">第一位是0x11 且第2位是0x4D</td>
     * <td>第27位（0x1b）值0x35 (字符5)</td>
     * <td>97版本</td>
     * </tr>
     * <tr>
     * 
     * <td>第27位（0x1b）值0x36 (字符6)</td>
     * <td>06版本</td>
     * </tr>
     * <tr>
     * 
     * <td>第27位（0x1b）值0x37 (字符7)</td>
     * <td>07版本</td>
     * </tr>
     * <tr>
     * 
     * <td>第27位（0x1b）值0x38 (字符8)</td>
     * <td>biu版本</td>
     * </tr>
     * <tr>
     * <td colspan=2>第一位是0x00 且第2位是0x00的情况</td>
     * <td >97版本（缺少头部）</td>
     * </tr>
     * <tr>
     * <td colspan=2>非该情况</td>
     * <td >pre97的版本（96或更早）</td>
     * </tr>
     * </table>
     * <br/>
     * 
     * @param byteStream
     *            文件流
     * @see <a href="http://www.minesweeper.info/forum/viewtopic.php?f=26&t=86"> http://www.minesweeper.info/forum/viewtopic.php?f=26&t=86</a>
     */
    /*
     * public VideoCheckBean checkVersion(byte[] byteStream) { VideoCheckBean bean = new VideoCheckBean(); String videoType = new String(byteStream, 1, 4); bean.videoType = TYPE_RMV; bean.videoVersion
     * = VERSION2; return bean; }
     */

    /**
     * 解析录像版本 扫雷网录像以97为主 偶见97之前版本
     */
    public void analyzeVideo(byte[] byteStream, VideoDisplayBean bean)
    {
    }

    /**
     * 解析录像版本 头4位为版本 正常的显示为 *rmv（0x2A 0x72 0x6D 0x76） 之后2为是录像type 正常的为 1(0x00 0x01)
     */
    public MVFInfo analyzingVideo(byte[] byteStream, String name){
    	return null;
    }
//    {
//        // VideoInfo videoInfo=new VideoInfo();
//        VideoCheckBean checkBean = null;
//        //        checkVersion(byteStream);
//        if (checkBean.checkFlag)
//        {
//
//        }
//        // type 必须为1
//        int type1 = byteStream[0x04] & 0xFF;
//        int type2 = byteStream[0x05] & 0xFF;
//        int type = type1 * 256 + type2;
//        if (type != 1)
//        {
//
//        }
//        // fs 再取4位
//        int fs1 = byteStream[0x06] & 0xFF;
//        int fs2 = byteStream[0x07] & 0xFF;
//        int fs3 = byteStream[0x08] & 0xFF;
//        int fs4 = byteStream[0x09] & 0xFF;
//        long fs = fs1 * 65536 + fs2 * 16777216 + fs3 + fs4 * 256;
//        // result_string_size
//        int rs1 = byteStream[0x0A] & 0xFF;
//        int rs2 = byteStream[0x0B] & 0xFF;
//        int rs = rs1 * 256 + rs2;
//        // version_info_size
//        int vi1 = byteStream[0x0C] & 0xFF;
//        int vi2 = byteStream[0x0D] & 0xFF;
//        int vi = vi1 * 256 + vi2;
//        // play_info_size
//        int pi1 = byteStream[0x0E] & 0xFF;
//        int pi2 = byteStream[0x0F] & 0xFF;
//        int pi = pi1 * 256 + pi2;
//        // board_size
//        int bo1 = byteStream[0x10] & 0xFF;
//        int bo2 = byteStream[0x11] & 0xFF;
//        int bo = bo1 * 256 + bo2;
//        // preflags_size
//        int pf1 = byteStream[0x12] & 0xFF;
//        int pf2 = byteStream[0x13] & 0xFF;
//        int pf = pf1 * 256 + pf2;
//        // properties_size
//        int pp1 = byteStream[0x14] & 0xFF;
//        int pp2 = byteStream[0x15] & 0xFF;
//        int pp = pp1 * 256 + pp2;
//        // vid_size
//        int vid1 = byteStream[0x16] & 0xFF;
//        int vid2 = byteStream[0x17] & 0xFF;
//        int vid3 = byteStream[0x18] & 0xFF;
//        int vid4 = byteStream[0x19] & 0xFF;
//        long vid = vid1 * 65536 + vid2 * 16777216 + vid3 + vid4 * 256;
//        // cs_size
//        int cs1 = byteStream[0x1A] & 0xFF;
//        int cs2 = byteStream[0x1B] & 0xFF;
//        int cs = cs1 * 256 + cs2;
//        // 0x1C // newline
//        // result_string
//        String resultString = new String(byteStream, 0x1D, rs - 1);
//        String[] rsSplit = resultString.split("#");
//        // 级别
//        String rsSplit1 = rsSplit[0].split(":")[1];
//        if ("Beginner".equals(rsSplit1))
//        {
//            rsSplit1 = Const.LEVEL_BEG;
//        }
//        else if ("Intermediate".equals(rsSplit1))
//        {
//            rsSplit1 = Const.LEVEL_INT;
//        }
//        else
//        {
//            rsSplit1 = Const.LEVEL_EXP;
//        }
//        // SCORE
//        String rsSplit2 = rsSplit[1].split(":")[1];
//        // NAME
//        String rsSplit3 = rsSplit[2].split(":")[1];
//        // NICK
//        String rsSplit4 = rsSplit[3].split(":")[1];
//        // 3BV
//        String rsSplit5 = rsSplit[4].split(":")[1];
//        // nf
//        String rsSplit6 = rsSplit[5].split(":")[1];
//        // timeStamp
//        String timeStamp = rsSplit[6].split(":")[1];
//        long ts = Long.parseLong(timeStamp) * 1000L;
//        Date dt = new Date(ts);
//        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
//        String videots = sdf.format(dt);
//        String style = "1".equals(rsSplit6) ? Const.STYLE_NF : Const.STYLE_FL;
//        int startLength = 0x1D + rs - 1;
//        // 版本信息
//        String versionInfo = new String(byteStream, startLength, vi);
//        startLength += vi;
//        int pis1 = byteStream[startLength] & 0xFF;
//        int pis2 = byteStream[startLength + 1] & 0xFF;
//        int pis = pis1 * 256 + pis2;
//        int playInfoLength = byteStream[startLength + 2] & 0xFF;
//        // playInfo信息
//        String playInfo = new String(byteStream, startLength + 3, playInfoLength);
//        startLength += playInfoLength + 7;
//        // board信息 width height
//        int width = byteStream[startLength] & 0xFF;
//        int height = byteStream[startLength + 1] & 0xFF;
//        int mines1 = byteStream[startLength + 2] & 0xFF;
//        int mines2 = byteStream[startLength + 3] & 0xFF;
//        int mine = mines1 * 256 + mines2;
//        int rmvSize = width * height;
//        Cell[] rmvBoard = new Cell[rmvSize];
//        for (int i = 0; i < rmvSize; i++)
//        {
//            rmvBoard[i] = new Cell();
//            rmvBoard[i].mine = (rmvBoard[i].opened = rmvBoard[i].flagged = rmvBoard[i].opening = rmvBoard[i].opening2 = 0);
//        }
//        startLength += 4;
//        for (int i = 0; i < mine; i++)
//        {
//            // posX 表示行1
//            // posY 表示列6
//            int c = byteStream[(startLength + i * 2)] & 0xFF;
//            int d = byteStream[(startLength + 1 + i * 2)] & 0xFF;
//            int pos = d * width + c;
//            rmvBoard[pos].mine = 1;
//        }
//        Cells[] cells = new Cells[(height + 2) * (width + 2)];
//        for (int i = 0; i < (height + 2) * (width + 2); i++)
//        {
//            cells[i] = new Cells(0);
//        }
//        for (int i = 0; i < mine; i++)
//        {
//            int posY = byteStream[(startLength + i * 2)] & 0xFF;
//            int posX = byteStream[(startLength + 1 + i * 2)] & 0xFF;
//
//            int pos = (posX) * (width + 2) + posY;
//            System.out.println(posX + " " + posY + " " + pos);
//            cells[pos].what = 9;
//        }
//        for (int i = 1; i < (height + 1); i++)
//        {
//            for (int j = 1; j < (width + 1); j++)
//            {
//
//                if (cells[(width + 2) * i + j].what != 9)
//                {
//                    cells[(width + 2) * i + j].what = (cells[(width + 2) * (i - 1) + j - 1].what == 9 ? 1 : 0) + (cells[(width + 2) * (i - 1) + j].what == 9 ? 1 : 0)
//                            + (cells[(width + 2) * (i - 1) + j + 1].what == 9 ? 1 : 0) + (cells[(width + 2) * i + j - 1].what == 9 ? 1 : 0) + (cells[(width + 2) * i + j + 1].what == 9 ? 1 : 0)
//                            + (cells[(width + 2) * (i + 1) + j - 1].what == 9 ? 1 : 0) + (cells[(width + 2) * (i + 1) + j].what == 9 ? 1 : 0)
//                            + (cells[(width + 2) * (i + 1) + j + 1].what == 9 ? 1 : 0);
//                }
//
//            }
//        }
//        ZiniNum iZini = ToolKit.calcZini(width, height, mine, rmvBoard);
//        startLength += (mine * 2);
//        // preflags
//        if (pf > 0)
//        {
//            int num_preflags1 = byteStream[startLength] & 0xFF;
//            int num_preflags2 = byteStream[startLength + 1] & 0xFF;
//            int num_preflags = num_preflags1 * 256 + num_preflags2;
//            for (int i = 0; i < num_preflags; i++)
//            {
//                // TODO
//                /*
//                 * c=_fgetc(RMV);d=_fgetc(RMV);
//                 * 
//                 * video[cur].event=4; video[cur].x=square_size/2+c*square_size; video[cur].y=square_size/2+d*square_size; video[cur++].time=0;
//                 * 
//                 * video[cur].event=5; video[cur].x=square_size/2+c*square_size; video[cur].y=square_size/2+d*square_size; video[cur++].time=0;
//                 */
//            }
//        }
//        startLength += 2;
//        // properties
//        int qm = byteStream[startLength] & 0xFF;
//        int nf = byteStream[startLength + 1] & 0xFF;
//        int mmode = byteStream[startLength + 2] & 0xFF;
//        int llevel = byteStream[startLength + 3] & 0xFF;
//        int i = pp > 4 ? pp : 4;
//        startLength += pp;
//        // 下面是 video信息
//        List<RmvVideo> rmvVideoList = new ArrayList<RmvVideo>();
//        int cur = 0;
//        int c;
//        boolean flag = true;
//        boolean isFirst = true;
//        while (flag)
//        {
//            RmvVideo rmvVideo = new RmvVideo();
//            rmvVideo.setCur(cur);
//            ++i;
//            c = byteStream[startLength] & 0xFF;
//            rmvVideo.setEvent(c);
//            rmvVideo.setEventName(getEventName(c));
//            // timestamp event
//            if (c == 0)
//            {
//                startLength += 2;
//                i += 4;
//            }
//            // mouse event
//            else if (c <= 7)
//            {
//                i += 8;
//                startLength++;
//                int time1 = byteStream[startLength] & 0xFF;
//                int time2 = byteStream[startLength + 1] & 0xFF;
//                int time3 = byteStream[startLength + 2] & 0xFF;
//                int time = time1 * 65536 + time2 * 256 + time3;
//                // 中间有1位未知
//                int x1 = byteStream[startLength + 4] & 0xFF;
//                int x2 = byteStream[startLength + 5] & 0xFF;
//                int x = x1 * 256 + x2 - 12;
//                int y1 = byteStream[startLength + 6] & 0xFF;
//                int y2 = byteStream[startLength + 7] & 0xFF;
//                int y = y1 * 256 + y2 - 56;
//                rmvVideo.setTime(time);
//                rmvVideo.setX(x);
//                rmvVideo.setY(y);
//
//                cur++;
//                startLength += 8;
//                if (isFirst)
//                {
//                    isFirst = false;
//                    RmvVideo rmvVideo2 = new RmvVideo();
//
//                    rmvVideo2.event = c;
//                    rmvVideo2.setEventName(getEventName(c));
//                    rmvVideo2.cur = cur;
//                    rmvVideo.event = 2;
//                    rmvVideo.setEventName(getEventName(2));
//                    rmvVideo2.x = rmvVideo.getX();
//                    rmvVideo2.y = rmvVideo.getY();
//                    rmvVideoList.add(rmvVideo2);
//                    rmvVideoList.add(rmvVideo);
//                    cur++;
//                }
//                else
//                {
//                    rmvVideoList.add(rmvVideo);
//                }
//
//                // video[cur].time=getint3(RMV);
//                // _fgetc(RMV);
//                // video[cur].x=getint2(RMV)-12;
//                // video[cur++].y=getint2(RMV)-56;
//                // error("Invalid event");
//            }
//            else if (c == 8)
//            {
//
//                // board event
//            }
//            else if (c <= 14 || (c >= 18 && c <= 27))
//            {
//                i += 2;
//                startLength++;
//                int x = byteStream[startLength] & 0xFF + 1;
//                int y = byteStream[startLength + 1] & 0xFF + 1;
//                startLength += 2;
//                rmvVideo.setX(x);
//                rmvVideo.setY(y);
//
//                rmvVideoList.add(rmvVideo);
//                cur++;
//            }
//            else if (c <= 17)
//            {
//                flag = false;
//                break;
//            }
//        }
//        VideoInfo timex = analyzingRawList(rmvVideoList, height, width, cells);
//        String mvfType = checkBean.videoType;
//        String userID = playInfo;
//        String date = videots;
//        String level = rsSplit1;
//        String mode = (mmode == 0 ? "经典" : "UPK");
//        String time = String.format("%.3f", new Object[] { Double.valueOf(timex.getTime() + 1) });
//        String bbbv = rsSplit5;
//        String bbbvs = String.format("%.3f", new Object[] { Double.valueOf(bbbv) / (Double.valueOf(timex.getTime())) });
//        String distance = String.format("%.3f", new Object[] { Double.valueOf(timex.getDistance()) });
//        String allClicks = String.format("%d", new Object[] { Integer.valueOf(timex.lclicks) + Integer.valueOf(timex.dclicks) + Integer.valueOf(timex.rclicks) });
//        String clicks = String.format("%.3f", new Object[] { Double.valueOf(allClicks) / Double.valueOf(timex.getTime()) });
//        String zini = String.valueOf(iZini.zini);
//        String islands = String.valueOf(iZini.islands);
//        String rqp = String.format("%.2f", new Object[] { Double.valueOf(timex.getTime()) * ((Double.valueOf(timex.getTime()) + 1.0D)) / Double.valueOf(bbbv) });
//
//        String ioe = String.format("%.3f", new Object[] { Double.valueOf(Double.valueOf(bbbv) * 1.0D / Double.valueOf(allClicks)) });
//        String completion = "100%";
//        String num0 = String.valueOf(iZini.num0);
//        String num1 = String.valueOf(iZini.num1);
//        String num2 = String.valueOf(iZini.num2);
//        String num3 = String.valueOf(iZini.num3);
//        String num4 = String.valueOf(iZini.num4);
//        String num5 = String.valueOf(iZini.num5);
//        String num6 = String.valueOf(iZini.num6);
//        String num7 = String.valueOf(iZini.num7);
//        String num8 = String.valueOf(iZini.num8);
//        String numAll = String.valueOf(iZini.num1 + 2 * iZini.num2 + 3 * iZini.num3 + 4 * iZini.num4 + 5 * iZini.num5 + 6 * iZini.num6 + 7 * iZini.num7 + 8 * iZini.num8);
//
//        String disSpeed = String.format("%.3f", new Object[] { Double.valueOf(timex.getDistance()) / (Double.valueOf(timex.getTime())) });
//        String openings = String.valueOf(iZini.openings);
//
//        String disBv = String.format("%.3f", new Object[] { Double.valueOf(timex.getDistance()) / (Double.valueOf(rsSplit5)) });
//        String disNum = String.format(
//                "%.3f",
//                new Object[] { Double.valueOf(Double.valueOf(timex.getDistance())
//                        / ((iZini.num1 + 2 * iZini.num2 + 3 * iZini.num3 + 4 * iZini.num4 + 5 * iZini.num5 + 6 * iZini.num6 + 7 * iZini.num7 + 8 * iZini.num8) * 1.0D)) });
//        String hzoe = String.format("%.3f", new Object[] { Double.valueOf(iZini.zini * 1.0D / Double.valueOf(allClicks)) });
//        String numSpeed = String.format(
//                "%.3f",
//                new Object[] { Double.valueOf(((iZini.num1 + 2 * iZini.num2 + 3 * iZini.num3 + 4 * iZini.num4 + 5 * iZini.num5 + 6 * iZini.num6 + 7 * iZini.num7 + 8 * iZini.num8) * 1.0D))
//                        / (Double.valueOf(timex.getTime())) });
//        // ZINIS=ZINI/time
//        String zinis = String.format("%.3f", new Object[] { Double.valueOf(((iZini.zini) * 1.0D)) / (Double.valueOf(timex.getTime())) });
//        String occam = String.format("%.3f",
//                new Object[] { Double.valueOf(((Double.valueOf(rsSplit5) / (Double.valueOf(timex.getTime()))) * Double.valueOf((Double.valueOf(rsSplit5) * 1.0D / Double.valueOf(allClicks))))) });
//        String lclicks = timex.lclicks;
//        String dclicks = timex.dclicks;
//        String rclicks = timex.rclicks;
//        String qg = String.format("%.3f", new Object[] { Double.valueOf((Math.pow((Double.valueOf(timex.getTime())), 1.7D)) / Double.valueOf(bbbv)) });
//        String flags = String.format("%d", new Object[] { Integer.valueOf(timex.flags) });
//        String markFlag = (qm == 0 ? "UNMARK" : "MARK");
//        String hold = String.format("%d", new Object[] { Integer.valueOf(timex.hold) });
//
//        return new MVFInfo(name, mvfType, userID, date, level, style, mode, time, bbbv, bbbvs, distance, clicks, zini, rqp, ioe, completion, num0, num1, num2, num3, num4, num5, num6, num7, num8,
//                numAll, disSpeed, allClicks, disBv, disNum, hzoe, numSpeed, zinis, occam, openings, lclicks, dclicks, rclicks, qg, flags, markFlag, hold, islands);
//    }

    /**
     * 根据rmvList 分析
     * 
     * @param rmvVideoList rmvVideoList
     * @param height height
     * @param width width
     * @param cells cells
     * @return VideoInfo VideoInfo
     */
//    private VideoInfo analyzingRawList(List<RmvVideo> rmvVideoList, int height, int width, Cells[] cells)
//    {
//        VideoInfo info = new VideoInfo();
//        // distance
//        double path = 0.0D;
//        //
//        double time = 0.0D;
//        /*
//         * const char* event_names[]={"","mv","lc","lr","rc","rr","mc","mr","","pressed" ,"pressedqm","closed", "questionmark","flag","blast","boom","won","nonstandard"
//         * ,"number0","number1","number2","number3", "number4","number5","number6","number7","number8","blast"};
//         */
//        String mouseTypeNomv = "";
//        int tempR = 0;
//        int lstatus = 0;
//        int rstatus = 0;
//        int l = 0;
//        int d = 0;
//        int r = 0;
//        // 计算1.5click
//        int holds = 0;
//        int ax = 0;
//        int ay = 0;
//        Cells[] tempCells = new Cells[(height) * (width)];
//        for (int i = 0; i < tempCells.length; i++)
//        {
//            tempCells[i] = new Cells(0);
//        }
//        for (int i = 0; i < rmvVideoList.size(); i++)
//        {
//            RmvVideo rmvVideo = rmvVideoList.get(i);
//            int event = rmvVideo.event;
//            if (event >= 1 && event <= 7)
//            {
//                time = rmvVideo.time;
//                int nx = rmvVideo.x;
//                int ny = rmvVideo.y;
//                boolean flag = true;
//                if ("rr".equals(mouseTypeNomv))
//                {
//                    flag = false;
//                }
//                int olstatus = 0;
//                int orstatus = 0;
//                int lact = 0;
//                int ract = 0;
//                String mouseType = "";
//                switch (event) 
//                {
//                // mv
//                    case 1:
//                        mouseType = "mv";
//                        lact = 0;
//                        ract = 0;
//                        break;
//                    // lc
//                    case 2:
//                        mouseType = "lc";
//                        lact = 1;
//                        ract = 0;
//                        break;
//                    // lr
//                    case 3:
//                        mouseType = "lr";
//                        lact = -1;
//                        ract = 0;
//                        break;
//                    // rc
//                    case 4:
//                        mouseType = "rc";
//                        lact = 0;
//                        ract = 1;
//                        break;
//                    // rr
//                    case 5:
//                        mouseType = "rr";
//                        lact = 0;
//                        ract = -1;
//                        break;
//                    // rr
//                    case 6:
//                        mouseType = "mc";
//                        lact = 1;
//                        ract = -1;
//                        break;
//                    // rr
//                    case 7:
//                        mouseType = "mr";
//                        lact = -1;
//                        ract = -1;
//                        break;
//                }
//                if (!"mv".equals(mouseType))
//                {
//                    mouseTypeNomv = mouseType;
//                }
//                lstatus += lact;
//                rstatus += ract;
//                olstatus = lstatus - lact;
//                orstatus = rstatus - ract;
//                if (lact == -1 && orstatus == 0 && flag)
//                {
//                    l++;
//                    // l++的时候
//                    int qx = (nx) / 16 + 1;
//                    int qy = (ny) / 16 + 1;
//                    if (qx <= width && qy <= height)
//                    {
//                        int xx = tempCells[(qy - 1) * width + qx - 1].status;
//                        if (xx == 0)
//                        {
//                            ToolKit.digg(qx, qy, tempCells, cells);
//                        }
//                    }
//                }
//
//                if ((lact == -1 ? 1 : 0 + ract == -1 ? 1 : 0) * (olstatus == 1 ? 1 : 0) * (orstatus == 1 ? 1 : 0) > 0)
//                {
//                    d++;
//                    if (tempR == 1)
//                    {
//                        r--;
//                        tempR = 0;
//                    }
//                    int qx = (nx) / 16 + 1;
//                    int qy = (ny) / 16 + 1;
//
//                    if (qx <= width && qy <= height)
//                    {
//                        int thiswhat = cells[(qy) * (width + 2) + (qx)].what;
//                        // 计算点击位置周围一圈雷数
//                        if (thiswhat != 0)
//                        {
//                            int arroundFlag = 0;
//                            // 存在左上格 如果不在第一行或第一列
//                            if (!((qx == 1) || (qy == 1)))
//                            {
//                                arroundFlag += (("F".equals(tempCells[(qy - 2) * width + qx - 2].sta)) ? 1 : 0);
//                            }
//                            // 存在上格 如果不在第一行
//                            if (qy != 1)
//                            {
//                                arroundFlag += (("F".equals(tempCells[(qy - 2) * width + qx - 1].sta)) ? 1 : 0);
//                            }
//                            // 存在右上格 如果不在第一行或最后一列
//                            if (!((qx == width) || (qy == 1)))
//                            {
//                                arroundFlag += (("F".equals(tempCells[(qy - 2) * width + qx].sta)) ? 1 : 0);
//                            }
//                            // 存在左格 如果不在第一列
//                            if (qx != 1)
//                            {
//                                arroundFlag += (("F".equals(tempCells[(qy - 1) * width + qx - 2].sta)) ? 1 : 0);
//                            }
//                            // 存在右格 如果不在最后一列
//                            if (qx != width)
//                            {
//                                arroundFlag += (("F".equals(tempCells[(qy - 1) * width + qx].sta)) ? 1 : 0);
//                            }
//                            // 存在左下格 如果不在最后一行或最后一列
//
//                            if (!((qx == 1) || (qy == height)))
//                            {
//                                arroundFlag += (("F".equals(tempCells[(qy) * width + qx - 2].sta)) ? 1 : 0);
//                            }
//                            // 存在下格 如果不在最后一行
//                            if (qy != height)
//                            {
//                                arroundFlag += (("F".equals(tempCells[(qy) * width + qx - 1].sta)) ? 1 : 0);
//                            }
//                            // 存在右下格 如果不在最后一行或最后一列
//                            if (!((qx == width) || (qy == height)))
//                            {
//                                arroundFlag += (("F".equals(tempCells[(qy) * width + qx].sta)) ? 1 : 0);
//                            }
//                            /*
//                             * System.out.println(qx + " " + qy + " " + arroundFlag + " " + thiswhat);
//                             */
//
//                            // 计算这个数字是否等于周围一圈雷数
//                            if (arroundFlag == thiswhat)
//                            {
//                                ToolKit.digg(qx - 1, qy - 1, tempCells, cells);
//                                ToolKit.digg(qx - 1, qy, tempCells, cells);
//                                ToolKit.digg(qx - 1, qy + 1, tempCells, cells);
//                                ToolKit.digg(qx, qy - 1, tempCells, cells);
//                                ToolKit.digg(qx, qy + 1, tempCells, cells);
//                                ToolKit.digg(qx + 1, qy - 1, tempCells, cells);
//                                ToolKit.digg(qx + 1, qy, tempCells, cells);
//                                ToolKit.digg(qx + 1, qy + 1, tempCells, cells);
//                            }
//                        }
//                    }
//                }
//                if (ract == 1)
//                {
//                    if (olstatus == 0)
//                    {
//                        r++;
//                        int qx = (nx) / 16 + 1;
//                        int qy = (ny) / 16 + 1;
//                        if (qx <= width && qy <= height)
//                        {
//                            int xx = tempCells[(qy - 1) * width + qx - 1].status;
//
//                            if (xx == 0)
//                            {
//                                tempR = 0;
//                                tempCells[(qy - 1) * width + qx - 1].status = 2;
//                                tempCells[(qy - 1) * width + qx - 1].sta = "F";
//                            }
//                            else if (xx == 2)
//                            {
//                                tempR = 0;
//                                tempCells[(qy - 1) * width + qx - 1].status = 0;
//                                tempCells[(qy - 1) * width + qx - 1].sta = " ";
//                            }
//                            else
//                            {
//                                tempR = 1;
//
//                            }
//                        }
//                    }
//                    else
//                    {
//                        tempR = 0;
//                    }
//                }
//                if ((orstatus == 1 ? 1 : 0) * (lact == 1 ? 1 : 0) > 0)
//                {
//                    holds++;
//                }
//
//                if (i > 0)
//                {
//                    path += Math.sqrt((nx - ax) * (nx - ax) + (ny - ay) * (ny - ay));
//                }
//                ax = nx;
//                ay = ny;
//            }
//
//        }
//        int flagCount = 0;
//        for (int i = 0; i < tempCells.length; i++)
//        {
//            if ("F".equals(tempCells[i].sta))
//            {
//                flagCount++;
//            }
//        }
//        info.setFlags(String.valueOf(flagCount));
//        info.setHold(String.valueOf(holds));
//        info.setLclicks(String.valueOf(l));
//        info.setDclicks(String.valueOf(d));
//        info.setRclicks(String.valueOf(r));
//        info.setDistance(String.valueOf(path));
//        info.setTime(String.valueOf(time / 1000));
//        return info;
//    }
//    public static ZiniNum calcingbbbvs(int width, int height, int mines, Cell[] board)
//    {
//        int size = width * height;
//        int closed_cells = size;
//        int openings = 0;
//        int bbbv = 0;
//        int num0 = 0;
//        int num1 = 0;
//        int num2 = 0;
//        int num3 = 0;
//        int num4 = 0;
//        int num5 = 0;
//        int num6 = 0;
//        int num7 = 0;
//        int num8 = 0;
//        int islands = 0;
//        for (int r = 0; r < height; r++)
//        {
//            for (int c = 0; c < width; c++)
//            {
//                int index = c * height + r;
//                board[index].rb = (r != 0 ? r - 1 : r);
//                board[index].re = (r == height - 1 ? r : r + 1);
//                board[index].cb = (c != 0 ? c - 1 : c);
//                board[index].ce = (c == width - 1 ? c : c + 1);
//            }
//
//        }
//
//        for (int i = 0; i < size; i++)
//        {
//            board[i].premium = (-(board[i].number = getnumber(board, height, i)) - 2);
//        }
//        for (int i = 0; i < size; i++)
//        {
//            if ((board[i].number == 0) && (board[i].opening == 0))
//            {
//                openings++;
//                processopening(board, height, openings, i);
//            }
//        }
//        for (int i = 0; i < size; i++)
//        {
//            if (board[i].mine == 1)
//            {
//                board[i].openingAr = 2;
//            }
//            if ((board[i].opening > 0) && (board[i].mine == 0))
//            {
//                board[i].openingAr = 1;
//                for (int rr = board[i].rb; rr <= board[i].re; rr++)
//                {
//                    for (int cc = board[i].cb; cc <= board[i].ce; cc++)
//                    {
//                        if (board[(cc * height + rr)].openingAr != 2)
//                        {
//                            board[(cc * height + rr)].openingAr = 1;
//                        }
//                    }
//                }
//            }
//        }
//
//        for (int i = 0; i < size; i++)
//        {
//            if (board[i].openingAr == 0)
//            {
//                board[i].openingAr = 3;
//                islands(board, height, size, i);
//                islands++;
//            }
//        }
//
//        bbbv = openings;
//        for (int i = 0; i < size; i++)
//        {
//            if ((board[i].opening == 0) && (board[i].mine == 0))
//            {
//                bbbv++;
//            }
//            board[i].premium += getadj3bv(board, height, i);
//        }
//        System.out.println(bbbv);
//        return null;
//    }
//
//    private static int getnumber(Cell[] board, int height, int index)
//    {
//        int res = 0;
//        for (int rr = board[index].rb; rr <= board[index].re; rr++)
//        {
//            for (int cc = board[index].cb; cc <= board[index].ce; cc++)
//            {
//                res += board[(cc * height + rr)].mine;
//            }
//        }
//        return res;
//    }
//
//    private static void setopeningborder(Cell[] board, int op_id, int index)
//    {
//        if (board[index].opening != 0)
//        {
//            board[index].opening = op_id;
//        }
//        else if (board[index].opening != op_id)
//        {
//            board[index].opening2 = op_id;
//        }
//    }
//
//    private static void processopening(Cell[] board, int height, int op_id, int index)
//    {
//        board[index].opening = op_id;
//        for (int rr = board[index].rb; rr <= board[index].re; rr++)
//        {
//            for (int cc = board[index].cb; cc <= board[index].ce; cc++)
//            {
//                int i = cc * height + rr;
//                if (board[i].number != 0)
//                {
//                    setopeningborder(board, op_id, i);
//                }
//                else if (board[i].opening == 0)
//                {
//                    processopening(board, height, op_id, i);
//                }
//            }
//                
//        }
//    }
//
//    private static int getadj3bv(Cell[] board, int height, int index)
//    {
//        int res = 0;
//        if (board[index].number == 0)
//        {
//            return 1;
//        }
//        for (int rr = board[index].rb; rr <= board[index].re; rr++)
//        {
//            for (int cc = board[index].cb; cc <= board[index].ce; cc++)
//            {
//                int i = cc * height + rr;
//                res += ((board[i].mine == 0) && (board[i].opening == 0) ? 1 : 0);
//            }
//        }
//        if (board[index].opening != 0)
//        {
//            res++;
//        }
//        if (board[index].opening2 != 0)
//        {
//            res++;
//        }
//        return res;
//    }
//
//    private static void islands(Cell[] board, int height, int size, int index)
//    {
//        for (int rr = board[index].rb; rr <= board[index].re; rr++)
//        {
//            for (int cc = board[index].cb; cc <= board[index].ce; cc++)
//            {
//                if (board[(cc * height + rr)].openingAr == 0)
//                {
//                    board[(cc * height + rr)].openingAr = 3;
//                    islands(board, height, size, (cc * height + rr));
//
//                }
//            }
//        }
//
//    }
//
//    private String getEventName(int event)
//    {
//        String[] eventLst = new String[] { "", "mv", "lc", "lr", "rc", "rr", "mc", "mr", "", "pressed", "pressedqm", "closed", "questionmark", "flag", "blast", "boom", "won", "nonstandard",
//                "number0", "number1", "number2", "number3", "number4", "number5", "number6", "number7", "number8", "blast" };
//        return eventLst[event];
//    }
}
