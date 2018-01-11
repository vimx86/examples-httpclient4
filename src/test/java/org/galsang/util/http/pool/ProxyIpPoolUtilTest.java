package org.galsang.util.http.pool;

import org.galsang.util.http.pool.ProxyIpPoolUtil.ProxyIpBean;
import org.junit.Test;

/**
 * Description：
 * <br /> Author： galsang
 */
public class ProxyIpPoolUtilTest {


    @Test
    public void testProxyIpPool() {

        for (int i = 0; i < 10; i++) {
            int start = 10;
            int hostnameSuffix = start + i;
            ProxyIpPoolUtil.add(new ProxyIpBean("192.168.6." + hostnameSuffix, 8090, "http"));
        }

        ProxyIpBean proxyIpBean = new ProxyIpBean("192.168.6.100", 8090, "http");
        proxyIpBean.setEnable(false);
        ProxyIpPoolUtil.add(proxyIpBean);

        System.out.println(ProxyIpPoolUtil.printInfo());

        System.out.println(ProxyIpPoolUtil.getAllEnableProxyIp());

        System.out.println(" ================================================== ");

//        List<ProxyIpBean> proxyIpBeanList = new ArrayList<>(ProxyIpPoolUtil.getAllEnableProxyIp());
//        System.out.println(proxyIpBeanList);
//        proxyIpBean = proxyIpBeanList.get(RandomUtil.getRandomIntWithIn(0, proxyIpBeanList.size()));
        proxyIpBean = ProxyIpPoolUtil.getRandomProxyIp();
        System.out.println("随机选取的ip为 ==== " + proxyIpBean);


    }


}