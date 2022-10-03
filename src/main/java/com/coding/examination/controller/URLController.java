package com.coding.examination.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coding.examination.service.UrlService;

@CrossOrigin(origins = "*")
@RestController
public class URLController {

	private static final Logger logger = LoggerFactory.getLogger(URLController.class);

	@Autowired
	private UrlService urlService;

	/**
	 * 
	 * @param Url
	 * @return ResponseEntity<String> If response status is OK (200), then means a
	 *         shortened URL is generated successfully
	 */
	@PostMapping("/v1/shortner/url")
	public ResponseEntity<String> generate(@RequestBody @NonNull String url) {
		logger.info("Shortened Url request recieved for URL => " + url);
		String shortnedURL = urlService.generateShortenedUrl(url);
		logger.info("Shortened Url for URL => " + shortnedURL);
		return ResponseEntity.ok(shortnedURL);
	}

}
