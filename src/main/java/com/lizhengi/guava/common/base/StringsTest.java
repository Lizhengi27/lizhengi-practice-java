package com.lizhengi.guava.common.base;

import com.google.common.base.Strings;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;

/**
 * @author lizhengi
 * @version 1.0.0
 **/
public class StringsTest {

    /**
     * 测试 Strings.nullToEmpty() 方法
     *
     * @description 测试 Strings.nullToEmpty() 方法，将 null 转换为空字符串，保证不会出现 null 值
     * @testpoint 当传入 null 时，期望返回空字符串；当传入空字符串时，期望返回空字符串；当传入非空字符串时，期望返回原字符串
     */
    @Test
    public void testNullToEmpty() {
        // 定义空字符串和非空字符串
        String emptyString = "";
        String nonEmptyString = "Hello, MCopilot!";

        // 当传入 null 时，期望返回空字符串
        assertEquals("Expect empty string", emptyString, Strings.nullToEmpty(null));
        // 当传入空字符串时，期望返回空字符串
        assertEquals("Expect empty string", emptyString, Strings.nullToEmpty(emptyString));
        // 当传入非空字符串时，期望返回原字符串
        assertEquals("Expect non-empty string", nonEmptyString, Strings.nullToEmpty(nonEmptyString));
    }

    /**
     * 测试 Strings.emptyToNull() 方法
     *
     * @description 测试 Strings.emptyToNull() 方法，将空字符串转换为 null，保证不会出现空字符串
     * @testpoint 当传入空字符串时，期望返回 null；当传入非空字符串时，期望返回原字符串
     */
    @Test
    public void testEmptyToNull() {
        // 定义空字符串和非空字符串
        String emptyString = "";
        String nonEmptyString = "Hello, MCopilot!";

        // 当传入空字符串时，期望返回 null
        assertNull("Expect null", Strings.emptyToNull(emptyString));
        // 当传入非空字符串时，期望返回原字符串
        assertEquals("Expect non-empty string", nonEmptyString, Strings.emptyToNull(nonEmptyString));
    }

    /**
     * 测试 Strings.isNullOrEmpty() 方法
     *
     * @description 测试 Strings.isNullOrEmpty() 方法，判断字符串是否为 null 或空字符串
     * @testpoint 当传入 null 时，期望返回 true；当传入空字符串时，期望返回 true；当传入非空字符串时，期望返回 false；当传入仅包含空格的字符串时，期望返回 false
     */
    @Test
    public void testIsNullOrEmpty() {
        // 测试 null 字符串
        assertTrue(Strings.isNullOrEmpty(null));

        // 测试空字符串
        assertTrue(Strings.isNullOrEmpty(""));

        // 测试非空字符串
        assertFalse(Strings.isNullOrEmpty("hello"));

        // 测试仅包含空格的字符串
        assertFalse(Strings.isNullOrEmpty(" "));
    }

    /**
     * 测试 Strings.padStart() 方法
     *
     * @description 测试 Strings.padStart() 方法，将字符串填充到指定长度，填充字符在字符串前面
     * @testpoint 当字符串长度小于指定长度时，期望在字符串前面填充指定字符，直到达到指定长度；当字符串长度等于或大于指定长度时，期望返回原字符串
     */
    @Test
    public void testPadStart() {
        // 测试字符串长度小于指定长度
        assertEquals("007", Strings.padStart("7", 3, '0'));
        assertEquals("2010", Strings.padStart("2010", 3, '0'));
        assertEquals("00123", Strings.padStart("123", 5, '0'));

        // 测试字符串长度等于或大于指定长度
        assertEquals("123", Strings.padStart("123", 2, '0'));
    }

    /**
     * 测试 Strings.padEnd() 方法
     *
     * @description 测试 Strings.padEnd() 方法，将字符串填充到指定长度，填充字符在字符串后面
     * @testpoint 当字符串长度小于指定长度时，期望在字符串后面填充指定字符，直到达到指定长度；当字符串长度等于或大于指定长度时，期望返回原字符串
     */
    @Test
    public void testPadEnd() {
        // 测试字符串长度小于指定长度
        assertEquals("4.000", Strings.padEnd("4.", 5, '0'));
        assertEquals("2010", Strings.padEnd("2010", 3, '!'));
        assertEquals("12300", Strings.padEnd("123", 5, '0'));

        // 测试字符串长度等于或大于指定长度
        assertEquals("123", Strings.padEnd("123", 2, '0'));
    }

    /**
     * 测试 Strings.repeat() 方法
     *
     * @description 测试 Strings.repeat() 方法，将字符串重复指定次数
     * @testpoint 当传入空字符串时，期望返回空字符串；当传入非空字符串时，期望返回重复指定次数后的字符串
     */
    @Test
    public void testRepeat() {
        // 测试空字符串
        assertEquals("", Strings.repeat("", 0));
        assertEquals("", Strings.repeat("abc", 0));

        // 测试非空字符串
        assertEquals("abc", Strings.repeat("abc", 1));
        assertEquals("abcabc", Strings.repeat("abc", 2));
        assertEquals("abcabcabc", Strings.repeat("abc", 3));
        assertEquals("aaaaaa", Strings.repeat("a", 6));

        // 测试异常情况
        assertThrows(IllegalArgumentException.class, () -> Strings.repeat("abc", -1));
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> Strings.repeat("a", Integer.MAX_VALUE));
    }

    /**
     * 测试 Strings.commonPrefix() 方法，获取两个字符串的最长公共前缀
     * @testpoint 当两个字符串完全相同时，期望返回其中任意一个字符串；当两个字符串没有公共前缀时，期望返回空字符串；当两个字符串有公共前缀时，期望返回公共前缀
     */
    @Test
    public void testCommonPrefix() {
        // 两个字符串完全相同时
        assertEquals("", Strings.commonSuffix("", ""));
        assertEquals("abc", Strings.commonSuffix("abc", "abc"));

        // 两个字符串没有公共前缀时
        assertEquals("", Strings.commonSuffix("abc", "def"));

        // 两个字符串有公共前缀时
        assertEquals("ab", Strings.commonSuffix("abc", "abd"));
        assertEquals("a\uD834\uDF06b", Strings.commonSuffix("a\uD834\uDF06bc", "a\uD834\uDF06bd"));
    }

    /**
     * 测试 Strings.commonSuffix() 方法，获取两个字符串的最长公共后缀
     * @testpoint 当两个字符串完全相同时，期望返回其中任意一个字符串；当两个字符串没有公共后缀时，期望返回空字符串；当两个字符串有公共后缀时，期望返回公共后缀
     */
    @Test
    public void testCommonSuffix() {
        // 两个字符串完全相同时
        assertEquals("abcdef", Strings.commonSuffix("abcdef", "abcdef"));

        // 两个字符串没有公共后缀时
        assertEquals("", Strings.commonSuffix("abcdef", "123456"));

        // 两个字符串有公共后缀时
        assertEquals("abc", Strings.commonSuffix("123abc", "456abc"));
        assertEquals("uvwxyz", Strings.commonSuffix("abcdefghijklmnopqrstuvwxyz", "uvwxyz"));
        assertEquals("bc", Strings.commonSuffix("abc", "bc"));

        // 一个字符串为空字符串时
        assertEquals("", Strings.commonSuffix("abc", ""));
        assertEquals("", Strings.commonSuffix("", "abc"));
        assertEquals("", Strings.commonSuffix("", ""));
    }

    /**
     * 测试 Strings.lenientFormat() 方法
     *
     * @description 测试 Strings.lenientFormat() 方法，使用占位符将参数格式化为字符串
     * @testpoint 当模板字符串中的占位符数量等于参数数量时，期望返回格式化后的字符串；当占位符数量少于参数数量时，期望返回模板字符串中的所有占位符都被替换后的字符串；当占位符数量多于参数数量时，期望返回模板字符串中未被替换的占位符和参数的字符串表示；当参数为 null 时，期望返回字符串 "null"；当模板字符串为 null 时，期望返回字符串 "null"
     */
    @Test
    public void testLenientFormat() {
        // 测试正常情况
        assertEquals("Hello, World!", Strings.lenientFormat("Hello, %s!", "World"));

        // 测试多个占位符
        assertEquals("1 + 2 = 3", Strings.lenientFormat("%s + %s = %s", 1, 2, 3));

        // 测试占位符数量少于参数数量
        assertEquals("A, B [C, D]", Strings.lenientFormat("%s, %s", "A", "B", "C", "D"));

        // 测试占位符数量多于参数数量
        assertEquals("A, %s, %s", Strings.lenientFormat("%s, %s, %s", "A"));

        // 测试参数为null
        assertEquals("null", Strings.lenientFormat("%s", (Object) null));

        // 测试模板为null
        assertEquals("null", Strings.lenientFormat(null));
    }

}
