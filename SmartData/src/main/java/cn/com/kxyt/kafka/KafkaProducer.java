package cn.com.kxyt.kafka;

import cn.com.kxyt.entity.City;
import com.alibaba.fastjson.JSON;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 生产者发送消息
     */
    public void sendUserMessage(City city) {
        city.setId(789L);
        city.setName("unknown");
        city.setCountrycode("un");
        String s = JSON.toJSONString(city);
        kafkaTemplate.send("city",s);
        logger.info("生产消息至Kafka" + s);
    }
}
