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

package com.alibaba.cloud.routing.zuul.filter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.cloud.routing.constant.LabelRoutingConstants;
import com.alibaba.cloud.routing.context.LabelRoutingContext;
import com.alibaba.cloud.routing.properties.LabelRoutingProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public class LabelRoutingZuulFilter extends ZuulFilter {

	// Zuul filter order.
	@Value("${" + LabelRoutingConstants.Zuul.ZUUL_ROUTE_FILTER_ORDER + ":"
			+ LabelRoutingConstants.Zuul.ZUUL_ROUTE_FILTER_ORDER_VALUE + "}")
	protected Integer zuulFilterOrder;

	// Gateway rule priority switch.
	@Value("${" + LabelRoutingConstants.Zuul.ZUUL_HEADER_PRIORITY + ":true}")
	protected Boolean zuulHeaderPriority;

	@Resource
	private LabelRoutingProperties properties;

	@Override
	public String filterType() {

		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return zuulFilterOrder;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {

		RequestContext context = RequestContext.getCurrentContext();
		applyRequestHeader(context);

		return null;
	}

	private void applyRequestHeader(RequestContext context) {

		// Use map to simplify if... else statement
		Map<String, String> routingPropertiesMap = new HashMap<>();
		routingPropertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_ZONE,
				properties.getZone());
		LabelRoutingContext.getCurrentContext().setRoutingZone(properties.getZone());
		routingPropertiesMap.put(LabelRoutingConstants.SCA_ROUTING_SERVICE_REGION,
				properties.getRegion());
		LabelRoutingContext.getCurrentContext().setRoutingRegion(properties.getRegion());

		LabelRoutingContext.getCurrentContext()
				.setHttpServletRequest(context.getRequest());

		routingPropertiesMap
				.forEach((k, v) -> setHeader(context, k, v, zuulHeaderPriority));

	}

	private void setHeader(RequestContext context, String headerName, String headerValue,
			Boolean zuulHeaderPriority) {
		if (StringUtils.isEmpty(headerValue)) {
			return;
		}

		if (zuulHeaderPriority) {

			context.addZuulRequestHeader(headerName, headerValue);
		}
		else {

			String header = context.getRequest().getHeader(headerName);

			if (StringUtils.isEmpty(header)) {

				context.addZuulRequestHeader(headerName, headerValue);
			}

		}
	}

}