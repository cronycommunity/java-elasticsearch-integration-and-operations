package com.bigdata.elasticsearch;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class GetApiWithID {
    public static void main(String[] args) throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        //Get API with ID
        GetResponse response = client.prepareGet("product", "_doc", "2").get();

        Map<String, Object> source = response.getSource();

        String name = (String) source.get("name");
        String price = (String) source.get("price");

        System.out.println("name : " + name + " Price : " + price);

        client.close();
    }
}
