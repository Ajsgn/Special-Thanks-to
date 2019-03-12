/*
 * Copyright (c) 2019, Ajsgn 杨光 (Ajsgn@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ajsgn.test.plugin.spring.mq.ali.rocket.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;

import cn.ajsgn.plugin.spring.mq.ali.rocket.topic.MessageHandler;

@Component
public class TestMessageHandle implements MessageHandler {	//测试消息处理
	
//	@Autowired
//	private XxxService xxxService;

	private Logger log = LoggerFactory.getLogger(TestMessageHandle.class);

	@Override
	public Action consume(Message message, ConsumeContext context) {
//		xxxService.doSomething();
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

	@Override
	public String consumerExpression() {
		return "*";
	}

}
