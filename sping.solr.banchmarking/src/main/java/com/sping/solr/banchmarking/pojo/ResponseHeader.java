package com.sping.solr.banchmarking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseHeader {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("QTime")
    private Integer qTime;
    @JsonProperty("params")
    private Params params;

}
