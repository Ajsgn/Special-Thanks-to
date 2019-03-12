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
package cn.ajsgn.plugin.spring.mq.ali.rocket;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.ajsgn.plugin.spring.mq.ali.rocket.event.AliTopicEventListener;
import cn.ajsgn.plugin.spring.mq.ali.rocket.regist.AliRocketMQRegistRule;

/**
 * <p>插件启动支持类对象</p>
 * @ClassName: AliRocketMQPlugin
 * @Description: 插件启动支持类对象
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:06:21
 */
public class AliRocketMQPlugin implements ApplicationContextAware {
	
	@Autowired
	private AliRocketMQTopicCenter topicCenter;	// topics
	@Autowired
	private AliRocketMQEvent event;	// topic event
	private ApplicationContext applicationContext;	// spring 上下文
	
	private Logger log = LoggerFactory.getLogger(AliRocketMQPlugin.class);

	@PostConstruct
	public void init() {
		// 获取所有的规则配置
		Map<String, AliRocketMQRegistRule> rules = applicationContext.getBeansOfType(AliRocketMQRegistRule.class);
		// 事件监听
		Map<String, AliTopicEventListener> topicEventListenersMap = applicationContext.getBeansOfType(AliTopicEventListener.class);
		// 注册事件监听器
		event.buildWith(topicCenter);
		event.registEventListeners(topicEventListenersMap.values());
		topicCenter.registEvent(event);
		// 检测到没有需要初始化的配置，给出警告
		if(true == rules.isEmpty()) {
			log.warn("No bean type of RocketMQRegistRule be found ~");
		} else {
			// 循环生成注册topic
			for(Map.Entry<String, AliRocketMQRegistRule> rule : rules.entrySet()) {
				AliRocketMQRegistRule rocketMQRegistRule = rule.getValue();
				topicCenter.regist(rocketMQRegistRule);
			}
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
