package com.hgnu.study.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/30 15:23
 */
public class collection {

    ExpressRunner runner = new ExpressRunner(false,false);
    DefaultContext<String, Object> context = new DefaultContext<String, Object>();

    /**
     * 集合的快捷写法
     * @throws Exception
     */
    @Test
    public void testSet() throws Exception {
        String express = "abc = NewMap(1:1,2:2); return abc.get(1) + abc.get(2);";
        Object r = runner.execute(express, context, null, false, false);
        System.out.println(r);
        express = "abc = NewList(1,2,3); return abc.get(1)+abc.get(2)";
        r = runner.execute(express, context, null, false, false);
        System.out.println(r);
        express = "abc = [1,2,3]; return abc[1]+abc[2];";
        r = runner.execute(express, context, null, false, false);
        System.out.println(r);
    }

    /**
     * map的遍历
     */
    @Test
    public void foreache(){
        //遍历map
//        map = new HashMap();
//        map.put("a", "a_value");
//        map.put("b", "b_value");
//        keySet = map.keySet();
//        objArr = keySet.toArray();
//        for (i=0;i<objArr.length;i++) {
//            key = objArr[i];
//            System.out.println(map.get(key));
//        }
    }
}
