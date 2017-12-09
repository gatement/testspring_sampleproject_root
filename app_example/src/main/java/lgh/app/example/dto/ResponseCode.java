package lgh.app.example.dto;

public enum ResponseCode {

    SUCCESS(0, "成功"),
    SERVER_ERROR(1000, "服务器内部错误"),
    NOT_FOUND(1001, "访问接口不存在"),
    INVALID_ERROR(1002, "不合法的请求"),
    FORMAT_ERROR(1003, "请求格式错误");

    private int code;
    private String name;

    ResponseCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
