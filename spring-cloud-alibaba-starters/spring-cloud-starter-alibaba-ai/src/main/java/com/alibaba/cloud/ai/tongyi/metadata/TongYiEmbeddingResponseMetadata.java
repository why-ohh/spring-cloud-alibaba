package com.alibaba.cloud.ai.tongyi.metadata;

import com.alibaba.dashscope.embeddings.TextEmbeddingResult;
import com.alibaba.dashscope.embeddings.TextEmbeddingUsage;

import org.springframework.ai.embedding.EmbeddingResponseMetadata;
import org.springframework.util.Assert;

import java.util.Objects;

public class TongYiEmbeddingResponseMetadata extends EmbeddingResponseMetadata {
    private String requestId;

    private TextEmbeddingUsage usage;


    public static TongYiEmbeddingResponseMetadata from(TextEmbeddingResult textEmbeddingResult) {
        Assert.notNull(textEmbeddingResult, "TongYiTextEmbeddingResult must not be null");

        return new TongYiEmbeddingResponseMetadata(
                textEmbeddingResult.getRequestId(),
                textEmbeddingResult.getUsage()
        )
    }

    public TongYiEmbeddingResponseMetadata(
            String requestId,
            TextEmbeddingUsage usage
    ) {
        this.requestId = requestId;
        this.usage = usage;

    }

    public TextEmbeddingUsage getUsage() {
        return usage;
    }

    public void setUsage(TextEmbeddingUsage usage) {
        this.usage = usage;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }

        TongYiEmbeddingResponseMetadata that  = (TongYiEmbeddingResponseMetadata) o;
        return Objects.equals(requestId,this.requestId);

    }

    @Override
    public int hashCode(){
        return Objects.hash(requestId);
    }

}




