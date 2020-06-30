package com.lei.spring.threelayercache;

import org.springframework.beans.factory.ObjectFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static sun.tools.jconsole.inspector.XObject.NULL_OBJECT;

public class ThreeLayerCache {

    /** Cache of singleton objects: bean name --> bean instance 一级缓存 */

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    /** Cache of early singleton objects: bean name --> bean instance 二级缓存 */

    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);

    /** Cache of singleton factories: bean name --> ObjectFactory 三级缓存 */

    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>(16);


    // 尝试从三级缓存获取单例
    protected Object getSingleton(String beanName, boolean allowEarlyReference) {

        Object singletonObject = this.singletonObjects.get(beanName);
        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
            synchronized (this.singletonObjects) {
                singletonObject = this.earlySingletonObjects.get(beanName);

                if (singletonObject == null && allowEarlyReference) {
                    ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);

                    if (singletonFactory != null) {
                        singletonObject = singletonFactory.getObject();
                        this.earlySingletonObjects.put(beanName, singletonObject);
                        this.singletonFactories.remove(beanName);
                    }
                }
            }
        }

        return (singletonObject != NULL_OBJECT ? singletonObject : null);
    }
}
