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
package cn.ajsgn.plugin.spring.mq.ali.rocket.support;

import java.util.Properties;

import com.aliyun.openservices.ons.api.PropertyKeyConst;

import cn.ajsgn.plugin.kit.PropertiesKit;

/**
 * <p>Rocket支持配置类</p>
 * @ClassName: RocketMQSupportConfig
 * @Description: Rocket支持配置类
 * @author Ajsgn@foxmail.com
 * @date 2019年4月3日 下午2:54:11
 */
public interface AliRocketMQSupportConfig {
	
	/**
	 * <p>AccessKey, 用于标识、校验用户身份<p>
	 * @Title: accessKey
	 * @Description: AccessKey, 用于标识、校验用户身份
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午2:55:09
	 * @return String 
	 */
	public String accessKey();

	/**
	 * <p>SecretKey, 用于标识、校验用户身份<p>
	 * @Title: secretKey
	 * @Description: SecretKey, 用于标识、校验用户身份
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午2:55:30
	 * @return String 
	 */
	public String secretKey();

	/**
	 * <p>Group ID，客户端ID<p>
	 * @Title: groupId
	 * @Description: Group ID，客户端ID
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午2:56:46
	 * @return String 
	 */
	public String groupId();

	/**
	 * <p>消息队列服务接入点<p>
	 * @Title: nameServerAddr
	 * @Description: 消息队列服务接入点
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午2:55:49
	 * @return String 
	 */
//	public String onsAddr();
	
	/**
	 * <p>在spring ioc容器中的名称，用于注入<p>
	 * <p>consumer端暂时不支持从spring IoC中获取对象<p>
	 * @Title: springIOCBeanName
	 * @Description: 在spring ioc容器中的名称
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午3:38:57
	 * @return String 
	 */
	public abstract String springIOCBeanName();
	
	/**
	 * <p>Name Server地址<p>
	 * <p>该属性会覆盖PropertyKeyConst.ONSAddr<p>
	 * @Title: nameSrvAddr
	 * @Description: Name Server地址
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午4:11:21
	 * @return String Name Server地址
	 */
	public String nameSrvAddr();
	
	/**
	 * <p>将参数封装成Properties对象，并返回给调用者<p>
	 * @Title: toProperties
	 * @Description: 将参数封装成Properties对象，并返回给调用者
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月3日 下午2:55:59
	 * @return Properties 参数封装对象
	 */
	public default Properties toProperties() {
		Properties properties = new Properties();
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.GROUP_ID, groupId());
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.AccessKey, accessKey());
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.SecretKey, secretKey());
//		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.ONSAddr, onsAddr());
		PropertiesKit.setPropertyIfNotNull(properties, PropertyKeyConst.NAMESRV_ADDR, nameSrvAddr());
		return properties;
	}
	
}
