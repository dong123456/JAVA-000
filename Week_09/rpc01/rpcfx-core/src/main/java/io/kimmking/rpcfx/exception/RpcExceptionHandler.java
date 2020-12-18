package io.kimmking.rpcfx.exception;

import io.kimmking.rpcfx.api.RpcfxResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RpcExceptionHandler {

    @ExceptionHandler(RpcException.class)
    public RpcfxResponse RpcHandler (RpcException e) {
        RpcfxResponse response = new RpcfxResponse();
        response.setException(e);
        response.setStatus(false);
       return response;
    }
}
