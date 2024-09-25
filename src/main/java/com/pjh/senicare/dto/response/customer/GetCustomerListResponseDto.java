package com.pjh.senicare.dto.response.customer;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pjh.senicare.common.object.Customer;
import com.pjh.senicare.dto.response.ResponseCode;
import com.pjh.senicare.dto.response.ResponseDto;
import com.pjh.senicare.dto.response.ResponseMessage;
import com.pjh.senicare.repository.resultSet.GetCustomerResultSet;

import lombok.Getter;

@Getter
public class GetCustomerListResponseDto extends ResponseDto {

    private List<Customer> customers;

    private GetCustomerListResponseDto (List<GetCustomerResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.customers = Customer.getList(resultSets);
    }

    public static ResponseEntity<GetCustomerListResponseDto> success(List<GetCustomerResultSet> resultSets) {
        GetCustomerListResponseDto responseBody = new GetCustomerListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}