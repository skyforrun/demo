package com.hgnu.study.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.Operator;
import org.junit.Test;

/**
 * @Author zj
 * @Description 扩展操作符：Operator
 * @Date 2021/3/30 14:56
 */

public class operate {

    ExpressRunner runner = new ExpressRunner();

    DefaultContext<String, Object> context = new DefaultContext<>();

    /**
     * 替换if then else 等关键字
     */
    @Test
    public void aliais() throws Exception {
        runner.addOperatorWithAlias("如果", "if",null);
        runner.addOperatorWithAlias("则", "then",null);
        runner.addOperatorWithAlias("否则", "else",null);

        context.put("语文",100);
        context.put("数学",20);
        context.put("英语",30);

        String exp = "如果  (语文+数学+英语>270) 则 {return 1;} 否则 {return 0;}";

        Object execute = runner.execute(exp, context, null, false, false, null);
        System.out.println(execute);
    }

    /**
     *
     */
    @Test
    public void useOperate() throws Exception {
        //(1)addOperator
//        runner.addOperator("join",new JoinOperator());
//        Object r = runner.execute("1 join 2 join 3", context, null, false, false);
//        System.out.println(r);

        //(2)replaceOperator
//        runner.replaceOperator("+",new JoinOperator());
//        Object r = runner.execute("1 + 2 + 3", context, null, false, false);
//        System.out.println(r);


        //(3)addFunction
        runner.addFunction("join",new JoinOperator());
        Object r = runner.execute("join(1,2,3)", context, null, false, false);
        System.out.println(r);
    }

    /**
     *  如何自定义Operator
     *  定义一个继承自com.ql.util.express.Operator的操作符
     */
    public class JoinOperator extends Operator {
        public Object executeInner(Object[] list) throws Exception {
            Object opdata1 = list[0];
            Object opdata2 = list[1];
            if(opdata1 instanceof java.util.List){
                ((java.util.List)opdata1).add(opdata2);
                return opdata1;
            }else{
                java.util.List result = new java.util.ArrayList();
                result.add(opdata1);
                result.add(opdata2);
                return result;
            }
        }
    }
}
