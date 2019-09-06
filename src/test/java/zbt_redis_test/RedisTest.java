/**   
 * Copyright © 2019 公司名. 八维董事长.
 * 
 * @Title: RedisTest.java 
 * @Prject: zbt_redis_test
 * @Package: zbt_redis_test 
 * @Description: TODO
 * @author: 11209   
 * @date: 2019年9月6日 上午9:59:23 
 * @version: V1.0   
 */
package zbt_redis_test;

import java.io.Serializable;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zhangbotang.common.utils.RandomUtil;
import com.zhangbotang.common.utils.StringUtil;
import com.zhangbotang.domain.User;

/** 
 * @ClassName: RedisTest 
 * @Description: TODO
 * @author: 11209
 * @date: 2019年9月6日 上午9:59:23  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class RedisTest {

	//注入redis模板
	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Test
	public void redisTest() {
		long startTime = System.currentTimeMillis();
		for (int i = 1; i < 100001; i++) {
			User user = new User();
			user.setId(i);
			user.setName(StringUtil.randomChineseString(3));
			user.setSex(StringUtil.randomSex());
			user.setPhone(StringUtil.randomPhone());
			user.setEmail(StringUtil.randomEmail());
			user.setBirthday(RandomUtil.random(18,70));
////			(3)使用RedisTemplate保存上述模拟的十万个user对象到Redis。（4分）
			redisTemplate.opsForHash().put("user", "user"+i, user.toString());
//			System.out.println(user);
		}
		long endTime = System.currentTimeMillis();
//		(4)保存完成后，输出系列化方式、保存数量、所耗时间三项数据，并将这三项数据复制到记事本中。（4分）
		System.out.println("Hash类型保存:储存["+100000+"]个user用：["+(endTime-startTime)+"毫秒秒]");
		
	}
}
