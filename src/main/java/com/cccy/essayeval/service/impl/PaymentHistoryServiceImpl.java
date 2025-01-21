package com.cccy.essayeval.service.impl;

import com.cccy.essayeval.entity.PaymentHistory;
import com.cccy.essayeval.mapper.PaymentHistoryMapper;
import com.cccy.essayeval.service.IPaymentHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 付款历史记录表 服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2025-01-21
 */
@Service
public class PaymentHistoryServiceImpl extends ServiceImpl<PaymentHistoryMapper, PaymentHistory> implements IPaymentHistoryService {

}
