package cn.com.kxyt.handle;

import cn.com.kxyt.core.Result;
import cn.com.kxyt.service.CityService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobHandle extends IJobHandler {

    @Autowired
    CityService cityService;

    @XxlJob("TestHandler")
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        s = "大声道撒";
        System.out.println(s);
        return ReturnT.SUCCESS;
    }

    @XxlJob("cityHandler")
    public ReturnT<String> execute1(String s) throws Exception {
        int i = cityService.importAll();
        return new ReturnT<>(String.valueOf(i));
    }
}
