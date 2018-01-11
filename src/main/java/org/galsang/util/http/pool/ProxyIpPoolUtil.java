package org.galsang.util.http.pool;

import org.galsang.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * Description： 代理 ip 池工具
 * <br /> Author： galsang
 */
public class ProxyIpPoolUtil {

    // 创建容器，，，量大 //TODO 更深层次策略待设计
    private final static Set<ProxyIpBean> proxyIpBeans = new CopyOnWriteArraySet<>();

    public static String printInfo() {
        return "ProxyIpPoolUtil{" + "proxyIpBeans=" + proxyIpBeans + '}';
    }

    /**
     * 池中所有代理ip总量
     *
     * @return
     */
    public static int allSize() {
        return proxyIpBeans.size();
    }

    /**
     * 池中所有可以使用代理ip总量
     *
     * @return
     */
    public static int enableAllSize() {
        return proxyIpBeans.size();
    }


    /**
     * 向池中增加代理ip
     *
     * @param proxyIpBean
     */
    public static void add(ProxyIpBean proxyIpBean) {
        if (proxyIpBean.isEnable() == null) {
            proxyIpBean.setEnable(true); // 增加代理ip时，默认为可用。
        }
        proxyIpBeans.add(proxyIpBean);
    }

    /**
     * 从池中移除代理ip
     *
     * @param proxyIpBean
     */
    public static void remove(ProxyIpBean proxyIpBean) {
        proxyIpBeans.remove(proxyIpBean);
    }


    /**
     * 获取所有可用代理ip
     *
     * @return
     */
    public static Set<ProxyIpBean> getAllEnableProxyIp() {
        return proxyIpBeans.stream().filter(proxyIpBean -> proxyIpBean.isEnable() == true).collect(Collectors.toSet());
    }

    /**
     * 随机获取一个可用的代理ip
     *
     * @return
     */
    public static ProxyIpBean getRandomProxyIp() {
        List<ProxyIpBean> proxyIpBeanList = new ArrayList<>(ProxyIpPoolUtil.getAllEnableProxyIp());
        return proxyIpBeanList.get(RandomUtil.getRandomIntWithIn(0, proxyIpBeanList.size()));
    }

    /**
     * 代理 IP
     */
    public static class ProxyIpBean {

        /**
         * 主机
         */
        private final String hostname;
        /**
         * 端口
         */
        private final Integer port;
        /**
         * 协议
         */
        private final String scheme;
        /**
         * 代理 ip 是否可用
         */
        private Boolean enable = Boolean.TRUE;

        public ProxyIpBean(String hostname, Integer port, String scheme) {
            this.hostname = hostname;
            this.port = port;
            this.scheme = scheme;
        }

        @Override
        public String toString() {
            return "ProxyIpBean{" + "hostname='" + hostname + '\'' + ", port=" + port + ", scheme='" + scheme + '\'' + ", enable=" +
                    enable + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ProxyIpBean)) return false;

            ProxyIpBean that = (ProxyIpBean) o;

            if (getHostname() != null ? !getHostname().equals(that.getHostname()) : that.getHostname() != null) return false;
            if (getPort() != null ? !getPort().equals(that.getPort()) : that.getPort() != null) return false;
            if (getScheme() != null ? !getScheme().equals(that.getScheme()) : that.getScheme() != null) return false;
            return enable != null ? enable.equals(that.enable) : that.enable == null;
        }

        @Override
        public int hashCode() {
            int result = getHostname() != null ? getHostname().hashCode() : 0;
            result = 31 * result + (getPort() != null ? getPort().hashCode() : 0);
            result = 31 * result + (getScheme() != null ? getScheme().hashCode() : 0);
            result = 31 * result + (enable != null ? enable.hashCode() : 0);
            return result;
        }

        public String getHostname() {
            return hostname;
        }

        public Integer getPort() {
            return port;
        }

        public String getScheme() {
            return scheme;
        }

        public Boolean isEnable() {
            return enable;
        }

        public void setEnable(Boolean enable) {
            this.enable = enable;
        }

    }


}
