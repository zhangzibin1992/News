package com.zhuoxinzhiqu.news.common;

/**
 * @author zhangzibin
 * Created by zhangzibin on 2016/10/19.
 */
public class CommonUtil {
    /** 联网的ip */
    public static String NETIP = "118.244.212.82";
    /** 联网的路径 */
    public static String NETPATH = "http://" + NETIP + ":9092/newsClient";
    /** SharedPreferences保存用户名键 */
    public static final String SHARE_USER_NAME = "userName";
    /** SharedPreferences保存用户名密码 */
    public static final String SHARE_USER_PWD = "userPwd";
    /** SharedPreferences保存是否第一次登陆 */
    public static final String SHARE_IS_FIRST_RUN = "isFirstRun";
    /** SharedPreferences存储路径 */
    public static final String SHAREPATH = "news_share";

}
