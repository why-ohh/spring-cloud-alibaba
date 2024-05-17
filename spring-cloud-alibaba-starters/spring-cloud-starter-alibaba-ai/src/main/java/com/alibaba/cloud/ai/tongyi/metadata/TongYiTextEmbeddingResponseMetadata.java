package com.alibaba.cloud.ai.tongyi.metadata;

import com.alibaba.dashscope.embeddings.TextEmbeddingUsage;

import org.springframework.ai.embedding.EmbeddingResponseMetadata;

/**
 * @author why_ohh
 * @author <a href="mailto:550588941@qq.com">why_ohh</a>
 */

public class TongYiTextEmbeddingResponseMetadata extends EmbeddingResponseMetadata {

	private Integer totalTokens;

	protected TongYiTextEmbeddingResponseMetadata(Integer totalTokens) {

		this.totalTokens = totalTokens;
	}

	public static TongYiTextEmbeddingResponseMetadata from(TextEmbeddingUsage usage) {

		return new TongYiTextEmbeddingResponseMetadata(usage.getTotalTokens());
	}

	public Integer getTotalTokens() {

		return totalTokens;
	}

	public void setTotalTokens(Integer totalTokens) {

		this.totalTokens = totalTokens;
	}
}
