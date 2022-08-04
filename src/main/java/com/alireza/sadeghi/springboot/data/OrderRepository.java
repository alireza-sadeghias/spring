package com.alireza.sadeghi.springboot.data;

import com.alireza.sadeghi.springboot.domain.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
