package com.gdyunst.ystadmin.prepare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 功能描述: 
 * @Author:zhujiwei
 * @Date:2017年5月18日 上午10:21:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ReplacePackage {

    public String entityOldString = "com.gdyunst.kckpcomm.entity.admin";
    public String entityNewString = "com.gdyunst.jqzy.framework.domain.entity.admin";
    public String domainNewString = "com.gdyunst.jqzy.framework.service.admin.domain";
    public String domainOldString = "com.gdyunst.kckpadmin.framework.domain";
    public String dtoOldString = "";
   
    
    public String sentityOldString = "com.gdyunst.kckpcomm.entity.service";
    public String sentityNewString = "com.gdyunst.jqzy.framework.domain.entity.service";
    public String sdomainNewString = "com.gdyunst.jqzy.framework.service.service.domain";
    public String sdomainOldString = "com.gdyunst.kckpservice.framework.domain";
    
    @Test
    public void test(){
        System.out.println("hello world");
        File file = new File("E:/Workbench/jqzy/src/main/resources/basemapper");
        List<File> files = listAllFile(file);
        for (int i = 0; i < files.size(); i++) {
            String filePath = files.get(i).getAbsolutePath();
            replacePackage(filePath,sentityOldString,sentityNewString);
            replacePackage(filePath,sdomainOldString,sdomainNewString);
            replacePackage(filePath,entityOldString,entityNewString);
            replacePackage(filePath,domainOldString,domainNewString);
        }
        
   
    }

    public List<File> listAllFile(File dir){
        System.out.println("===========>"+dir.getName());
        List<File> fileList = new ArrayList<>();
        File[] array = dir.listFiles();
        for(int i=0;i<array.length;i++){   
            if(array[i].isFile()){    
                fileList.add(array[i]);
            }
        }   
        return fileList;
    }
    /**
     * 功能描述:替换文件内容
     * @param filePath
     * @Author:dengshuai
     * @Date:2017年6月21日 下午3:32:52
     */
    public void replacePackage(String filePath,String oldString,String newString){
        
        ReplacePackage obj = new ReplacePackage();
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
