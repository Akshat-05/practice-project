package com.sping.solr.banchmarking.pojo;

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
    @JsonProperty("content_id")
    private String contentId;
    @JsonProperty("rights")
    private List<String> rights;
    @JsonProperty("countries_with_rights")
    private List<String> countriesWithRights;
    @JsonProperty("asset_type")
    private String assetType;
    @JsonProperty("asset_subtype")
    private String assetSubtype;
    @JsonProperty("item_sequence")
    private Integer itemSequence;
    @JsonProperty("genre")
    private List<String> genre;
    @JsonProperty("age_rating")
    private String ageRating;
    @JsonProperty("lang")
    private List<String> lang;
    @JsonProperty("is_platform_all")
    private List<Boolean> isPlatformAll;
    @JsonProperty("is_title_present")
    private List<Boolean> isTitlePresent;
    @JsonProperty("collection_content_id")
    private String collectionContentId;
    @JsonProperty("collection_tags")
    private List<String> collectionTags;
    @JsonProperty("item_tags")
    private List<String> itemTags;
    @JsonProperty("collection_languages")
    private List<String> collectionLanguages;
    @JsonProperty("collection_is_platform_all")
    private List<Boolean> collectionIsPlatformAll;
    @JsonProperty("collection_countries")
    private List<String> collectionCountries;
    @JsonProperty("content_type")
    private String contentType;
    @JsonProperty("_root_")
    private String root;
    @JsonProperty("_version_")
    private Long version;

}
