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
package cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer;

import com.aliyun.openservices.ons.api.order.MessageOrderListener;

/**
 * <p>普通消息消费者</p>
 * @ClassName: AliRocketMQNormalConsumerSupportConfig
 * @Description: 普通消息消费者
 * @author Ajsgn@foxmail.com
 * @date 2019年4月3日 下午2:44:17
 */
public interface AliRocketMQOrderConsumerSupportConfig extends AliRocketMQConsumerSupportConfig {
	
	/**
	 * <p>顺序消费者消费处理器<p>
	 * @Title: handler
	 * @Description: 顺序消费者消费处理器
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午3:18:55
	 * @return MessageListener 消息处理器
	 */
	public MessageOrderListener handler();
	
}
