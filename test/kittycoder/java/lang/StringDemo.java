package kittycoder.java.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shucheng on 2019-10-22 上午 8:20
 */
public class StringDemo {

    @Test
    public void test1() {
        String str1 = "ABC123abc321";
        String str2 = "";
        System.out.println("str1的长度为：" + str1.length());
        System.out.println("str2为空：" + str2.isEmpty());
        System.out.println("str1.charAt(0)===" + str1.charAt(0));

        // codePoint是字符在Unicode中的码位值（https://zhidao.baidu.com/question/454855508.html）
        // https://www.cnblogs.com/simple-huang/p/8643799.html
        // 获取str1中索引为0的字符，在Unicode中的码位值
        System.out.println("str1.codePointAt(0)===" + str1.codePointAt(1));

        System.out.print(str1 + "对应的char数组为：");
        System.out.println(str1.toCharArray());

        System.out.println("========================================");
        String str3 = "test3";
        String str4 = "test4";
        System.out.println(str3 + "和" + str4 + "内容是否相同：" + str3.equals(str4));
        System.out.println("test3和" + str3 + "内容是否相同：" + "test3".equals(str3));

        char[] str3charArr = new char[str3.length()];
        // 把str3里的所有字符复制到str3charArr中
        str3.getChars(0, str3.length(), str3charArr, 0);
        System.out.print("调用getChars复制到char数组后，新的char数组为：");
        System.out.println(str3charArr);

        // 获取str3所对应的byte数组
        byte[] str3byteArr = str3.getBytes();
        System.out.println(str3byteArr);

        String str5 = "中";
        // 获取String对应的byte数组，一个中文字符会被转换成3个byte
        byte[] str5byteArr = str5.getBytes();
        System.out.println(str5byteArr);
        System.out.println(new String(str5byteArr));

        System.out.println("========================================");
        System.out.println(str1 + "是以A开头：" + str1.startsWith("A"));
        System.out.println(str1 + "是以1结尾：" + str1.endsWith("1"));

        System.out.println("========================================");
        System.out.println(str1 + "中B第一次出现的位置的索引为：" + str1.indexOf('B'));
        System.out.println(str1 + "中2最后一次出现的位置的索引为：" + str1.lastIndexOf('2'));

        System.out.println("========================================");
        System.out.println("截取" + str1 + "中索引大于等于3的所有字符：" + str1.substring(3));
        System.out.println("截取" + str1 + "中索引大于等于3小于6的所有字符：" + str1.substring(3, 6));
        // subSequence实际调用的就是substring

        System.out.println("========================================");
        System.out.println(str1 + "中的字符A替换为J：" + str1.replace('A', 'J'));
        System.out.println(str1 + "中的字符串AB替换为JK：" + str1.replace("AB", "JK"));
        // 正则替换忽略大小写 https://www.cnblogs.com/BensonHe/p/6955791.html
        System.out.println(str1 + "中的字符串AB（忽略大小写）替换为KK：" + str1.replaceAll("(?i)AB", "KK"));
        System.out.println(str1 + "能够匹配正则\\w+：" + str1.matches("\\w+"));
        System.out.println(str1 + "中包含AB：" + str1.contains("AB"));

        System.out.println("========================================");
        System.out.println(str1 + "以B（忽略大小写）作为分隔符分割成数组：" + Arrays.toString(str1.split("(?i)B")));
        System.out.println(str3 + "和" + str4 + "拼接后的结果为：" + str3.concat(str4));
        String[] strArr = {"aa", "bb", "cc"};
        System.out.println("将strArr用|拼接：" + String.join("|", strArr));
        List<String> strList = new ArrayList<>();
        strList.add("s1");
        strList.add("s2");
        strList.add("s3");
        System.out.println("将strList用|拼接：" + String.join("|", strList));

        System.out.println("========================================");
        System.out.println(str1 + "所有字符转换为大写：" + str1.toUpperCase());
        System.out.println(str1 + "所有字符转换为小写：" + str1.toLowerCase());
        System.out.println("“  hehe  ”去掉空格为：" + "  hehe  ".trim());
        System.out.println(String.format("Hello，%s！你欠了%s【%d】元", "张三", "李四", 100));

        System.out.println("========================================");
        System.out.println("获取整数100对应的字符串："+ String.valueOf(100));
        char[] chArr = {'T', 'E', 'S', 'T'};
        System.out.println("获取chArr对应的字符串：" + String.valueOf(chArr));
    }
}
