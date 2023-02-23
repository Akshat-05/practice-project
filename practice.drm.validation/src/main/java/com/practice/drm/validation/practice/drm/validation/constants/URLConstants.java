package com.practice.drm.validation.practice.drm.validation.constants;

public class URLConstants {

    public static final String SOLR_URL =
            "http://solr-collections-indexing-elb-8924e4f16beba345.elb.ap-south-1.amazonaws.com:8983" +
                    "/solr/zee5_main_collection/select?fl=id,drm_key_id,drm_resource_id&q.op=OR&q=asset_type:%s AND asset_subtype:%s &rows=%s&start=%s&wt=json";

}
