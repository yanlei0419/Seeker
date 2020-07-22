package org.seeker.file.util;

import org.seeker.file.entity.CutPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
/**
 * 文件分割工具类
 */
public class FileCutUtil {
    /**
     * 文件切割默认大小
     */
    private static long piece = 50L * 1024L * 1024L;
    private static String randomAccessFileEncode = "ISO-8859-1";
    private static String lineSeparator=System.getProperty("line.separator");




    /**
     * 文件切片大小单位[字节] 文件切割
     * @param file
     * @return
     */
    public static List<CutPoint> handleCutFile(File file){
        RandomAccessFile raf=null;
        List<CutPoint> list=new ArrayList<CutPoint>();
        try {
            raf=new RandomAccessFile(file,"r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return list;
        }


        long fileLen=file.length();
        long tempPoint,tailPoint;
        CutPoint cp=null;
        String s=null;
        byte[] bytes=null;
        int length;
        try {
            raf.seek(0);
            long headPoint=raf.getFilePointer();

            while(true){
                cp=new CutPoint();
                list.add(cp);
                cp.setFileName(file.getName());
                cp.setHead(headPoint);
                raf.seek(headPoint+piece);
                tempPoint =raf.getFilePointer();
                if(tempPoint>=fileLen){//文件结尾
                    tailPoint=fileLen;
                    cp.setTail(tailPoint);

                    break;
                }
                s=raf.readLine();//当前位置获取到结尾
                bytes = s.getBytes(randomAccessFileEncode);
                length=bytes.length;
                tailPoint=tempPoint+length+lineSeparator.length();
                cp.setTail(tailPoint);

                if(tailPoint>=fileLen){//防止文件大小为101,分片后为100,回车换行符站位问题
                    break;
                }
                headPoint=tailPoint;
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("文件: "+file.getAbsolutePath()+" ,拆分异常:"+e);
            return new ArrayList<CutPoint>();
        }finally {
            try {
                if(null!=raf){
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
