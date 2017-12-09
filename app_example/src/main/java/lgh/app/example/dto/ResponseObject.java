package lgh.app.example.dto;

import java.util.HashMap;

import lombok.Data;

@Data
public class ResponseObject {

    private Integer code;
    private String message;
    private Object data;

    public ResponseObject(ResponseCode code) {
        this.code = code.getCode();
        this.message = code.getName();
        this.data = new HashMap();
    }

    public ResponseObject(ResponseCode code, Object data) {
        this.code = code.getCode();
        this.message = code.getName();
        this.data = data;
    }

    public ResponseObject(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = new HashMap();
    }
}
