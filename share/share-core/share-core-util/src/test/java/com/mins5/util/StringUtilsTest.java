package com.mins5.util;

import junit.framework.Assert;

import org.junit.Test;

import com.mins5.share.util.StringUtils;

/**
 * @author mins5
 * @since 2014-2-27
 */
public class StringUtilsTest {

	@Test
    public void testIsBlank() {
		String str= null;
		boolean isBlank = StringUtils.isBlank(str);
		Assert.assertTrue(isBlank);
		
		str = "";
		isBlank = StringUtils.isBlank(str);
		Assert.assertTrue(isBlank);
		
		str = "  ";
		isBlank = StringUtils.isBlank(str);
		Assert.assertTrue(isBlank);
		
		str = "  abc  ";
		isBlank = StringUtils.isBlank(str);
		Assert.assertFalse(isBlank);
    }
	
    @Test
    public void testTrimBlank() {
    	String str= null;
		String actual = StringUtils.trimBlank(str);
		Assert.assertNull(actual);
		
		str = "";
		actual = StringUtils.trimBlank(str);
		Assert.assertEquals("", actual);
		
		str = "  ";
		actual = StringUtils.trimBlank(str);
		Assert.assertEquals("", actual);
		
		str = "  abc  ";
		actual = StringUtils.trimBlank(str);
		Assert.assertEquals("abc", actual);
    }
    
    @Test
	public void testParseNull() {
    	String str= null;
		String actual = StringUtils.parseNull(str);
		Assert.assertNull(actual);
		
		str = "";
		actual = StringUtils.parseNull(str);
		Assert.assertNull(actual);
		
		str = "  ";
		actual = StringUtils.parseNull(str);
		Assert.assertNull(actual);
		
		str = "  abc  ";
		actual = StringUtils.parseNull(str);
		Assert.assertEquals("abc", actual);
	}
	
    @Test
	public void testParseEmpty() {
    	String str= null;
		String actual = StringUtils.parseEmpty(str);
		Assert.assertEquals("", actual);
		
		str = "";
		actual = StringUtils.parseEmpty(str);
		Assert.assertEquals("", actual);
		
		str = "  ";
		actual = StringUtils.parseEmpty(str);
		Assert.assertEquals("", actual);
		
		str = "  abc  ";
		actual = StringUtils.parseEmpty(str);
		Assert.assertEquals("abc", actual);
	}
}
