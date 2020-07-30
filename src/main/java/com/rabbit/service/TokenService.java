package com.rabbit.service;

import com.rabbit.dto.LoginUser;
import com.rabbit.dto.Token;

/**
 * Token管理器<br>
 * 可存储到redis或者数据库<br>
 * 具体可看实现类<br>
 * 为了简单环境搭建，实现了数据库方式实现
 *
 */
public interface TokenService {

	Token saveToken(LoginUser loginUser);

	void refresh(LoginUser loginUser);

	LoginUser getLoginUser(String token);

	boolean deleteToken(String token);

}
