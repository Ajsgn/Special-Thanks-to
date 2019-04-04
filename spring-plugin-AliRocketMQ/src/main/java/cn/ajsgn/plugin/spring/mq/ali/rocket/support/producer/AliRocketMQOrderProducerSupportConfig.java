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

/**
 * <p>普通消息生成者</p>
 * @ClassName: AliRocketMQNormalProducerSupportConfig
 * @Description: 普通消息生成者
 * @author Ajsgn@foxmail.com
 * @date 2019年4月3日 下午4:22:19
 */
public interface AliRocketMQOrderProducerSupportConfig extends AliRocketMQProducerSupportConfig {
	
	/**
	 * <p>设置事务消息的第一次回查延迟时间<p>
	 * @Title: checkImmunityTimeInSeconds
	 * @Description: 设置事务消息的第一次回查延迟时间
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午6:00:30
	 * @return String 设置事务消息的第一次回查延迟时间
	 */
	public default String checkImmunityTimeInSeconds() {
		return null;
	}

	@Override
	public default Properties toProperties() {
		Properties properties = AliRocketMQProducerSupportConfig.super.toProperties();
		if(null == properties) {
			properties = new Properties();
		}
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.CheckImmunityTimeInSeconds, checkImmunityTimeInSeconds());
		return properties;
	}
	
}
