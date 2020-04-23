package com.es;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

public class MyRestClient {
    public static void main(String[] args) {
        // RestClient restClient1 = RestClient.builder(new HttpHost("localhost", 9200, "http")).build();
        RestHighLevelClient restClient =
                new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));

       // createIndex2(restClient);
        //deleteIndex(restClient);
        //updateIndex(restClient);
        addBulk(restClient);
    }

    public static void openIndex(RestHighLevelClient restClient) {
        OpenIndexRequest request = new OpenIndexRequest("bank");
        request.timeout("2m");
        request.masterNodeTimeout(TimeValue.timeValueMinutes(1));
        request.indicesOptions(IndicesOptions.strictExpandOpen());
        try {
            OpenIndexResponse openIndexResponse = restClient.indices().open(request, RequestOptions.DEFAULT);
            boolean acknowledged = openIndexResponse.isAcknowledged();
            boolean shardsAcked = openIndexResponse.isShardsAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getIndex(RestHighLevelClient restClient) {
        GetIndexRequest request = new GetIndexRequest("myindex");
        // Whether to return local information or retrieve the state from master node
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(true);

        try {
            Boolean exist = restClient.indices().exists(request, RequestOptions.DEFAULT);
            System.out.println(exist);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void createIndex(RestHighLevelClient restClient) {
        String INDEX_NAME = "myindex";
        String CREATE_INDEX = "{\n" +
                "  \"properties\": {\n" +
                "    \"message\": {\n" +
                "      \"type\": \"text\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        CreateIndexRequest request = new CreateIndexRequest(INDEX_NAME);
        request.settings(Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2));
        request.setTimeout(TimeValue.timeValueMinutes(2));
        request.mapping(CREATE_INDEX, XContentType.JSON);

        try {

            // 提供IndicesClient
            CreateIndexResponse res = restClient.indices().create(request, RequestOptions.DEFAULT);
            // Indicates whether all of the nodes have acknowledged the request
            if (!res.isAcknowledged()) {
                throw new RuntimeException("init failed");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createIndex2(RestHighLevelClient restClient) {
        IndexRequest indexRequest = new IndexRequest("posts")
                .id("1")
                .source("user", "kimchy", "postDate", new Date(), "message", "try out es");
        indexRequest.routing("routing");
        indexRequest.timeout("1s");
        //  indexRequest.versionType(VersionType.EXTERNAL);
        indexRequest.opType("create");

        try {
            IndexResponse response = restClient.index(indexRequest, RequestOptions.DEFAULT);
            String index = response.getIndex();
            String id = response.getId();
            if (response.getResult() == DocWriteResponse.Result.CREATED) {
                System.out.println("reponse created");
            } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
                System.out.println("reponse UPDATED");
            }
            ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                System.out.println("shardInfo not success");
            }
            if (shardInfo.getFailed() > 0) {
                for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                    String reason = failure.reason();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getIndex2(RestHighLevelClient restClient) {
        GetRequest request = new GetRequest("posts", "1");
        // request.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);
        String[] includes = new String[] {"message", "*Date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(false, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
        request.storedFields("_none_");
        try {
            GetResponse response = restClient.get(request, RequestOptions.DEFAULT);
            String index = response.getIndex();
            String id = response.getId();
            if (response.isExists()) {
                long version = response.getVersion();
                String sourceAsString = response.getSourceAsString();
                Map<String, Object> sourceAsMap = response.getSourceAsMap();
                byte[] sourceAsBytes = response.getSourceAsBytes();
                Map<String, DocumentField> fields = response.getFields();
                System.out.println("=======");
            }
            String message = response.getField("_routing").getValue();
            System.out.println("message");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除的不是Index，而是Index中特定id=1的记录
     *
     * @param restClient
     */
    public static void deleteIndex(RestHighLevelClient restClient) {
        DeleteRequest request = new DeleteRequest("posts", "1");
        try {
            DeleteResponse deleteResponse = restClient.delete(request, RequestOptions.DEFAULT);
            String index = deleteResponse.getIndex();
            String id = deleteResponse.getId();
            long version = deleteResponse.getVersion();
            ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
            if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

            }
            if (shardInfo.getFailed() > 0) {
                for (ReplicationResponse.ShardInfo.Failure failure :
                        shardInfo.getFailures()) {
                    String reason = failure.reason();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateIndex(RestHighLevelClient restClient) {
        UpdateRequest request = new UpdateRequest("posts", "1").doc("updated", new Date(), "reason", "daily update");
        String json = "{\"created\":\"2017-01-01\"}";
        request.upsert(json, XContentType.JSON);
        try {
            UpdateResponse updateResponse = restClient.update(request, RequestOptions.DEFAULT);
            String index = updateResponse.getIndex();
            String id = updateResponse.getId();
            long version = updateResponse.getVersion();
            if (updateResponse.getResult() == DocWriteResponse.Result.CREATED) {
                System.out.println("result is create");

            } else if (updateResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                System.out.println("result is update");

            } else if (updateResponse.getResult() == DocWriteResponse.Result.DELETED) {

            } else if (updateResponse.getResult() == DocWriteResponse.Result.NOOP) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addBulk(RestHighLevelClient restClient){
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest("posts").id("2")
                .source(XContentType.JSON,"field", "foo"));
        request.add(new IndexRequest("posts").id("3")
                .source(XContentType.JSON,"field", "bar"));
        request.add(new IndexRequest("posts").id("4")
                .source(XContentType.JSON,"field", "baz"));
        // 原本没有other这个域，更新这个请求，会在这个id上新增一条记录
        request.add(new UpdateRequest("posts", "2")
                .doc(XContentType.JSON,"other", "test"));
        try {
            BulkResponse bulkResponse = restClient.bulk(request, RequestOptions.DEFAULT);
            for (BulkItemResponse bulkItemResponse : bulkResponse) {
                DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                switch (bulkItemResponse.getOpType()) {
                    case INDEX:
                    case CREATE:
                        IndexResponse indexResponse = (IndexResponse) itemResponse;
                        break;
                    case UPDATE:
                        UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                        break;
                    case DELETE:
                        DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
