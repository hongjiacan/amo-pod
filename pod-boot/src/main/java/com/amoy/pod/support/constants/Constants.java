package com.amoy.pod.support.constants;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/2
 */
public class Constants {

    public static final String RoleCodeAdmin = "ROLE_ADMIN";

    /**
     * redis key前缀
     */
    public enum RedisKey {
        /**
         * 图片验证码
         */
        imageCode("key_imageCode_"),
        /**
         * jwt
         */
        jwt("key_jwt_");

        private String value;

        private RedisKey(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 资源类型
     */
    public enum ResourceType {
        /**
         * 根节点
         */
        root("0"),
        /**
         * 目录
         */
        directory("1"),
        /**
         * 菜单
         */
        menu("2"),
        /**
         * 功能项
         */
        function("3");

        private String value;

        private ResourceType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
