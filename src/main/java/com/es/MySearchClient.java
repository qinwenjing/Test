package com.es;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpHost;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

public class MySearchClient {
    public static void main(String[] args) {
        RestHighLevelClient restClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
        search(restClient);
    }

    public static void search(RestHighLevelClient restClient){
        SearchRequest searchRequest = new SearchRequest("shakespeare");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //  profile the execution of queries and aggregations for a specific search request.
        searchSourceBuilder.profile(true);
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(QueryBuilders.termQuery("play_name","Henry IV"));
        searchSourceBuilder.from(5);
        searchSourceBuilder.size(5);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchSourceBuilder.sort(new FieldSortBuilder("_id").order(SortOrder.ASC));
        String[] includesFields = new String[]{"type","line_id","play_name"};
        String[] excludesFields = new String[]{"line_number"};
        searchSourceBuilder.fetchSource(includesFields, excludesFields);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field fieldType = new HighlightBuilder.Field("speaker");
        highlightBuilder.field(fieldType);
        HighlightBuilder.Field fieldName = new HighlightBuilder.Field("play_name");
        highlightBuilder.field(fieldName);
        //fieldType.highlighterType("unified");
        searchSourceBuilder.highlighter(highlightBuilder);



        searchRequest.source(searchSourceBuilder);

     /*   TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_company")
                .field("company.keyword");
        aggregation.subAggregation(AggregationBuilders.avg("average_age")
                .field("age"));
        searchSourceBuilder.aggregation(aggregation);*/

        try {
            SearchResponse searchResponse = restClient.search(searchRequest, RequestOptions.DEFAULT);
            RestStatus status = searchResponse.status();
            TimeValue took = searchResponse.getTook();
            Boolean terminatedEarly = searchResponse.isTerminatedEarly();
            boolean timeOut = searchResponse.isTimedOut();
            int totalShards = searchResponse.getTotalShards();
            int successfulShards = searchResponse.getSuccessfulShards();
            int failedShards = searchResponse.getFailedShards();

            SearchHits hits = searchResponse.getHits();
            TotalHits totalHits = hits.getTotalHits();
            SearchHit[] searchHits = hits.getHits();
            for(SearchHit hit : searchHits){
                String index = hit.getIndex();
                String id = hit.getId();
                float score = hit.getScore();
                String sourceAsString = hit.getSourceAsString();
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField highlightField = highlightFields.get("play_name");
                Text[] fragments = highlightField.fragments();
                String fragmentString = fragments[0].string();

            }
            // the total number of hits, must be interpreted in the context of totalHits.relation
            long numHits = totalHits.value;
            // whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
            TotalHits.Relation relation = totalHits.relation;
            float maxScore = hits.getMaxScore();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
