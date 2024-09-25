package com.pjh.senicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pjh.senicare.dto.request.customer.PostCustomerRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.customer.GetCustomerListResponseDto;
import com.pjh.senicare.dto.response.customer.GetCustomerResponseDto;
import com.pjh.senicare.entity.CustomerEntity;
import com.pjh.senicare.repository.CustomerRepository;
import com.pjh.senicare.repository.NurseRepository;
import com.pjh.senicare.repository.resultSet.GetCustomerResultSet;
import com.pjh.senicare.repository.resultSet.GetCustomersResultSet;
import com.pjh.senicare.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService {

    private final NurseRepository nurseRepository;
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<ResponseDto> postCustomer(PostCustomerRequestDto dto) {
        
        try {

            String charger = dto.getCharger();
            boolean isExistedNurse = nurseRepository.existsByUserId(charger);
            if (!isExistedNurse) return ResponseDto.noExistUserId();

            CustomerEntity customerEntity = new CustomerEntity(dto);
            customerRepository.save(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<? super GetCustomerListResponseDto> getCustomerList() {
        
        List<GetCustomersResultSet> resultSets = new ArrayList<>();

        try {
            
            resultSets = customerRepository.getCustomers();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCustomerListResponseDto.success(resultSets);

    }

    @Override
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(Integer csutomerNumber) {

        GetCustomerResultSet resultSet = null;

        try {

            resultSet = customerRepository

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCustomerResponseDto.success(resultSet);

    }
}