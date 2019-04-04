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
package cn.ajsgn.plugin.spring.mq.ali.rocket.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import cn.ajsgn.plugin.spring.mq.ali.rocket.event.AliRocketMQEvent;
import cn.ajsgn.plugin.spring.mq.ali.rocket.plugin.AliRocketMQPlugin;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AliRocketMQPlugin.class, AliRocketMQEvent.class})
/**
 * <p>启动aliRocketMQ服务注解</p>
 * @ClassName: EnableAliRocketMQ
 * @Description: 启动aliRocketMQ服务注解
 * @author Ajsgn@foxmail.com
 * @date 2019年3月12日 下午1:20:21
 */
public @interface EnableAliRocketMQ {

}
