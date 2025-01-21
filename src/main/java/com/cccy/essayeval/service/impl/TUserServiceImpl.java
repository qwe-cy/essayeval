package com.cccy.essayeval.service.impl;

import com.cccy.essayeval.entity.TUser;
import com.cccy.essayeval.mapper.TUserMapper;
import com.cccy.essayeval.service.ITUserService;
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
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
