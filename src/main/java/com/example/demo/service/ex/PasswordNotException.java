package com.example.demo.service.ex;

//密码不正确
public class PasswordNotException extends ServiceException{
    public PasswordNotException() {
        super();
    }

    public PasswordNotException(String message) {
        super(message);
    }

    public PasswordNotException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
