package com.mins5.util;

import junit.framework.Assert;

import org.junit.Test;

import com.mins5.share.util.MyStringUtils;

/**
 * @author mins5
 * @since 2014-2-27
 */
public class StringUtilsTest {

	@Test
	public void testIsBlank() {
		String str = null;
		boolean isBlank = MyStringUtils.isBlank(str);
		Assert.assertTrue(isBlank);

		str = "";
		isBlank = MyStringUtils.isBlank(str);
		Assert.assertTrue(isBlank);

		str = "  ";
		isBlank = MyStringUtils.isBlank(str);
		Assert.assertTrue(isBlank);

		str = "  abc  ";
		isBlank = MyStringUtils.isBlank(str);
		Assert.assertFalse(isBlank);
	}

	@Test
	public void testTrimBlank() {
		String str = null;
		String actual = MyStringUtils.trimBlank(str);
		Assert.assertNull(actual);

		str = "";
		actual = MyStringUtils.trimBlank(str);
		Assert.assertEquals("", actual);

		str = "  ";
		actual = MyStringUtils.trimBlank(str);
		Assert.assertEquals("", actual);

		str = "  abc  ";
		actual = MyStringUtils.trimBlank(str);
		Assert.assertEquals("abc", actual);
	}

	@Test
	public void testParseNull() {
		String str = null;
		String actual = MyStringUtils.parseNull(str);
		Assert.assertNull(actual);

		str = "";
		actual = MyStringUtils.parseNull(str);
		Assert.assertNull(actual);

		str = "  ";
		actual = MyStringUtils.parseNull(str);
		Assert.assertNull(actual);

		str = "  abc  ";
		actual = MyStringUtils.parseNull(str);
		Assert.assertEquals("abc", actual);
	}

	@Test
	public void testParseEmpty() {
		String str = null;
		String actual = MyStringUtils.parseEmpty(str);
		Assert.assertEquals("", actual);

		str = "";
		actual = MyStringUtils.parseEmpty(str);
		Assert.assertEquals("", actual);

		str = "  ";
		actual = MyStringUtils.parseEmpty(str);
		Assert.assertEquals("", actual);

		str = "  abc  ";
		actual = MyStringUtils.parseEmpty(str);
		Assert.assertEquals("abc", actual);
	}
}
