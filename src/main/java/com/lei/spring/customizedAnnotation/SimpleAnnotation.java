package com.lei.spring.customizedAnnotation;

import java.lang.annotation.*;

// @Documented @Target @Retention @Inherited: Target:目标注释对象; Retention: 保留策略:运行期间
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimpleAnnotation {
    String value();
}
