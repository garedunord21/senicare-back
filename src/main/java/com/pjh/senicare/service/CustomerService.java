package com.pjh.senicare.service;

import org.springframework.http.ResponseEntity;

import com.pjh.senicare.dto.request.customer.PostCustomerRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;

public interface CustomerService {
    
    ResponseEntity<ResponseDto> postCustomer(PostCustomerRequestDto dto);

}