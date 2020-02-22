package top.cyblogs.data;

import top.cyblogs.util.StringUtils;

public class SessionData {

    public static final String cookie = "";

    public static final String csrfKey = StringUtils.subString(cookie, "NTESSTUDYSI=", ";");
}
