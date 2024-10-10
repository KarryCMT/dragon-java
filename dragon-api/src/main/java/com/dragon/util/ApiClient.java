package com.dragon.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
public class ApiClient {


    private static final int MaxConnectTimeout = 30000;
    private static final RequestConfig requestConfig;


    static {
        // 设置连接池
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(100);

        RequestConfig.Builder builder = RequestConfig.custom();
        // 设置连接超时
        builder.setConnectTimeout(MaxConnectTimeout);
        // 设置读取超时
        builder.setSocketTimeout(MaxConnectTimeout);
        // 设置从连接池获取连接实例的超时
        builder.setConnectionRequestTimeout(MaxConnectTimeout);
        // 在提交请求之前 测试连接是否可用
        requestConfig = builder.build();
    }


    /**
     * @param path       请求路径+端口+路由
     * @param params     请求参数
     * @param resultType 返回接受对象
     */
    public static <T> T call(String path, Map<String, Object> params, Type resultType) {
        String data = call(path, params);
        return JSON.parseObject(data, resultType);
    }

    private static String call(String path, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String response = null;
        HttpPost httpPostRequest = new HttpPost(path);
        CloseableHttpResponse httpResponse = null;
        try {
            httpPostRequest.setConfig(requestConfig);
            httpPostRequest.addHeader("Content-Type", "application/json");
            if (params != null) {
                StringEntity stringEntity = new StringEntity(JSONObject.toJSONString(params), StandardCharsets.UTF_8);
                log.info("数据入参格式打印 = " + JSONObject.toJSONString(params));
                stringEntity.setContentEncoding("UTF-8");
                httpPostRequest.setEntity(stringEntity);
            }
            httpResponse = httpClient.execute(httpPostRequest);
            HttpEntity entity = httpResponse.getEntity();
            response = EntityUtils.toString(entity, "UTF-8");
            log.info("调用结果 = " + response);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (httpResponse != null) {
                try {
                    EntityUtils.consume(httpResponse.getEntity());
                } catch (IOException e) {
                    log.error("查看报错信息:{}" + e.getMessage(), e);
                }
            }
        }
        return response;
    }
}
