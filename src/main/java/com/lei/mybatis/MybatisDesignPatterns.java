package com.lei.mybatis;

import com.sun.tools.javac.util.Log;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MybatisDesignPatterns {
    public static void main(String[] args) {

        // 单例模式: 线程级别的ErrorContext; mybatis级别的LogFactory
        ErrorContext singletonContext = ErrorContext.getInstance();
    }
}

/* 单例模式 */
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

/* 代理模式:借助MapperProxy; 而proxy用MapperProxyFactory工厂模式创建出来的 */
class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> methodCache = new ConcurrentHashMap();

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public Class<T> getMapperInterface() {
        return this.mapperInterface;
    }

    public Map<Method, MapperMethod> getMethodCache() {
        return this.methodCache;
    }

    protected T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, mapperProxy);
    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy(sqlSession, this.mapperInterface, this.methodCache);
        return this.newInstance(mapperProxy);
    }
}

/*  非常典型的，该MapperProxy类实现了InvocationHandler接口，并且实现了该接口的invoke方法。
class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6424540398559729838L;
    private static final int ALLOWED_MODES = 15;
    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }

            if (method.isDefault()) {
                if (privateLookupInMethod == null) {
                    return this.invokeDefaultMethodJava8(proxy, method, args);
                }

                return this.invokeDefaultMethodJava9(proxy, method, args);
            }
        } catch (Throwable var5) {
            throw ExceptionUtil.unwrapThrowable(var5);
        }

        MapperMethod mapperMethod = this.cachedMapperMethod(method);
        return mapperMethod.execute(this.sqlSession, args);
    }
}
*/
