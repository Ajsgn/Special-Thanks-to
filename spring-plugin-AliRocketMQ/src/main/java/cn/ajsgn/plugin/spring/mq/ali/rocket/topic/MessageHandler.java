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
package cn.ajsgn.plugin.spring.mq.ali.rocket.topic;

import com.aliyun.openservices.ons.api.MessageListener;

/**
 * <p>消息处理器</p>
 * @ClassName: MessageHandler
 * @Description: 消息处理器
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:29:40
 */
public interface MessageHandler extends MessageListener{
	
	/**
	 * <p>当前消息处理器订阅的消费者tag<p>
	 * @Title: consumerExpression
	 * @Description: 当前消息处理器订阅的消费者tag
	 * @author Ajsgn@foxmail.com
	 * @date 2019年3月12日 下午1:29:51
	 * @return String 订阅的消费者tag
	 */
	public String consumerExpression();
	
}
