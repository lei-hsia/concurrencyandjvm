package com.lei.javase.interviews.mianshibaodian;


/*
*   在Java中 == 在JVM中, 内存中，代码中；一个字符分为2部分: JVM内部和OS的文件系统;
*               在JVM内部统一使用Unicode; 所有编码转换只发生在边界地方,i.e. JVM和OS交界处, 也就是各类输入输出流起作用地方;
*       IO字节流，IO字符流: 字节流没有变，字符流:默认做了编码转换;
*
* */
public class JVMUnicode {
    public static void main(String[] args) {
        char han = '永';
        System.out.format("%x", (short)han);

        char han1 = 0x6c38;
        System.out.println(han1);
    }
}
