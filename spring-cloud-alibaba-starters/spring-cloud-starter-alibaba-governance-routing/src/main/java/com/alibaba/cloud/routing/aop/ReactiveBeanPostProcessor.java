/*
 * Copyright 2022-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.routing.aop;

import com.alibaba.cloud.routing.aop.interceptor.ReactiveInterceptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class ReactiveBeanPostProcessor implements BeanPostProcessor {

	@Autowired
	private ReactiveInterceptor webClientInterceptor;

	@Override
	public Object postProcessBeforeInitialization(Object targetBean, String beanName)
			throws BeansException {
		return targetBean;
	}

	@Override
	public Object postProcessAfterInitialization(Object targetBean, String beanName)
			throws BeansException {
		if (targetBean instanceof WebClient.Builder) {
			WebClient.Builder webClientBuilder = (WebClient.Builder) targetBean;
			webClientBuilder.filter(webClientInterceptor);
		}
		return targetBean;
	}

}