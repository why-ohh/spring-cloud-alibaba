/*
 * Copyright 2023-2024 the original author or authors.
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

package com.alibaba.cloud.ai.example.tongyi.service.impl.textembedding;

import java.util.List;
import java.util.logging.Logger;

import com.alibaba.cloud.ai.example.tongyi.service.AbstractTongYiServiceImpl;

import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.stereotype.Service;

/**
 * @author why_ohh
 * @author <a href="mailto:550588941@qq.com">why_ohh</a>
 */

@Service
public class TongYiTextEmbeddingServiceImpl extends AbstractTongYiServiceImpl {

	private final Logger logger = Logger.getLogger(TongYiTextEmbeddingServiceImpl.class.getName());

	private final EmbeddingClient embeddingClient;

	public TongYiTextEmbeddingServiceImpl(EmbeddingClient embeddingClient) {

		this.embeddingClient = embeddingClient;
	}

	@Override
	public List<Double> textEmbedding(String text) {

		return embeddingClient.embed(text);
	}
}
