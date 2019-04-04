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
package cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer;

import java.util.Properties;

import com.aliyun.openservices.ons.api.PropertyKeyConst;

import cn.ajsgn.plugin.kit.PropertiesKit;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.AliRocketMQSupportConfig;

/**
 * <p>消息生产者支持配置</p>
 * @ClassName: AliRocketMQProducerSupportConfig
 * @Description: 消息生产者支持配置
 * @author Ajsgn@foxmail.com
 * @date 2019年4月3日 下午4:13:01
 */
public interface AliRocketMQProducerSupportConfig extends AliRocketMQSupportConfig {
	
	/**
	 * <p>设置客户端接入来源，默认ALIYUN<p>
	 * @Title: onsChannel
	 * @Description: 设置客户端接入来源，默认ALIYUN
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午4:11:50
	 * @return String 设置客户端接入来源，默认ALIYUN
	 */
	public default String onsChannel() {
		return null;
	}
	
	/**
	 * <p>消息发送超时时间<p>
	 * @Title: sendMsgTimeoutMillis
	 * @Description: 消息发送超时时间，如果服务端在配置的对应时间内未ACK，则发送客户端认为该消息发送失败。
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午4:12:01
	 * @return String 消息发送超时时间
	 */
	public default String sendMsgTimeoutMillis() {
		return null;
	}
	
	@Override
	public default Properties toProperties() {
		Properties properties = AliRocketMQSupportConfig.super.toProperties();
		if(null == properties) {
			properties = new Properties();
		}
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.NAMESRV_ADDR, nameSrvAddr());
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.OnsChannel, onsChannel());
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.SendMsgTimeoutMillis, sendMsgTimeoutMillis());
		
		return properties;
	}
	
}
