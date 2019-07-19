package com.example.utils;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Mr.LB
 * @description: ES客户端工具类
 * @date 2019/7/18 15:18
 */
@Component
public class ElasticSearchClientUtil {

    private static Client client;

    private static String ipAddress;

    private static Integer port;

    private static String clusterName;

    @Value("${elasticsearch.ipAddress}")
    public void setIpAddress(String ipAddress) {
        ElasticSearchClientUtil.ipAddress = ipAddress;
    }

    @Value("${elasticsearch.port}")
    public void setPort(Integer port) {
        ElasticSearchClientUtil.port = port;
    }

    @Value("${spring.data.elasticsearch.cluster-name}")
    public void setClusterName(String clusterName) {
        ElasticSearchClientUtil.clusterName = clusterName;
    }

    /**
     * 获取es客户端实例
     * @return
     * @throws UnknownHostException
     */
    public static Client getClientInstance() throws UnknownHostException {
        synchronized (ElasticSearchClientUtil.class){
            if(null == client){
                Settings setting = Settings.builder()
                                            .put("cluster.name",clusterName)
                                            .put("client.transport.sniff", true)//启动嗅探功能
                                            .build();

                client = new PreBuiltTransportClient(setting)
                        .addTransportAddresses(new InetSocketTransportAddress(InetAddress.getByName(ipAddress), port));
            }
            return client;
        }
    }

    public static void main(String[] args) {
        DeleteIndexResponse response = client.admin().indices().prepareDelete("operate_system_log_index").execute().actionGet();
    }
}
