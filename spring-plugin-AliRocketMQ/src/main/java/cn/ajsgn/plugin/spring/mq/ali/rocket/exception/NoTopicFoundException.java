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
package cn.ajsgn.plugin.spring.mq.ali.rocket.exception;

/**
 * <p>没有找到指定topic异常</p>
 * @ClassName: NoTopicFoundException
 * @Description: 没有找到指定topic异常
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:23:59
 */
public class NoTopicFoundException extends RuntimeException {

	private static final long serialVersionUID = 8031115640328320879L;
	
	public NoTopicFoundException(String errorMsg) {
		super(errorMsg);
	}

}
