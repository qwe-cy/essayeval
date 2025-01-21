package com.cccy.essayeval.service.impl;

import com.cccy.essayeval.entity.User;
import com.cccy.essayeval.mapper.UserMapper;
import com.cccy.essayeval.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
