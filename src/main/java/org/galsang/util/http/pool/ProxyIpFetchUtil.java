package org.galsang.util.http.pool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.galsang.util.http.pool.ProxyIpPoolUtil.ProxyIpBean;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Description：拉取代理IP
 * <br /> Author： galsang
 */
public class ProxyIpFetchUtil {


    /**
     * 初始化代理IP池
     * @throws IOException
     * @throws URISyntaxException
     */
    @Test
    public void initProxyIpPool() throws IOException, URISyntaxException {

        String uri = "http://svip.kuaidaili.com/api/getproxy/?orderid=961565610669021&num=100&b_pcchrome=1&b_pcie=1&b_pcff=1&protocol=1&method=2&an_an=1&an_ha=1&quality=2&format=json&sep=1";

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().build()).build();
        try {
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            CloseableHttpResponse response = httpClient.execute(httpGet);

            try {
                int statusCode = response.getStatusLine().getStatusCode();

                //得到服务响应状态码
                if (statusCode == HttpStatus.SC_OK) {

                    Header[] headers = response.getAllHeaders();
                    System.out.println("----------- header start -------------------------");
                    for (Header header : headers) {
                        System.out.println(header.getName() + "== :" + header.getValue());
                    }
                    System.out.println("----------- header end -----------------------");

                    HttpEntity entity = response.getEntity();
                    String html = EntityUtils.toString(entity, "UTF-8");

                    JSONObject obj = JSON.parseObject(html);
                    JSONObject data = obj.getJSONObject("data");
                    JSONArray jsonArray = data.getObject("proxy_list", JSONArray.class);
                    List<String> proxyList = jsonArray.toJavaObject(List.class);

                    // TODO 待优化
                    proxyList.forEach(proxyIp ->{
                        String[] ipTemp = proxyIp.split(":");
                        ProxyIpPoolUtil.add(new ProxyIpBean(ipTemp[0], Integer.parseInt(ipTemp[1]), "http"));
                    });

                    System.out.println(ProxyIpPoolUtil.getAllEnableProxyIp());

                } else {
                    //否则，消耗掉实体
                    System.out.println("statusCode == " + statusCode);
                }
                EntityUtils.consume(response.getEntity());
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }


}
