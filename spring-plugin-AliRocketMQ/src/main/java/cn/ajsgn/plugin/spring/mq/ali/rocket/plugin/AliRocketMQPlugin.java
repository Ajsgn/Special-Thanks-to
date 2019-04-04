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
package cn.ajsgn.plugin.spring.mq.ali.rocket.plugin;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.batch.BatchConsumer;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.aliyun.openservices.ons.api.order.OrderConsumer;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;

import cn.ajsgn.plugin.spring.mq.ali.rocket.event.AliRocketMQEvent;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer.AliRocketMQBatchConsumerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer.AliRocketMQConsumerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer.AliRocketMQNormalConsumerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.consumer.AliRocketMQOrderConsumerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer.AliRocketMQNormalProducerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer.AliRocketMQOrderProducerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer.AliRocketMQProducerSupportConfig;
import cn.ajsgn.plugin.spring.mq.ali.rocket.support.producer.AliRocketMQTransactionProducerSupportConfig;

/**
 * <p>插件支持类</p>
 * @ClassName: AliRocketMQPlugin
 * @Description: 插件支持类
 * @author Ajsgn@foxmail.com
 * @date 2019年4月4日 下午1:04:19
 */
public class AliRocketMQPlugin implements BeanFactoryPostProcessor {
	
	//spring bean factory
	private ConfigurableListableBeanFactory beanFactory;
	//事件对象
	private AliRocketMQEvent event;
	
	public void init() {
		//TODO 这里获取到的bean没有进行初始化，所以会失败
		
		// 获取需要创建的消费者集合
		Map<String, AliRocketMQConsumerSupportConfig> consumerConfigs = beanFactory.getBeansOfType(AliRocketMQConsumerSupportConfig.class);
		initConsumers(consumerConfigs);
		// 获取需要创建的生产者集合
		Map<String, AliRocketMQProducerSupportConfig> producerConfigs = beanFactory.getBeansOfType(AliRocketMQProducerSupportConfig.class);
		initProducers(producerConfigs);
	}

	/*
	 * 初始化消费者，暂不支持放入IoC容器
	 */
	private void initConsumers(Map<String, AliRocketMQConsumerSupportConfig> consumerConfigs) {
		if(null != consumerConfigs) {
			for(Map.Entry<String, AliRocketMQConsumerSupportConfig> entry : consumerConfigs.entrySet()) {
				AliRocketMQConsumerSupportConfig consumerSupportConfig = entry.getValue();
				// 创建并启动消费者同时订阅topic
				if(consumerSupportConfig instanceof AliRocketMQNormalConsumerSupportConfig) {
					AliRocketMQNormalConsumerSupportConfig normalConsumerSupportConfig = (AliRocketMQNormalConsumerSupportConfig) consumerSupportConfig;
					
					Consumer consumer = ONSFactory.createConsumer(normalConsumerSupportConfig.toProperties());
					consumer.subscribe(normalConsumerSupportConfig.topic(), normalConsumerSupportConfig.consumerSubExpression(), normalConsumerSupportConfig.handler());
					consumer.start();
					//启动成功，事件通知
					event.pubConsumerStrat(normalConsumerSupportConfig);
				} else if (consumerSupportConfig instanceof AliRocketMQOrderConsumerSupportConfig) {
					AliRocketMQOrderConsumerSupportConfig orderConsumerSupportConfig = (AliRocketMQOrderConsumerSupportConfig) consumerSupportConfig;
					OrderConsumer consumer = ONSFactory.createOrderedConsumer(orderConsumerSupportConfig.toProperties());
					consumer.subscribe(orderConsumerSupportConfig.topic(), orderConsumerSupportConfig.consumerSubExpression(), orderConsumerSupportConfig.handler());
					consumer.start();
					//启动成功，事件通知
					event.pubConsumerStrat(orderConsumerSupportConfig);
				} else if (consumerSupportConfig instanceof AliRocketMQBatchConsumerSupportConfig) {
					AliRocketMQBatchConsumerSupportConfig batchConsumerSupportConfig = (AliRocketMQBatchConsumerSupportConfig) consumerSupportConfig;
					BatchConsumer consumer = ONSFactory.createBatchConsumer(batchConsumerSupportConfig.toProperties());
					consumer.subscribe(batchConsumerSupportConfig.topic(), batchConsumerSupportConfig.consumerSubExpression(), batchConsumerSupportConfig.handler());
					consumer.start();
					//启动成功，事件通知
					event.pubConsumerStrat(batchConsumerSupportConfig);
				} else {
					throw new RuntimeException(String.format("Unknown consumer config of AliRocketMQConsumerSupportConfig be found : %s", consumerSupportConfig.getClass().getName()));
				}
			}
		}
	}
	
	/*
	 * 初始化生产者，并放入到spring IoC容器
	 */
	private void initProducers(Map<String, AliRocketMQProducerSupportConfig> producerConfigs) {
		if(null != producerConfigs) {
		    DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
		    
			for(Map.Entry<String, AliRocketMQProducerSupportConfig> entry : producerConfigs.entrySet()) {
				AliRocketMQProducerSupportConfig producerSupportConfig = entry.getValue();
				if(producerSupportConfig instanceof AliRocketMQNormalProducerSupportConfig) {
					AliRocketMQNormalProducerSupportConfig normalProducerSupportConfig = (AliRocketMQNormalProducerSupportConfig) producerSupportConfig;
					// 注册spring bean
					// 通过BeanDefinitionBuilder创建bean定义
				    BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ProducerBean.class);
				    beanDefinitionBuilder.addPropertyValue("properties", normalProducerSupportConfig.toProperties());
				    // 注册bean
				    defaultListableBeanFactory.registerBeanDefinition(normalProducerSupportConfig.springIOCBeanName(), beanDefinitionBuilder.getRawBeanDefinition());
				    //启动生产者
				    ProducerBean producer = beanFactory.getBean(normalProducerSupportConfig.springIOCBeanName(), ProducerBean.class);
				    producer.start();
				    //启动成功，事件通知
				    event.pubProducerStrat(normalProducerSupportConfig);
				} else if(producerSupportConfig instanceof AliRocketMQOrderProducerSupportConfig) {
					AliRocketMQOrderProducerSupportConfig orderProducerSupportConfig = (AliRocketMQOrderProducerSupportConfig) producerSupportConfig;

					BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(OrderProducer.class);
					beanDefinitionBuilder.addPropertyValue("properties", orderProducerSupportConfig.toProperties());
					
					defaultListableBeanFactory.registerBeanDefinition(orderProducerSupportConfig.springIOCBeanName(), beanDefinitionBuilder.getRawBeanDefinition());
					
					OrderProducer producer = beanFactory.getBean(orderProducerSupportConfig.springIOCBeanName(), OrderProducer.class);
					producer.start();
					
					//启动成功，事件通知
					event.pubProducerStrat(orderProducerSupportConfig);
				} else if(producerSupportConfig instanceof AliRocketMQTransactionProducerSupportConfig) {
					AliRocketMQTransactionProducerSupportConfig transactionProducerSupportConfig = (AliRocketMQTransactionProducerSupportConfig) producerSupportConfig;
					
					BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TransactionProducer.class);
					beanDefinitionBuilder.addPropertyValue("properties", transactionProducerSupportConfig.toProperties());
					
					defaultListableBeanFactory.registerBeanDefinition(transactionProducerSupportConfig.springIOCBeanName(), beanDefinitionBuilder.getRawBeanDefinition());

					TransactionProducer producer = beanFactory.getBean(transactionProducerSupportConfig.springIOCBeanName(), TransactionProducer.class);
					producer.start();
					
					//启动成功，事件通知
					event.pubProducerStrat(transactionProducerSupportConfig);
				} else {
					throw new RuntimeException(String.format("Unknown producer config of AliRocketMQProducerSupportConfig be found : %s", producerSupportConfig.getClass().getName()));
				}
			}
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
		this.event = beanFactory.getBean(AliRocketMQEvent.class);
		init();
	}

}
