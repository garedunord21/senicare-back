package com.pjh.senicare.service.implement;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pjh.senicare.dto.request.customer.PatchCustomerRequestDto;
import com.pjh.senicare.dto.request.customer.PostCareRecordRequestDto;
import com.pjh.senicare.dto.request.customer.PostCustomerRequestDto;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.customer.GetCustomerListResponseDto;
import com.pjh.senicare.dto.response.customer.GetCustomerResponseDto;
import com.pjh.senicare.entity.CareRecordEntity;
import com.pjh.senicare.entity.CustomerEntity;
import com.pjh.senicare.entity.ToolEntity;
import com.pjh.senicare.repository.CareRecordRepository;
import com.pjh.senicare.repository.CustomerRepository;
import com.pjh.senicare.repository.NurseRepository;
import com.pjh.senicare.repository.ToolRepository;
import com.pjh.senicare.repository.resultSet.GetCustomerResultSet;
import com.pjh.senicare.repository.resultSet.GetCustomersResultSet;
import com.pjh.senicare.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplement implements CustomerService {

    private final ToolRepository toolRepository;
    private final NurseRepository nurseRepository;
    private final CustomerRepository customerRepository;
    private final CareRecordRepository careRecordRepository;

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
    public ResponseEntity<? super GetCustomerResponseDto> getCustomer(Integer customerNumber) {

        GetCustomerResultSet resultSet = null;

        try {

            resultSet = customerRepository.getCustomer(customerNumber);
            if (resultSet == null) return ResponseDto.noExistCustomer();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetCustomerResponseDto.success(resultSet);

    }

    @Override
    public ResponseEntity<ResponseDto> patchCustomer(
        PatchCustomerRequestDto dto, 
        Integer customerNumber, 
        String userId
    ) {
        
        try {

            CustomerEntity customerEntity = customerRepository.findByCustomerNumber(customerNumber);
            if(customerEntity == null) return ResponseDto.noExistCustomer();

            String charger = customerEntity.getCharger();
            boolean isCharger = charger.equals(userId);
            if (!isCharger) return ResponseDto.noPermission();

            customerEntity.patch(dto);
            customerRepository.save(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    
        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> deleteCustomer(Integer customerNumber, String userId) {

        try {

        CustomerEntity customerEntity = customerRepository.findByCustomerNumber(customerNumber);
        if (customerEntity == null) return ResponseDto.noExistCustomer();

        String charger = customerEntity.getCharger();
        boolean isCharger = charger.equals(userId);
        if (!isCharger) return ResponseDto.noPermission();

        careRecordRepository.deleteByCustomerNumber(customerNumber);
        customerRepository.delete(customerEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();

    }

    @Override
    public ResponseEntity<ResponseDto> postCareRecord(
        PostCareRecordRequestDto dto, 
        Integer customerNumber,
        String userId
    ) {

        try {

            ToolEntity toolEntity = null;
            String usedToolName = null;

            Integer usedToolNumber = dto.getUsedToolNumber();
            Integer usedCount = dto.getCount();

            if (usedToolNumber != null) {
                toolEntity = toolRepository.findByToolNumber(usedToolNumber);
                if (toolEntity == null) return ResponseDto.noExistTool();

                Integer count = toolEntity.getCount();
                if (usedCount > count) return ResponseDto.ToolInsufficient();

                usedToolName = toolEntity.getName();
            }

            CareRecordEntity careRecordEntity = new CareRecordEntity(dto, usedToolName, userId, customerNumber);
            careRecordRepository.save(careRecordEntity);

            if (usedToolNumber != null) {
                toolEntity.decreaseCount(usedCount);
                toolRepository.save(toolEntity);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return ResponseDto.success();
        
    }

}