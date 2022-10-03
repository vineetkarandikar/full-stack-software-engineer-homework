package com.coding.examination.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {

	@Value("${redis.host}")
	private String host;

	@Value("${redis.port}")
	private int port;

	@Value("${redis.database}")
	private int database;

	@Value("${redis.password}")
	private String password;

	@Value("${redis.user}")
	private String userName;

	@Bean
	public RedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(host, port);
		standaloneConfig.setUsername(userName);
		standaloneConfig.setPassword(password);
		standaloneConfig.setDatabase(database);
		return new JedisConnectionFactory(standaloneConfig);
	}

	@Bean
	RedisTemplate<String, String> redisTemplate() {
		final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}