package org.galsang.util.http.pool;

import org.galsang.util.RandomUtil;
import org.galsang.util.http.pool.UserAgentPoolUtil.UserAgentBean;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：
 * <br /> Author： galsang
 */
public class UserAgentPoolUtilTest {

    List<String> list = new ArrayList<>();

    @Test
    public void testUserAgentPool() throws Exception {

        list.add("Mozilla/5.0 (Linux; Android 7.0; EVA-AL10 Build/HUAWEIEVA-AL10; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 " +
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
        System.out.println("User-Agent-Pool-SIZE === " + UserAgentPoolUtil.size());

        System.out.println(UserAgentPoolUtil.printInfo());

        System.out.println(" ================================================== ");

        System.out.println(UserAgentPoolUtil.getRandomUserAgent(null));


    }


}