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
package cn.ajsgn.plugin.kit;

import java.util.Properties;

/**
 * <p>properties工具类</p>
 * @ClassName: PropertiesKit
 * @Description: properties工具类
 * @author Ajsgn@foxmail.com
 * @date 2019年4月4日 上午11:33:35
 */
public class PropertiesKit {
	
	/**
	 * <p>如果不为空，则设置值<p>
	 * @Title: setPropertyIfNotNull
	 * @Description: 如果不为空，则设置值
	 * @param properties 源对象
	 * @param key 键
	 * @param value 值 
	 * @author Ajsgn@foxmail.com
	 * @date 2019年4月4日 上午11:33:51
	 */
	public static void setPropertyIfNotNull(Properties properties, String key, String value) {
		if(null != properties && null != key && null != value) {
			properties.setProperty(key, value);
		}
	}
	
	
}
