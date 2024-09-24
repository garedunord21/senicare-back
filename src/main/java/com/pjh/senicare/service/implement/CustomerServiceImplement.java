package com.pjh.senicare.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pjh.senicare.dto.request.customer.PostCustomerRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.service.CustomerService;

@Service
public class CustomerServiceImplement implements CustomerService {

    @Override
    public ResponseEntity<ResponseDto> postCustomer(PostCustomerRequestDto dto) {
        
        try {

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }
    
}
