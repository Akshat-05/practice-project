package com.practice.drm.validation.practice.drm.validation.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Doc {

    @JsonProperty("id")
    private String id;
    @JsonProperty("drm_key_id")
    private String drmKeyId;
    @JsonProperty("drm_resource_id")
    private String drmResourceId;

}
