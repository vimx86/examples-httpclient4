package org.galsang.util.http.pool;

import org.galsang.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * Description：用户代理工具类
 * <br /> Author： galsang
 */
public class UserAgentPoolUtil {

    // 量小
    private final static Set<UserAgentBean> userAgentBeans = new CopyOnWriteArraySet<>();

    public static String printInfo() {
        return "UserAgentPoolUtil{" + "userAgentBeans=" + userAgentBeans + "}";
    }

    /**
     * 总量
     *
     * @return
     */
    public static int size() {
        return userAgentBeans.size();
    }

    /**
     * 向池中增加用户代理
     *
     * @param userAgentBean
     */
    public static void add(UserAgentBean userAgentBean) {
        userAgentBeans.add(userAgentBean);
    }

    /**
     * 从池中移除用户代理
     *
     * @param userAgentBean
     */
    public static void remove(UserAgentBean userAgentBean) {
        userAgentBeans.remove(userAgentBean);
    }

    /**
     * 获取指定类型的用户代理
     *
     * @param userAgentType 用户代理类型
     * @return
     */
    public static Set<UserAgentBean> getAllUserAgentByType(String userAgentType) {
        return userAgentBeans.stream().filter(userAgentBean -> userAgentType.equals(userAgentBean.getType())).collect(Collectors.toSet());
    }

    /**
     * 随机获取一个用户代理
     * <br /> 默认获取手机用户代理
     *
     * @param userAgentType 代理类型
     * @return
     */
    public static UserAgentBean getRandomUserAgent(String userAgentType) {
        if (userAgentType == null) { // 默认获取手机用户代理
            userAgentType = UserAgentBean.TYPE_MOBILE;
        }
        List<UserAgentBean> userAgentBeanList = new ArrayList<>(UserAgentPoolUtil.getAllUserAgentByType(userAgentType));
        return userAgentBeanList.get(RandomUtil.getRandomIntWithIn(0, userAgentBeanList.size()));
    }

    /**
     * 随机获取一个手机用户代理信息
     * @return
     */
    public static String getRandomUserAgentInfo(){
        return UserAgentPoolUtil.getRandomUserAgent(null).getContent();
    }

    /**
     * 用户代理
     */
    public static class UserAgentBean {

        public static final String TYPE_MOBILE = "mobile";
        public static final String TYPE_PC = "pc";
        public static final String TYPE_IPAD = "ipad";

        private final String content;

        private final String type;

        public UserAgentBean(String content, String type) {
            this.content = content;
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public String getType() {
            return type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserAgentBean)) return false;

            UserAgentBean that = (UserAgentBean) o;

            if (getContent() != null ? !getContent().equals(that.getContent()) : that.getContent() != null) return false;
            return getType() != null ? getType().equals(that.getType()) : that.getType() == null;
        }

        @Override
        public int hashCode() {
            int result = getContent() != null ? getContent().hashCode() : 0;
            result = 31 * result + (getType() != null ? getType().hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "\nUserAgentBean{" + "content='" + content + '\'' + ", type='" + type + '\'' + '}';
        }
    }

}
