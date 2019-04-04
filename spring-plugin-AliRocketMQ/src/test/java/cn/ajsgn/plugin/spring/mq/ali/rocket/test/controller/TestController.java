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
package cn.ajsgn.plugin.spring.mq.ali.rocket.test.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.bean.ProducerBean;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Resource(name="normalProducer")
	ProducerBean producer;
	
	@RequestMapping("/hello")
	public String hello() {
		
		Message message = new Message();
		message.setBody(String.valueOf(System.currentTimeMillis()).getBytes());
		message.setTopic("topic_for_test_normal");
		message.setMsgID(String.valueOf(System.currentTimeMillis()));
		message.setKey("key_for_test");
		message.setTag("tag_for_test");
		
		producer.send(message);
		
		
		return "Hello";
	}
	
}
