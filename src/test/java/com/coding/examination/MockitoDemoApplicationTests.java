package com.coding.examination;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coding.examination.service.UrlService;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class MockitoDemoApplicationTests {
	
	@Autowired
	private UrlService urlServiceTest;

	@Test
	public void testUrlService() {
		Mockito.when(urlServiceTest.generateShortenedUrl("https://www.abbcaghe.com/spring-boot-url-shortener")).thenReturn("https://www.abbcaghe.com/934W22W22429M12227Q16");
		String testName = urlServiceTest.generateShortenedUrl("https://www.abbcaghe.com/spring-boot-url-shortener");
		Assert.assertEquals("https://www.abbcaghe.com/934W22W22429M12227Q16", testName);
	}
}
