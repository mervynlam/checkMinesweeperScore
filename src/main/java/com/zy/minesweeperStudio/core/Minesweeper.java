package com.zy.minesweeperStudio.core;

import com.zy.minesweeperStudio.bean.VideoDisplayBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

public class Minesweeper 
{ 
	   /**
     * 
     * @param file
     *            file
     * @return VideoDisplayBean VideoDisplayBean
     */
    public static VideoDisplayBean parseVideo(File file)
    {
        String name = file.getName();
        VideoDisplayBean bean = new VideoDisplayBean();
        bean.setName(name);
        String videoType = "";
        if (file.getName().toLowerCase().endsWith(".mvf"))
        {
            videoType = "Mvf";
        }
        else if (file.getName().toLowerCase().endsWith(".avf"))
        {
            videoType = "Avf";
        }
        else
        {
            videoType = "Rmv";
        }
        // 各种录像信息在这里 给转化成RAW格式
        if (file.exists())
        {
            byte[] byteStream = readFile(file);
            Class<?> classMethod = null;
            try
            {
                String classNameStr = "com.zy.minesweeperStudio.util." + videoType + "Util";
                classMethod = Class.forName(classNameStr);
                Method method = classMethod.getMethod("analyzeVideo", byte[].class, VideoDisplayBean.class);
                method.invoke(classMethod.newInstance(), byteStream, bean);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            fillBean(bean, "FILENOFOUND");
        }
        // 根据RAW格式计算信息
        return bean;
    }
    public static byte[] readFile(File file)
    {
        byte[] ret = (byte[]) null;
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
            long len = file.length();
            ret = new byte[(int) len];
            fis.read(ret);
        }
        catch (FileNotFoundException e)
        {
            ret = (byte[]) null;

            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException localIOException1)
                {
                }
            }
        }
        catch (IOException e)
        {
            ret = (byte[]) null;

            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException localIOException2)
                {
                }
            }
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (IOException localIOException3)
                {
                }
            }
        }
        return ret;
    }
    public static void fillBean(VideoDisplayBean bean, String str)
    {
        Class<?> classMethod = null;
        try
        {
            String classNameStr = "com.zy.minesweeperStudio.bean.VideoDisplayBean";
            classMethod = Class.forName(classNameStr);
            Method[] methods = classMethod.getMethods();
            for (Method method : methods)
            {
                String name = method.getName();
                if (!"setName".equals(name) && name.startsWith("set"))
                {
                    Method setMethod = classMethod.getMethod(name, String.class);
                    setMethod.invoke(bean, str);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}