package com.lei.concurrentandjvm.jvmgcjmm2044;

public class TestPassValue {

    private void changeValue1(int age) {
        age = 30;
    }

    private void changeValue2(Person p) {
        p.setName("xxx");
    }

    private void changeValue3(String str) {
        str = "xxx";
    }

    public static void main(String[] args) {
        TestPassValue test = new TestPassValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age----"+age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("person----"+person.getName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("String----"+str);
    }

}
