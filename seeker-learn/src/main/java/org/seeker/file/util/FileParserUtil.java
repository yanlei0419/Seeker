package org.seeker.file.util;

import org.seeker.file.entity.CutPoint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileParserUtil {
    public final static byte byteLineSepN='\n';
    public final static byte byteLineSepR='\r';
    public final static int dataInsBatchSize=1000;
    public final static String encode="utf-8";



    public static List<List<String>> getLines(File file, CutPoint cp){
        long head=cp.getHead();
        long tail=cp.getTail();
        int size= (int) (head-head);
        RandomAccessFile raf=null;

        try {
            raf = new RandomAccessFile(file, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        try {
            raf.seek(head);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if(null!=raf){
                    raf.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }

        MappedByteBuffer in;
        FileChannel fc=null;
        String line;


        try {
            fc=raf.getChannel();
            in=fc.map(FileChannel.MapMode.READ_ONLY,head,size);
            byte[] bytes = new byte[size];
            byte[] byteLine;


            List<List<String>> all=new ArrayList<List<String>>();
            List<String> list=null;
            int start=0,count;

            for (int i = 0; i < size; i++) {
                bytes[i]=in.get(i);
                if(readyLine(bytes[i])||i==size-1){//换行符
                    count=i-start+1;
                    byteLine = new byte[count];
                    System.arraycopy(bytes,start,byteLine,0,count);
                    start=i+1;
                    line=new String(byteLine,encode);
                    if(null==list || list.size()>=dataInsBatchSize){
                        list =new ArrayList<String>(dataInsBatchSize);
                        all.add(list);
                    }
                    if(null!=line){
                        line=line.trim();
                    }
                    if("".equals(line)){
                        list.add(line);
                    }

                }

            }
            return all;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<List<String>>();
        }
    }


    /**
     * 判断是否为换行符
     * @return
     */
    public static boolean readyLine(byte val){
        switch (val){
            case byteLineSepR:
                return true;
            case byteLineSepN:
                return true;
            default:
                return false;
        }

    }












}
