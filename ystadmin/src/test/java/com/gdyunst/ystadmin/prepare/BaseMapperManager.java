package com.gdyunst.ystadmin.prepare;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gdyunst.ystadmin.application.utils.LogUtil;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.IGenEntity;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.IGenMapper;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Column;
import com.gdyunst.ystadmin.framework.service.domainservice.prepare.base.Table;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseMapperManager {

    @Test
    public void batchGenBaseMapper() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, SQLException {

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user = "test";
            String pwd = "123456";
            String url = "jdbc:mysql://192.168.1.115:3306/yst_ystadmin?useUnicode=true&amp;characterEncoding=UTF-8";
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", pwd);
            props.setProperty("remarks", "true"); // 设置可以获取remarks信息
            props.setProperty("useInformationSchema", "true");// 设置可以获取tables
                                                              // remarks信息
            conn = DriverManager.getConnection(url, props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 1、获取数据库所有表
        StringBuffer sbTables = new StringBuffer();
        List<Table> tables = new ArrayList<Table>();
        sbTables.append("-------------- 数据库中有下列的表 ----------<br/>");
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rs = dbMetaData.getTables(null, null, null, new String[] { "TABLE" });
            while (rs.next()) {// ///TABLE_TYPE/REMARKS
                Table table = new Table();
                table.setName(rs.getString("TABLE_NAME"));
                table.setRemark(rs.getString("REMARKS"));
                sbTables.append("表名：" + rs.getString("TABLE_NAME") + "<br/>");
                sbTables.append("表类型：" + rs.getString("TABLE_TYPE") + "<br/>");
                sbTables.append("表所属数据库：" + rs.getString("TABLE_CAT") + "<br/>");
                sbTables.append("表所属用户名：" + rs.getString("TABLE_SCHEM") + "<br/>");
                sbTables.append("表备注：" + rs.getString("REMARKS") + "<br/>");
                sbTables.append("------------------------------<br/>");
                tables.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 2、遍历数据库表，获取各表的字段等信息
        for (int i=0;i<tables.size();i++) {
            String tableName = tables.get(i).getName();  
                ResultSet rs = conn.getMetaData().getColumns(null, "",tableName.toUpperCase(), "%");  
                List<Column> columns = new ArrayList<>();
                while(rs.next()){  
                    //System.out.println("字段名："+rs.getString("COLUMN_NAME")+"--字段注释："+rs.getString("REMARKS")+"--字段数据类型："+rs.getString("TYPE_NAME"));  
                    String colName = rs.getString("COLUMN_NAME");  
                    String remarks = rs.getString("REMARKS");  
                    if(remarks == null || remarks.equals("")){  
                        remarks = colName;  
                    }  
                    
                    String dbType = rs.getString("TYPE_NAME");  
                    LogUtil.DebugLog(this, tableName+"------------"+ colName+"----------->"+dbType);
                    String javaType = Column.sqlType2JavaType(dbType);
                    Column column = new Column();
                    column.setName(colName);  
                    column.setRemark(remarks);
                    column.setType(javaType);
                    columns.add(column);  
                }
                tables.get(i).setColumns(columns);
                genMapper.genMapper(tables.get(i));
                genEntity.genEntity(tables.get(i));
            }  
              
        }
    
    
        
//        for (int i=0;i<tables.size();i++) {
//            List<Column> columns = new ArrayList<>();
//            String sql = "select * from " + tables.get(i).getName();
//            try {
//                PreparedStatement ps = conn.prepareStatement(sql);
//                ResultSet rs = ps.executeQuery();
//                ResultSetMetaData meta = rs.getMetaData();
//                int columeCount = meta.getColumnCount();
//                LogUtil.DebugLog(this, "表名：----------->"+tables.get(i).getName());
//                
//                
//                ResultSet rsNew = conn.getMetaData().getColumns(null, "",tables.get(i).getName().toUpperCase(), "%");  
//                
//                
//                for (int j = 1; j < columeCount + 1; j++) {
//                    Column column = new Column();
//                    column.setName(meta.getColumnName(j));  
//                    
//                    
//                    column.setRemark(meta.getColumnLabel(j));
//                    
//                    
//                    int columnType = meta.getColumnType(j);
//                    LogUtil.DebugLog(this, column.getName()+"字段类型：----------->"+columnType);
//                    String sqlType = Column.getTypes(columnType);
//                    LogUtil.DebugLog(this, column.getName()+"字段类型2：----------->"+sqlType);
//                    String javaType = Column.sqlType2JavaType(sqlType);
//                    LogUtil.DebugLog(this, column.getName()+"字段类型3：----------->"+javaType);
//                    column.setType(javaType);
//                    LogUtil.DebugLog(this, "字段名：----------->"+column.getName());
//                    columns.add(column);
//                    
//                }
//                tables.get(i).setColumns(columns);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            
//            genMapper.genMapper(tables.get(i));
//            genEntity.genEntity(tables.get(i));
//        }

//    }
    @Autowired
    IGenEntity genEntity;
    @Autowired
    IGenMapper genMapper;
    

   
}
