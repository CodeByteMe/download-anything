package top.cyblogs.support;

/**
 * 用来填写Secret参数
 * <p>
 * TODO 未进行文档人工翻译
 * <p>
 * 从1.18.4版本开始，除了HTTP基本授权外，aria2还提供RPC方法级别的授权。
 * 在将来的版本中，将删除HTTP基本授权，而RPC方法级别的授权将变为强制性。
 * <p>
 * 要使用RPC方法级别的授权，用户必须使用该--rpc-secret选项指定RPC秘密授权令牌。
 * 对于每个RPC方法调用，调用者必须包含token:做令牌的前缀 。
 * 如果不使用--rpc-secret选项，但是RPC方法中的第一个参数是字符串并以开头token:，
 * 则在处理请求之前，该参数也会从参数列表中删除。
 */
public interface Secret {

    static String token(String token) {
        if (token == null) {
            token = "";
        }
        return "token:" + token;
    }
}
