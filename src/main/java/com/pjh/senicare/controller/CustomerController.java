package com.pjh.senicare.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjh.senicare.dto.request.customer.PatchCustomerRequestDto;
import com.pjh.senicare.dto.request.customer.PostCustomerRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.customer.GetCustomerListResponseDto;
import com.pjh.senicare.dto.response.customer.GetCustomerResponseDto;
import com.pjh.senicare.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    
    private final CustomerService customerService;

    @PostMapping(value={"", "/"})
    public ResponseEntity<ResponseDto> postCustomer(
        @RequestBody @Valid PostCustomerRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = customerService.postCustomer(requestBody);
        return response;
    }

    @GetMapping(value={"", "/"})
    public ResponseEntity<? super GetCustomerListResponseDto> getCustomerList() {
        ResponseEntity<? super GetCustomerListResponseDto> response = customerService.getCustomerList();
        return response;
    }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer (
        @PathVariable("customerNumber") Integer customerNumber
    ) {
        ResponseEntity<? super GetCustomerResponseDto> response = customerService.getCustomer(customerNumber);
        return response;
    }

    @PatchMapping("/{customerNumber}")
    public ResponseEntity<ResponseDto> patchCustomer(
        @RequestBody @Valid PatchCustomerRequestDto requestBody,
        @PathVariable("customerNumber") Integer customerNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = customerService.patchCustomer(requestBody, customerNumber, userId);
        return response;
    }

    @DeleteMapping("/{customerNumber}")
    public ResponseEntity<ResponseDto> deleteCustomer(
        @PathVariable("customerNumber") Integer customerNumber,
        @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<ResponseDto> response = customerService.deleteCustomer(customerNumber, userId);
        return response;
    }
    
}