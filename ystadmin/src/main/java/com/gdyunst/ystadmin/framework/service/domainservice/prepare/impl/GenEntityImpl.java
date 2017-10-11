package com.gdyunst.ystadmin.framework.service.domainservice.prepare.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdyunst.ystadmin.framework.service.domainservice.prepare.IGenEntity;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Column;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Entity;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.LogUtil;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.PrepareConstant;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Table;

@Service
public class GenEntityImpl implements IGenEntity {

    @Autowired
    ReplaceFileString rfs;
    
    @Override
    public int genEntity(Table table) {
        if (table == null || table.getColumns() == null)
            return 0;

        Entity entity = table2Entity(table);
        File entityFile = new File(PrepareConstant.entityPath + File.separator + entity.getEntFileName());
        LogUtil.DebugLog(this.getClass(), "entityFile is "+entityFile.getPath());
        File tplFile = new File(PrepareConstant.ENTITYTPL_PATH);
        if (!entityFile.exists()) {
            try {
                entityFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return -1;
            }
        }

        BufferedReader br = null;
        String line = null;
        StringBuffer buf = new StringBuffer();
        BufferedWriter bw = null;
        try {
            InputStreamReader read = new InputStreamReader(new FileInputStream(tplFile), "UTF-8");
            // 根据文件路径创建缓冲输入流
            br = new BufferedReader(read);

            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
            while ((line = br.readLine()) != null) {

                buf.append(line).append("\r\n");

            }

            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(entityFile), "UTF-8");
            // 根据文件路径创建缓冲输出流
            bw = new BufferedWriter(write);
            // 将内容写入文件中
            bw.write(buf.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            // 关闭流
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                }
            }
            // 关闭流
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    bw = null;
                }
            }
        }

        rfs.replacePackage(entityFile.getAbsolutePath(), "${package}", entity.getPackages());
        rfs.replacePackage(entityFile.getAbsolutePath(), "${entDesc}", entity.getEntDesc());
        rfs.replacePackage(entityFile.getAbsolutePath(), "${tableName}", table.getName());
        rfs.replacePackage(entityFile.getAbsolutePath(), "${entityName}", entity.getEntName());
        rfs.replacePackage(entityFile.getAbsolutePath(), "${entColumns}", entity.getEntColumns());
        rfs.replacePackage(entityFile.getAbsolutePath(), "${entGetSet}", entity.getEntGetSet());
        rfs.replacePackage(entityFile.getAbsolutePath(), "${toString}", entity.getToString());
        rfs.replacePackage(entityFile.getAbsolutePath(), "${project}", PrepareConstant.PROJECT);

        return 1;
    }

    private Entity table2Entity(Table table){
        Entity entity = new Entity();
        entity.setEntColumns(genEntColumns(table));
        entity.setEntDesc(genEntDesc(table));
        entity.setEntGetSet(genEntGetSet(table));
        entity.setEntName(genEntName(table));
        entity.setEntFileName(genEntFileName(table));
        entity.setPackages(PrepareConstant.packages);
        return entity;
    }

    @Override
    public String genEntColumns(Table table) {
        List<Column> columns = table.getColumns();
        StringBuffer entColumns = new StringBuffer();
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if (column.getName().equals("id")) {
                entColumns.append("@Id\n\t");
                entColumns.append("private String id;   //");
                entColumns.append(column.getRemark()+"\n\t");
                continue;
            }
            if(column.getType().equals("Date")){
                entColumns.append("@JsonFormat(timezone = \"GMT+8\", pattern = \"yyyy-MM-dd HH:mm:ss\")\n\t");
                entColumns.append("@JSONField(format=\"yyyy-MM-dd HH:mm:ss\")"); 
                entColumns.append("@Column(name=\""+column.getName()+"\")\n\t");
                entColumns.append("private Date "+column.getName()+";  //");
                entColumns.append(column.getRemark()+"\n\t");
                continue;
            }
            entColumns.append("@Column(name=\""+column.getName()+"\")\n\t");
            entColumns.append("private "+column.getType()+" "+column.getName()+";  //");
            entColumns.append(column.getRemark()+"\n\t");

        }
        return entColumns.toString();
    }

    @Override
    public String genEntGetSet(Table table) {
        List<Column> columns = table.getColumns();
        StringBuffer entGetSet = new StringBuffer();
        for (int i = 0; i < columns.size(); i++) {
            String name = columns.get(i).getName();
            String columnName = name.substring(0, 1).toUpperCase() + name.substring(1);
            String columnType = columns.get(i).getType();
            
            entGetSet.append("public "+columnType+" get"+columnName+"(){\n");
            if(columnType.equals("Double")){//如果是Double类型经常用于计算设默认值0.0
                entGetSet.append("\t\treturn "+name+"==null?0.0:"+name+";\n\t"); 
            }else{
                entGetSet.append("\t\treturn "+name+";\n\t");  
            }
            entGetSet.append("}\n");
            entGetSet.append("\n\t");
            
            entGetSet.append("public void set"+columnName+"("+columnType+" "+name+"){\n\t");
            if(columnType.equals("Double")){//如果是Double类型经常用于计算设默认值0.0
                entGetSet.append("\tthis."+name+" = "+name+"==null?0.0:"+name+";\n\t"); 
            }else{
                entGetSet.append("\tthis."+name+" = "+name+";\n\t");
            }
            
            entGetSet.append("}\n\n\t");
        }
        return entGetSet.toString();
    }

    @Override
    public String genEntDesc(Table table) {
        // TODO Auto-generated method stub
        return table.getRemark();
    }

    @Override
    public String genEntFileName(Table table) {
        String domainName = table.getName().split("_")[1];
        domainName =  domainName.substring(0, 1).toUpperCase() + domainName.substring(1);
        String entName = domainName.substring(0, 1).toUpperCase() + domainName.substring(1) + "Entity";
        return entName+".java";
    }
    
    @Override
    public String genEntName(Table table) {
        String domainName = table.getName().split("_")[1];
        domainName =  domainName.substring(0, 1).toUpperCase() + domainName.substring(1);
        String entName = domainName.substring(0, 1).toUpperCase() + domainName.substring(1) + "Entity";
        return entName;
    }

    @Override
    public String genEntToString(Table table) {
        // TODO Auto-generated method stub
        return null;
    }
}
