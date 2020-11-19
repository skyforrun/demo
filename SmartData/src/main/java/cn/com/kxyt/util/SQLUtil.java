package cn.com.kxyt.util;

import cn.com.kxyt.core.Sql;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @author zj
 * @Description:
 * @date 2020/11/18 15:15
 */
public class SQLUtil {

    /**
     * 往数据库增加数据SQL集合
     */
    private static List<Sql> allSqlList = new ArrayList();
    /**
     * 往数据库增加数据SQL集合
     */
    private static List<Sql> insertList = new ArrayList();
    /**
     * 往数据库删除数据SQL集合
     */
    private static List<Sql> deleteList = new ArrayList();
    /**
     * 往数据库修改数据SQL集合
     */
    private static List<Sql> updateList = new ArrayList();
    /**
     * 往数据库查询数据SQL集合
     */
    private static List<Sql> queryList = new ArrayList();

    public static String sqlId;
    public static List<String> parameterValue = new ArrayList<>();

    public static List<Sql> extractSql(){
        //读取外部配置SQLjson配置文件
        String sqlJson = JsonFileUtil.readJsonFile("./config/sqlist.json");
        JSONArray sqlLists = JSONObject.parseObject(sqlJson).getJSONArray("lists");
        for (int i = 0; i < sqlLists.size(); i++) {
            //取出每条SQL数组
            JSONObject sqlArray = sqlLists.getJSONObject(i);
            //取出每条sql的相关信息
            sqlId = sqlArray.getString("id");
            String remarks = sqlArray.getString("remarks");
            String sqlInfo = sqlArray.getString("sql");
            //将SQL信息封装到SQL实体类
            Sql sql = new Sql();
            sql.setId(sqlId);
            sql.setSql(sqlInfo);
            sql.setRemarks(remarks);
            allSqlList.add(sql);
            //相对应的集合添加对应的SQL实体
            if (sql.getSql().startsWith("SELECT")|sql.getSql().startsWith("select")){
                queryList.add(sql);
            }else if (sql.getSql().startsWith("DELETE")|sql.getSql().startsWith("delete")){
                deleteList.add(sql);
            }else if (sql.getSql().startsWith("UPDATE")|sql.getSql().startsWith("update")){
                updateList.add(sql);
            }else {
                insertList.add(sql);
            }
        }
        return allSqlList;
    }

    /**
     * 接口传参抽取SQL
     * @param url
     * @return
     */
    public static List<String> parseSql(String url){
        sqlId = url.substring(url.lastIndexOf("_") - 4, url.lastIndexOf("/"));
        String parameter = url.substring(url.lastIndexOf("/")+1);
        int size = JSONObject.parseObject(parameter).size();
        for (int i = 1; i < size+1; i++) {
            String Value = JSONObject.parseObject(parameter).getString(String.valueOf(i));
            parameterValue.add(Value);
        }
        return parameterValue;
    }
}
