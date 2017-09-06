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

import com.gdyunst.ystadmin.framework.service.domainservice.prepare.IGenMapper;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Column;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.LogUtil;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Mapper;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.PrepareConstant;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Table;

@Service
public class GenMapper implements IGenMapper {

    @Autowired
    ReplaceFileString rfs;

    @Override
    public int genMapper(Table table) {
        if (table == null || table.getColumns() == null)
            return 0;
        List<Column> columns = table.getColumns();
        String dbColumns = "(";
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if(column.getName().equals(PrepareConstant.DBCOLUMN_PARENTID)){
                genTreeMapper(table);
                return 1;
            }

        }
        genBaseMapper(table);
        return 1;
    }
    
    
    @Override
    public int genBaseMapper(Table table) {
        if (table == null || table.getColumns() == null)
            return 0;

        Mapper mapper = table2Mapper(table);
        File mapperFile = new File(PrepareConstant.mapperPath + File.separator + mapper.getMapperName());
        LogUtil.DebugLog(this.getClass(), "mapperFile is "+mapperFile.getPath());
        File tplFile = new File(PrepareConstant.NORMAL_MAPPERTPL_PATH);
        if (!mapperFile.exists()) {
            try {
                mapperFile.createNewFile();
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

            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(mapperFile), "UTF-8");
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

        rfs.replacePackage(mapperFile.getAbsolutePath(), "${dbColumns}", mapper.getDbColumns());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${insertColumns}", mapper.getInsertColumns());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${updateColumns}", mapper.getUpdateColumns());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${listColumns}", mapper.getListColumns());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${orderColumn}", mapper.getOrderColumn());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${domainName}", mapper.getDomainName());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${entityName}", mapper.getEntName());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${package}", mapper.getPackages());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${tableName}", mapper.getTableName());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${batchInsertColumns}", mapper.getBatchInsertColumns());

        return 1;
    }

    @Override
    public int genTreeMapper(Table table) {
        if (table == null || table.getColumns() == null)
            return 0;

        Mapper mapper = table2Mapper(table);
        File mapperFile = new File(PrepareConstant.mapperPath + File.separator + mapper.getMapperName());
        LogUtil.DebugLog(this.getClass(), "mapperFile is "+mapperFile.getPath());
        File tplFile = new File(PrepareConstant.TREE_MAPPERTPL_PATH);
        if (!mapperFile.exists()) {
            try {
                mapperFile.createNewFile();
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

            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(mapperFile), "UTF-8");
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

        rfs.replacePackage(mapperFile.getAbsolutePath(), "${dbColumns}", mapper.getDbColumns());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${insertColumns}", mapper.getInsertColumns());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${updateColumns}", mapper.getUpdateColumns());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${listColumns}", mapper.getListColumns());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${orderColumn}", mapper.getOrderColumn());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${domainName}", mapper.getDomainName());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${entityName}", mapper.getEntName());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${package}", mapper.getPackages());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${tableName}", mapper.getTableName());
        rfs.replacePackage(mapperFile.getAbsolutePath(), "${batchInsertColumns}", mapper.getBatchInsertColumns());
        return 1;
    }

    private Mapper table2Mapper(Table table) {
        String domainName = table.getName().split("_")[1];
        domainName =  domainName.substring(0, 1).toUpperCase() + domainName.substring(1);
        String entName = domainName.substring(0, 1).toUpperCase() + domainName.substring(1) + "Entity";
        Mapper mapper = new Mapper();
        mapper.setMapperName(entName + "Mapper.xml");
        mapper.setDbColumns(genDBColumns(table));
        mapper.setInsertColumns(genInsertColumns(table));
        mapper.setListColumns(genListColumns(table));
        mapper.setOrderColumn(genOrderColumn(table));
        mapper.setUpdateColumns(genUpdateColumns(table));
        mapper.setDomainName(domainName);
        mapper.setEntName(entName);
        mapper.setTableName(table.getName());
        mapper.setBatchInsertColumns(genBatchInsertColumns(table));
        mapper.setPackages(PrepareConstant.packages);
        return mapper;
    }

    @Override
    public String genDBColumns(Table table) {
        List<Column> columns = table.getColumns();
        String dbColumns = "(";
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if (column.getName().equals(PrepareConstant.DBCOLUMN_CREATEDATE)) {
                continue;
            }
            if (i < columns.size() - 1) {
                dbColumns += column.getName() + ",";
            } else {
                dbColumns += column.getName() + ")";
            }

        }

        return dbColumns;
    }

    @Override
    public String genInsertColumns(Table table) {
        List<Column> columns = table.getColumns();
        String insertColumns = "(";
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if (column.getName().equals(PrepareConstant.DBCOLUMN_CREATEDATE)) {
                continue;
            }
            if (i < columns.size() - 1) {
                insertColumns += "#{" + column.getName() + "},";
            } else {
                insertColumns += "#{" + column.getName() + "})";
            }
        }

        return insertColumns;
    }

    @Override
    public String genUpdateColumns(Table table) {
        List<Column> columns = table.getColumns();
        String updateColumns = "";
        String tempLine = "<if test=\"%s!=null and %s!=''\">,%s=#{%s}</if>\n\t\t";
        for (int i = columns.size()-1; i >= 0; i--) {
            Column column = columns.get(i);
            LogUtil.DebugLog(this.getClass(), "tempLine is ------------->"+tempLine);
            tempLine = String.format(tempLine, column.getName(), column.getName(), column.getName(),column.getName());
            LogUtil.DebugLog(this.getClass(), "column name is ------------->"+column.getName());
            LogUtil.DebugLog(this.getClass(), "tempLine is ------------->"+tempLine);
            updateColumns += tempLine;
            tempLine = "<if test=\"%s!=null and %s!=''\">,%s=#{%s}</if>\n\t\t";
        }

        return updateColumns;
    }

    @Override
    public String genListColumns(Table table) {
        List<Column> columns = table.getColumns();
        String listColumns = "";
        String tempLine = "<if test=\"%s!=null and %s!=''\">AND %s=#{%s}</if>\n\t\t";
        for (int i = columns.size()-1; i >= 0; i--) {
            Column column = columns.get(i);
            if (column.getName().equals(PrepareConstant.DBCOLUMN_DELSTATUS)) {
                tempLine = "<if test=\"delStatus!=null and delStatus!=''\">AND delStatus=#{delStatus}</if>\n\t\t";
                listColumns += tempLine;
                tempLine = "<if test=\"delStatus==null\">AND delStatus=0</if>\n\t\t";
                listColumns += tempLine;
            } else {
                tempLine = String.format(tempLine, column.getName(), column.getName(), column.getName(),column.getName());
                listColumns += tempLine;
            }
            tempLine = "<if test=\"%s!=null and %s!=''\">AND %s=#{%s}</if>\n\t\t";
        }

        return listColumns;
    }

    @Override
    public String genOrderColumn(Table table) {
        List<Column> columns = table.getColumns();
        for (Column column : columns) {
            String indexOrder = column.getName();
            if (indexOrder.equals(PrepareConstant.DBCOLUMN_INDEXORDER)) {
                return indexOrder;
            }
        }

        return PrepareConstant.DBCOLUMN_CREATEDATE;
    }


    @Override
    public String genBatchInsertColumns(Table table) {
        List<Column> columns = table.getColumns();
        String insertColumns = "(";
        for (int i = 0; i < columns.size(); i++) {
            Column column = columns.get(i);
            if (column.getName().equals(PrepareConstant.DBCOLUMN_CREATEDATE)) {
                continue;
            }
            if (i < columns.size() - 1) {
                insertColumns += "#{item." + column.getName() + "},";
            } else {
                insertColumns += "#{item." + column.getName() + "},";
            }
        }

        return insertColumns;
    }

    

}
