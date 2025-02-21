package com.belisa.delivery.aop;

import com.belisa.delivery.dto.ProductDto;
import com.belisa.delivery.entity.ProductEntity;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductServiceAspect {

    @AfterReturning(pointcut = "execution(* com.example.delivery.service.impl.ProductServiceImpl.updateProductStatusToInTransit(..))")
    public void logStatusChangeInTransit() {
        System.out.println("Products changed status");
    }

    @AfterReturning(pointcut = "execution(* com.example.delivery.service.impl.ProductServiceImpl.markProductAsDelivered(..))", returning = "productDto")
    public void logStatusChangeAsDelivered(ProductDto productDto) {
        System.out.println("Products " + productDto.getName() + "changed status " + productDto.getStatus());
    }
}