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
package cn.ajsgn.plugin.spring.mq.ali.rocket.config;

import java.util.Properties;

import com.aliyun.openservices.ons.api.PropertyKeyConst;

/**
 * <p>ons mq config</p>
 * @ClassName: AliRocketMQConfig
 * @Description: ons mq config
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:20:47
 */
public class AliRocketMQConfig {

	private String accessKey;
	private String secretKey;
	private String groupId;
	private String nameServerAddr;

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getNameServerAddr() {
		return nameServerAddr;
	}

	public void setNameServerAddr(String nameServerAddr) {
		this.nameServerAddr = nameServerAddr;
	}

	public Properties toProperties() {
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.GROUP_ID, getGroupId());
		// 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.AccessKey, getAccessKey());
		// 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.SecretKey, getSecretKey());
		// 设置 TCP 接入域名（此处以公共云生产环境为例）
		properties.put(PropertyKeyConst.NAMESRV_ADDR, getNameServerAddr());
		return properties;
	}

	@Override
	public String toString() {
		return "RocketMQConfig [accessKey=" + accessKey + ", secretKey="
				+ secretKey + ", groupId=" + groupId + ", nameServerAddr="
				+ nameServerAddr + "]";
	}


}
