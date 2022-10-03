package com.coding.examination.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.coding.examination.common.DemoApplicationException;

@Service
public class UrlService {

	private static final Logger logger = LoggerFactory.getLogger(UrlService.class);

	@Value("${shortened.url.limit}")
	private int ShortenedUrlLimit;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private static String possibleCombination = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	/**
	 * 
	 * @param String
	 * @return String This method get value for key from Redis.
	 */
	public String getValueByKey(@NonNull String key) {
		return redisTemplate.opsForValue().get(key);
	}

	/**
	 * 
	 * @param String
	 * @return String This method performs url validation and extract the base url.
	 */
	private String vaildateURL(String url) {
		Pattern pattern = Pattern.compile("^(?:http:\\/\\/|www\\.|https:\\/\\/)([^\\/]+)");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			return matcher.group(0);
		} else {
			throw new DemoApplicationException(HttpStatus.BAD_REQUEST, "Invalid URL");
		}
	}

	/**
	 * 
	 * @return String This method generates random alphanumeric code which is not
	 *         present till now.
	 */
	protected String getRandomAphanumericString() {
		StringBuilder randomString = new StringBuilder();
		Random rnd = new Random();
		int generatorCount = 0;
		while (generatorCount < ShortenedUrlLimit) {
			int index = (int) (rnd.nextFloat() * possibleCombination.length());
			randomString.append(possibleCombination.charAt(index));
			randomString.append(index);
			generatorCount++;
		}
		// make sure the short link isn't already used
		if (getValueByKey(randomString.toString()) != null) {
			getRandomAphanumericString();
		}
		return randomString.toString();
	}

	/**
	 * @param String
	 * @return String This method generates generate Shortened Url.
	 */
	public String generateShortenedUrl(@NonNull String url) {
		logger.info("Generation of Shortened Url Started  for " + url);
		StringBuilder generatedUrl = new StringBuilder(vaildateURL(url));
		String shortLink = getRandomAphanumericString();
		if (getValueByKey(url) == null) {
			generatedUrl.append("/");
			generatedUrl.append(shortLink);
			setKeyValue(url, generatedUrl.toString());
			setKeyValue(shortLink, shortLink);
			logger.info("Generation of Shortened Url completed  for " + url + " with value as " + generatedUrl);
			return generatedUrl.toString();
		} else {
			logger.info("Shortened url alreday exists for " + url);
			return getValueByKey(url);
		}

	}

	/**
	 * 
	 * @param key
	 * @param url This method save key for a given value in redis.
	 */
	private void setKeyValue(@NonNull String key, @NonNull String url) {
		redisTemplate.opsForValue().set(key, url);
	}

}
