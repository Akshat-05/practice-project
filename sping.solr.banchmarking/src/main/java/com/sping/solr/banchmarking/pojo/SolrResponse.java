package com.sping.solr.banchmarking.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolrResponse {
    @JsonProperty("responseHeader")
    private ResponseHeader responseHeader;
    @JsonProperty("response")
    private Response response;
}

