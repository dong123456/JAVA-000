package io.kimmking.rpcfx.exception;


public class RpcException extends Exception{

    static final long serialVersionUID = 123456789999L;

    private String msg;

    private int code = 500;

    public RpcException() {
        super();
    }


    public RpcException(String message) {
        super(message);
        this.msg = message;
    }

    public RpcException(String message, int code) {
        super(message);
        this.msg = message;
        this.code = code;
    }

    public RpcException(String message, int code, Throwable cause) {
        super(message, cause);
        this.msg = message;
        this.code = code;
    }

    public String getMsg () {
        return msg;
    }

    public void setMsg (String msg) {
        this.msg = msg;
    }

    public int getCode () {
        return code;
    }

    public void setCode (int code) {
        this.code = code;
    }



    public RpcException(Throwable cause) {
        super(cause);
    }
}
