package cn.com.kxyt.core;

/**
 * @author zj
 * @Description: SQL实体类
 * @date 2020/11/19 9:06
 */
public class Sql<T> {

    /**
     * 接口传过来的SQL id
     */
    private String id;

    /**
     * 接口传过来的SQL SQL语句
     */
    private String sql;

    /**
     * 接口传过来的SQL 描述
     */
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Sql{" +
                "id='" + id + '\'' +
                ", sql='" + sql + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
