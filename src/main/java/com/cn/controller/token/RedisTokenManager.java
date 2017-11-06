package com.cn.controller.token;

import com.cn.model.Customer;
import com.cn.model.User;
import com.cn.util.SerializeUtil;
import com.cn.util.redis.RedisClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by home on 2017/10/25.
 */
@Component("tokenManager")
public class RedisTokenManager implements  TokenManager {

    /**
     * Redis中Token的前缀
     */
    private static final String REDIS_TOKEN_PREFIX = "AUTHORIZATION_TOKEN_";

    //protected int tokenExpireSeconds = 7 * 24 * 3600;
   // protected int tokenExpireSeconds = 10;

    @Autowired
    private RedisClientTemplate redisClientTemplate;

    public TokenModel createToken(Customer customer) {
        //使用uuid作为源token
        String token = createTokenId();
        TokenModel tokenModel= new TokenModel(customer, token);
        //存储到redis并设置过期时间
        redisClientTemplate.set(SerializeUtil.serialize(REDIS_TOKEN_PREFIX+ token),SerializeUtil.serialize(tokenModel));
        redisClientTemplate.expire(SerializeUtil.serialize(REDIS_TOKEN_PREFIX+token),tokenExpireSeconds );
        //redisClientTemplate.set(REDIS_TOKEN_PREFIX+user.getId(),token);
        //redisClientTemplate.expire(REDIS_TOKEN_PREFIX+user.getId(),tokenExpireSeconds );

        return tokenModel;
    }

    public TokenModel  getToken(String token) {
        if ( token== null || token.length() == 0) {
            return null;
        }
        TokenModel tokenModel=null;
        //使用userId和源token简单拼接成的token，可以增加加密措施
        try{
            byte[] value=redisClientTemplate.get(SerializeUtil.serialize(REDIS_TOKEN_PREFIX + token));
           if(value.length!=0) {
               tokenModel = (TokenModel) SerializeUtil.unserizlize(value);
           }
        }catch (Exception e){
            return null;
        }
          return tokenModel;
    }

   /* public boolean checkToken(TokenModel model) {
        if (model == null) {
            return false;
        }
        String token = redis.boundValueOps(model.getUserId()).get();
        if (token == null || !token.equals(model.getToken())) {
            return false;
        }
        //如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        redis.boundValueOps(model.getUserId()).expire(Constants.TOKEN_EXPIRES_HOUR, TimeUnit.HOURS);
        return true;
    }*/

    @Override
    public boolean checkToken(String token) {
        if (token == null||"".equals(token)) {
            return false;
        }

        try{
            byte[] value=redisClientTemplate.get(SerializeUtil.serialize(REDIS_TOKEN_PREFIX + token));
            if(value.length!=0) {
                redisClientTemplate.expire(SerializeUtil.serialize(REDIS_TOKEN_PREFIX+token),tokenExpireSeconds );
              return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }

    }

    public void deleteToken(String  token) {
        try {

            redisClientTemplate.del(SerializeUtil.serialize(REDIS_TOKEN_PREFIX + token));
        }
        catch (Exception e){

        }
    }

    @Override
    public String createTokenId() {
        //单机可用uuid，
        return UUID.randomUUID().toString().replace("-", "");
    }
}
