package com.bigdata.elasticsearch;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class AddItem {
    public static void main(String[] args) throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));


        Map<String, Object> json = new HashMap<>();

        json.put("name", "Asus Vivabook");
        json.put("detail", "8 RAM 512 SSD");
        json.put("price","2200");

        IndexResponse indexResponse = client.prepareIndex("product", "_doc", "4")
                .setSource(json, XContentType.JSON)
                .get();

        System.out.println(indexResponse.getId());

        client.close();
    }
}
