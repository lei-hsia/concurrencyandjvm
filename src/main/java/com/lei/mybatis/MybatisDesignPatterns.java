package com.lei.mybatis;

import com.sun.tools.javac.util.Log;

import java.lang.reflect.Constructor;

public class MybatisDesignPatterns {
    public static void main(String[] args) {

        // 单例模式: 线程级别的ErrorContext; mybatis级别的LogFactory
        ErrorContext singletonContext = ErrorContext.getInstance();
    }
}

// thread-level
class ErrorContext {
    private static final ThreadLocal<ErrorContext> LOCAL = new ThreadLocal<>();

    private ErrorContext(){ }

    public static ErrorContext getInstance() {
        ErrorContext context = LOCAL.get();
        if (context == null) {
            context = new ErrorContext();
            LOCAL.set(context);
        }
        return context;
    }
}
// mybatis-level
class LogFactory {
    private static Constructor<? extends Log> logConstructor;

    private LogFactory() {}

    public static long getLog(Class<?> aClass) throws ClassNotFoundException {
        return getLog(Class.forName(aClass.getName()));
    }

}

