package com.lei.spring.customizedAnnotation;

import java.lang.reflect.Method;

public class UseAnnotation {

    @SimpleAnnotation("testStringValue")
    public void testMethod() {
        // do something
    }

    /*  1. 反射获得运行期间类名
        2. 类: 获得类中的方法名和字段名
        3. method/fields: 通过getAnnotation获得方法／字段上所有的注解
        4. 实现注解逻辑
    * */
    private static void annotationLogic() {
        Class<UseAnnotation> useAnnotationClass = UseAnnotation.class;
        for (Method method :useAnnotationClass.getMethods()) {
            SimpleAnnotation annotation = method.getAnnotation(SimpleAnnotation.class);
            if (annotation != null) {
                System.out.println("Method name: "+method.getName()); // 运行期间获得运行方法名
                System.out.println("value: "+annotation.value());     // 获得注解值
            }
        }
    }

    public static void main(String[] args) {
        annotationLogic();
    }
}
