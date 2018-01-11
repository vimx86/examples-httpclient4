package org.apache.http.examples.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.galsang.util.RandomUtil;
import org.galsang.util.http.pool.ProxyIpPoolUtil;
import org.galsang.util.http.pool.ProxyIpPoolUtil.ProxyIpBean;
import org.galsang.util.http.pool.ProxyUserPoolUtil;
import org.galsang.util.http.pool.ProxyUserPoolUtil.ProxyUserBean;
import org.galsang.util.http.pool.UserAgentPoolUtil;
import org.galsang.util.http.pool.UserAgentPoolUtil.UserAgentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * <br /> Author： galsang
 */
public class UserAgentClientExecuteProxy {

    public static void main(String[] args) throws Exception {

        { // 初始化代理ip池
//            for (int i = 0; i < 10; i++) {
//                int start = 10;
//                int hostnameSuffix = start + i;
//                ProxyIpPoolUtil.add(new ProxyIpBean("192.168.6." + hostnameSuffix, 8090, "http"));
//            }
            ProxyIpPoolUtil.add(new ProxyIpBean("177.114.78.197", 8080, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("192.116.142.153", 8080, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("191.210.144.106", 8080, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("200.115.231.143", 8080, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("88.157.149.250", 8080, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("101.248.64.68", 8080, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("39.134.161.12", 8080, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("94.177.160.48", 8080, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("124.238.235.135", 8000, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("92.222.93.65", 9999, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("34.198.16.211", 211, "http"));
            ProxyIpPoolUtil.add(new ProxyIpBean("94.177.248.160", 80, "http"));

//            ProxyIpFetchUtil.initProxyIpPool();
        }

        {// 初始化用户代理池
            List<String> list = new ArrayList<>();

            list.add("Mozilla/5.0 (Linux; Android 7.0; EVA-AL10 Build/HUAWEIEVA-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0" +
                    "Chrome/55.0.2883.91 Mobile Safari/537.36 rabbit/1.0 baiduboxapp/7.1 (Baidu; P1 7.0)");
            list.add("Mozilla/5.0 (iPhone; CPU iPhone OS 10_1 like Mac OS X) AppleWebKit/602.2.14 (KHTML, like Gecko) Mobile/14B72 " +
                    "baiduboxapp/0_11.0.1.8_enohpi_8022_2421/1.01_2C2%258enohPi/1099a/829DC6DBC0AEAD61A02C1CFCE879036A930962A7BORAPKKEOQM/1");
            list.add("Mozilla/5.0 (iPhone; CPU iPhone OS 10_2 like Mac OS X) AppleWebKit/602.3.12 (KHTML, like Gecko) Mobile/14C92 baiduboxapp/0_11.0.1.8_enohpi_4331_057/2.01_1C2%258enohPi/1099a/429925480CF15D3A5213A5603C2B469B0541C7053ORAETNMMPM/1");
            list.add("Mozilla/5.0 (Linux; Android 5.1.1; vivo X7 Build/LMY47V; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/48.0.2564.116 Mobile Safari/537.36 baiduboxapp/8.6.5 (Baidu; P1 5.1.1)");
            list.add("Mozilla/5.0 (Linux; U; Android 5.1; zh-cn; m1 metal Build/LMY47I) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 " +
                    "Chrome/37.0.0.0 MQQBrowser/7.6 Mobile Safari/537.36");
            list.add("Mozilla/5.0 (iPhone 6s; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 " +
                    "MQQBrowser/7.6.0 Mobile/14E304 Safari/8536.25 MttCustomUA/2 QBWebViewType/1 WKType/1");
            list.add("Mozilla/5.0 (Linux; Android 4.0.3; M031 Build/IML74K) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 " +
                    "Mobile Safari/535.19");
            list.add("Mozilla/5.0 (iPhone; CPU iPhone OS 7_0_4 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) CriOS/31.0.1650.18 Mobile/11B554a Safari/8536.25");

            for (int i = 0; i < 10; i++) {
                UserAgentPoolUtil.add(new UserAgentBean(list.get(RandomUtil.getRandomIntWithIn(0, list.size())), UserAgentBean.TYPE_MOBILE));
            }
        }

        {// 初始化用户账号池

            String[] loginNameArray = new String[]{
                    "13022106450",
                    "18516564993",
                    "18616964181",
                    "18616961149",
                    "18616961140",
                    "18616963140"
            };
            String password = "f93237d93ea71121109e5f58d2916f81";

            int length = loginNameArray.length;
            for (int i = 0; i < length; i++) {
                ProxyUserPoolUtil.add(new ProxyUserBean(loginNameArray[i], password));
            }
        }


        CloseableHttpClient httpClient = HttpClients.custom()
                .setUserAgent(UserAgentPoolUtil.getRandomUserAgentInfo())
                .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT).build()).build();
        try {
            HttpHost target = new HttpHost("www.runoob.com", 80, "http");
//            HttpHost target = new HttpHost("www.runoob.com", 80, "http");
//            http://www.runoob.com/redis/redis-lists.html

            // 随机获取一个可用的代理ip
            ProxyIpBean proxyIpBean = ProxyIpPoolUtil.getRandomProxyIp();

            HttpHost proxy = new HttpHost(proxyIpBean.getHostname(), proxyIpBean.getPort(), proxyIpBean.getScheme());

            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
            HttpGet httpGet = new HttpGet("/redis/redis-lists.html");
//            HttpGet httpGet = new HttpGet("/redis/redis-lists.html");

            httpGet.setConfig(config);

            httpGet.setHeader("Connection","Keep-Alive");

            System.out.println("Executing request " + httpGet.getRequestLine() + " to " + target + " via " + proxy);

            CloseableHttpResponse response = httpClient.execute(target, httpGet);
            try {
                System.out.println("------------------------------------------");
                System.out.println(response.getStatusLine());
//                System.out.println(EntityUtils.toString(response.getEntity()));

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
                    System.out.println("html == " + html);
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
