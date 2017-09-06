package com.gdyunst.ystadmin.framework.service.domainservice.prepare.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class ReplaceFileString {

    /**
     * 功能描述:替换文件内容
     * @param filePath
     * @Author:dengshuai
     * @Date:2017年6月21日 下午3:32:52
     */
    public void replacePackage(String filePath,String oldString,String newString){
        
        ReplaceFileString obj = new ReplaceFileString();
        obj.write(filePath, obj.read(filePath,oldString,newString)); // 读取修改文件
    }
    
    /**
     * 替换文件内容
     * 
     * @param filePath
     * @return
     */
    public String read(String filePath,String oldString,String newString) {
        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();
        
        try {
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(new FileReader(filePath));
            
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {
                System.out.println("oldString is =======>"+oldString);
                // 此处根据实际需要修改某些行的内容
                if(line.contains(oldString)){//如果有entity的包名，替换
                    line = line.replace(oldString, newString);
                    System.out.println("replace=======>"+line);
                    buf.append(line);
                }// 如果不用修改, 则按原来的内容回写
                else {
                    buf.append(line);
                }
                buf.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
        }
        
        return buf.toString();
    }
    
    /**
     * 将内容回写到文件中
     * 
     * @param filePath
     * @param content
     */
    public void write(String filePath, String content) {
        BufferedWriter bw = null;
        
        try {
            // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(new FileWriter(filePath));
            // 将内容写入文件中
            bw.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }
    }


}
