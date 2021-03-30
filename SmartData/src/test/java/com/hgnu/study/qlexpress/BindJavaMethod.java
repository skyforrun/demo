package com.hgnu.study.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.junit.Test;

/**
 * @Author zj
 * @Description 绑定java类或者对象的method
 * @Date 2021/3/30 15:05
 */
public class BindJavaMethod {

    ExpressRunner runner = new ExpressRunner();

    DefaultContext<String, Object> context = new DefaultContext<>();

    @Test
    public void test() throws Exception {
        runner.addFunctionOfClassMethod("取绝对值", Math.class.getName(), "abs", new String[] { "double" }, null);
        runner.addFunctionOfClassMethod("转换为大写", BeanExample.class.getName(), "upper", new String[] { "String" }, null);

        runner.addFunctionOfServiceMethod("打印", System.out, "println",new String[] { "String" }, null);
        runner.addFunctionOfServiceMethod("contains", new BeanExample(), "anyContains",
                new Class[] { String.class, String.class }, null);

        String exp = "取绝对值(-100);转换为大写(\"hello world\");打印(\"你好吗？\");contains(\"helloworld\",\"aeiou\")";
        Object execute = runner.execute(exp, context, null, false, false);
        System.out.println(execute);
    }

    public static class BeanExample {
        public static String upper(String abc) {
            return abc.toUpperCase();
        }
        public boolean anyContains(String str, String searchStr) {

            char[] s = str.toCharArray();
            for (char c : s) {
                if (searchStr.contains(c+"")) {
                    return true;
                }
            }
            return false;
        }
    }
}
