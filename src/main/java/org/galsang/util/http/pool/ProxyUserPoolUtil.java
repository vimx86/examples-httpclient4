package org.galsang.util.http.pool;

import org.galsang.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Description： 用户账号工具类
 * <br /> Author： galsang
 */
public class ProxyUserPoolUtil {

    private final static Set<ProxyUserBean> proxyUserBeans = new CopyOnWriteArraySet<>();

    /**
     * 向池中增加用户账号
     *
     * @param proxyUserBean
     */
    public static void add(ProxyUserBean proxyUserBean) {
        proxyUserBeans.add(proxyUserBean);
    }

    /**
     * 清空账号池，重新添加一批账户号
     * @param proxyUserBeanList
     */
    public static void addAll(List<ProxyUserBean> proxyUserBeanList){
        proxyUserBeanList.clear();
        proxyUserBeanList.addAll(proxyUserBeanList);
    }

    /**
     * 从池中移除用户账号
     *
     * @param proxyUserBean
     */
    public static void remove(ProxyUserBean proxyUserBean) {
        proxyUserBeans.remove(proxyUserBean);
    }

    /**
     * 随机获取一个用户账号
     *
     * @return
     */
    public static ProxyUserBean getRandomProxyUser() {
        List<ProxyUserBean> proxyUserBeanList = new ArrayList<>(ProxyUserPoolUtil.proxyUserBeans);
        return proxyUserBeanList.get(RandomUtil.getRandomIntWithIn(0, proxyUserBeanList.size()));
    }

    public static class ProxyUserBean{

        /**
         * 登录名
         */
        private String loginName;

        /**
         * 密码（密文）
         */
        private String password;

        public ProxyUserBean() {
        }

        public ProxyUserBean(String loginName, String password) {
            this.loginName = loginName;
            this.password = password;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

}
