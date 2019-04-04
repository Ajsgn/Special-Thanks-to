package cn.ajsgn.plugin.spring.mq.ali.rocket.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

@Component
public class MessageHandler implements MessageListener {

	Logger log = LoggerFactory.getLogger(MessageHandler.class);

	@Override
	public Action consume(Message message, ConsumeContext context) {
		String msg = new String(message.getBody());
		log.info(String.format("【消费者】 获取生成者消息 : %s ", msg));
		Action result = null;
		int i = Double.valueOf((Math.random() * 10)).intValue();
		if (i % 2 == 0) {
			result = Action.ReconsumeLater;
			log.info(String.format("【失败消费】 : %s", msg));
		} else {
			result = Action.CommitMessage;
			log.info(String.format("【成功消费】 : %s", msg));
		}
		return result;
	}

}