package org.vegetto.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.seeker.common.util.WriteFileUtil;

public class ReadyFileUtils {
	
	/**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String path,String fileName) {
        File file = new File(path+fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String lineVal = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((lineVal = reader.readLine()) != null) {
            	HandlyLineData.handly(lineVal,line,fileName);
                line++;
            }
            reader.close();
            Set<String> set=HandlyLineData.set;
            Iterator<String> it = set.iterator();
           while(it.hasNext()){
            	String content="-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=end [" +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"] =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n\t";
				String abc=it.next()+HandlyLineData.sgin;
            	WriteFileUtil.writeFile(HandlyLineData.filePath, "[" + fileName + "]=-="+abc, content,5);
			}
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

}
