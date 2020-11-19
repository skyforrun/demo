package cn.com.kxyt.controller;

import cn.com.kxyt.core.Sql;
import cn.com.kxyt.util.SQLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author zj
 * @Description:
 * @date 2020/11/1911:03
 */

@Controller
public class StandAddressController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    Sql sql = new Sql();

    String url = "http://192.168.1.245:9020/getGeoJson/s1_3_7/{\"1\":\"12\",\"2\":\"3\",\"3\":\"4\",\"4\":\"1\",\"5\":\"001\"}";

    public void da(){
        System.out.println(SQLUtil.parseSql(url));
        sql.setId(SQLUtil.sqlId);
        logger.info("开始执行SQL，sql的ID为"+sql.getId());
        List<Sql> sqls = SQLUtil.extractSql();
        for (int i = 0; i < sqls.size(); i++) {
            if (sqls.get(i).getId().equals(sql.getId())){
                sql = sqls.get(i);
                System.out.println(sql);
            }
        }
    }

    public static void main(String[] args) {
        StandAddressController addressController = new StandAddressController();
        addressController.da();
    }

}
