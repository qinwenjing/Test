package com.test;

import java.io.Serializable;
import java.util.List;

/**
 * 样本处理请求参数
 * create by liuyanjiang on 2018/3/15
 */
public class SampleProcessRequest implements Serializable {

    /**
     * 文件hdfs路径
     */
    private String filePath;
    /**
     * 异常处理
     */
    private String errorValues;
    /**
     * 分隔符
     */
    private String seperator;
    /**
     * 是否有表头
     */
    private String tableHead;
    /**
     * 文件处理结果保存路径
     */
    private String processResultPath;
    /**
     * hive库名
     */
    private String hiveDatabase;

    /**
     * 文件内容编码
     */
    private String encode;

    /**
     * 是否临时
     *
     * @param args
     */

    /**
     * 表名
     */
    private String tableName;

    /**
     * 列模式
     */
    private List sampleColumnSchemas;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getErrorValues() {
        return errorValues;
    }

    public void setErrorValues(String errorValues) {
        this.errorValues = errorValues;
    }

    public String getSeperator() {
        return seperator;
    }

    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }

    public String getTableHead() {
        return tableHead;
    }

    public void setTableHead(String tableHead) {
        this.tableHead = tableHead;
    }

    public String getProcessResultPath() {
        return processResultPath;
    }

    public void setProcessResultPath(String processResultPath) {
        this.processResultPath = processResultPath;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getHiveDatabase() {
        return hiveDatabase;
    }

    public void setHiveDatabase(String hiveDatabase) {
        this.hiveDatabase = hiveDatabase;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public List getSampleColumnSchemas() {
        return sampleColumnSchemas;
    }

    public void setSampleColumnSchemas(List sampleColumnSchemas) {
        this.sampleColumnSchemas = sampleColumnSchemas;
    }
}
