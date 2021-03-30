package com.hgnu.study.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import org.junit.Test;

/**
 * @Author zj
 * @Description macro 宏定义
 * @Date 2021/3/30 15:11
 */
public class Macro {

    ExpressRunner runner = new ExpressRunner();

    @Test
    public void test() throws Exception {
        runner.addMacro("计算平均成绩", "(语文+数学+英语)/3.0");
        runner.addMacro("是否优秀", "计算平均成绩>90");
        IExpressContext<String, Object> context =new DefaultContext<String, Object>();
        context.put("语文", 88);
        context.put("数学", 99);
        context.put("英语", 95);
        Object result = runner.execute("是否优秀", context, null, false, false);
        System.out.println(result);
    }
}
