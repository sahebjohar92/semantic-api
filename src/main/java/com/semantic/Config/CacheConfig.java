//package com.semantic.Config;
//
//import com.semantic.Constants.ApiConstants;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Method;
//
////@Configuration
////@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport {
//
//    @Resource
//    private Environment env;
//
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//
//        redisConnectionFactory.setHostName(env.getRequiredProperty(ApiConstants.PROPERTY_NAME_REDIS_HOST));
//        redisConnectionFactory.setPort(Integer.valueOf(env.getRequiredProperty(ApiConstants.PROPERTY_NAME_REDIS_PORT)));
//        return redisConnectionFactory;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//
//        // Number of seconds before expiration. Defaults to unlimited (0)
//        cacheManager.setDefaultExpiration(0);
//        return cacheManager;
//    }
//
//    @Bean
//    public KeyGenerator keyGenerator() {
//        return new KeyGenerator() {
//
//            @Override
//            public Object generate(Object target, Method method, Object... params) {
//                int hashCode = 17;
//                hashCode = 31 * hashCode + target.getClass().getName().hashCode();
//                hashCode = 31 * hashCode + method.getName().hashCode();
//
//                for (Object object : params) {
//                    int jsonHashCode = object.hashCode();
//                    hashCode = 31 * hashCode + jsonHashCode;
//                }
//                return Integer.valueOf(hashCode);
//            }
//        };
//    }
//
//}