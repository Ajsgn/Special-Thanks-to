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
package cn.ajsgn.test.plugin.spring.mq.ali.rocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.openservices.ons.api.Message;

import cn.ajsgn.plugin.spring.mq.ali.rocket.AliRocketMQTopicCenter;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private AliRocketMQTopicCenter topicCenter;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World ~";
	}
	
	@RequestMapping("/send")
	public String send(String topicName, String msg) {
		Message message = new Message();
		message.setBody(msg.getBytes());
		message.setMsgID(String.valueOf(System.currentTimeMillis()));
		message.setKey("key_for_test");
		message.setTag("tag_for_test");
		message.setTopic(topicName);
		topicCenter.routeMessage(message);
		return "Hello World ~";
	}
	
	
	
	
}
