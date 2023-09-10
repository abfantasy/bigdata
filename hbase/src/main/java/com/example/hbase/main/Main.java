package com.example.hbase.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        createTable("ABCD", "c1", "c2", "c3");
//        queryTableByRow("AA", "r1");
        queryTableByRowAndFamilyAndColumn("AA", "r1", "col_1", "age");
    }

    static Admin admin = null;
    static Connection connection = null;

    static {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "172.17.0.2");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "172.17.0.2:60000");
        // 获取连接对象
        try {
            connection = ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void close(Connection conn, Admin admin) throws IOException {
        if (conn != null) {
            conn.close();
        }
        if (admin != null) {
            admin.close();
        }
    }

    public static void queryTableByRow(String tableName, String row) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get query = new Get(Bytes.toBytes(row));
        Result result = table.get(query);
        List<Cell> cells = Arrays.asList(result.rawCells());
        cells.stream().forEach(cell -> {
            //行的值，不局限于数值，比如可以为r1
            String rowName = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
            //列族,如col
            String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
            //列的限定符,col的限定符，如name、age、address
            String familyQualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            //列的限定符的值
            String familyQualifierValue = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
            System.err.println("rowName" + "=" + "【" + rowName + "】");
            System.err.println("family" + "=" + "【" + family + "】");
            System.err.println("familyQualifier" + "=" + "【" + familyQualifier + "】");
            System.err.println("familyQualifierValue" + "=" + "【" + familyQualifierValue + "】");
            System.err.println();
        });
    }

    public static void queryTableByRowAndFamilyAndColumn(String tableName, String row, String familyName, String familyColumnName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get query = new Get(Bytes.toBytes(row));
        query.addFamily(Bytes.toBytes(familyName));
        query.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(familyColumnName));
        Result result = table.get(query);
        List<Cell> cells = Arrays.asList(result.rawCells());
        cells.stream().forEach(cell -> {
            //行的值，不局限于数值，比如可以为r1
            String rowName = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
            //列族,如col
            String family = Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
            //列的限定符,col的限定符，如name、age、address
            String familyQualifier = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            //列的限定符的值
            String familyQualifierValue = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
            System.err.println("rowName" + "=" + "【" + rowName + "】");
            System.err.println("family" + "=" + "【" + family + "】");
            System.err.println("familyQualifier" + "=" + "【" + familyQualifier + "】");
            System.err.println("familyQualifierValue" + "=" + "【" + familyQualifierValue + "】");
            System.err.println();
        });
    }

    public static void createTable(String tableName, String... columnFamily) throws IOException {
        // 判断是否存在列族信息
        if (columnFamily.length <= 0) {
            System.out.println("请设置列族信息！");
            return;
        }
        //判断表是否存在
        if (tableExist(tableName)) {
            System.out.println("表" + tableName + "已存在");
        } else {
            //创建表描述器
            TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(tableName));
            //创建多个列族
            int i = 1;
            for (String cf : columnFamily) {
                String date = "t" + i++;
                // 创建列族描述器
                ColumnFamilyDescriptor columnFamilyDescriptor = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(date)).build();
                tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
            }
            //根据对表的配置，创建表
            admin.createTable(tableDescriptorBuilder.build());
            System.out.println("表" + tableName + "创建成功！");
        }
    }

    public static boolean tableExist(String tableName) throws IOException {
        boolean tableExists = admin.tableExists(TableName.valueOf(tableName));
        return tableExists;
    }

}
