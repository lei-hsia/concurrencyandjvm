package com.lei.javase.interviews.leetcode.bytedance;

/*  for(1; 2; 3) { 4;} 执行顺序: 第一次1243，第二次243...
* */
public class AddStrings415 {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = num1.length()-1, j = num2.length()-1; i>=0 || j>=0 || carry > 0; --i, --j) {
            int x = i >= 0? num1.charAt(i) - '0' : 0;
            int y = j >= 0? num2.charAt(j) - '0' : 0;
            sb.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }
}
