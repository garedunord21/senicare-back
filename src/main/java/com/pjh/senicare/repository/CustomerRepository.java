package com.pjh.senicare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pjh.senicare.entity.CustomerEntity;
import com.pjh.senicare.repository.resultSet.GetCustomerResultSet;
import com.pjh.senicare.repository.resultSet.GetCustomersResultSet;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    
    CustomerEntity findByCustomerNumber(Integer customerNumber);

    @Query(
    value = 
        "SELECT " +
        "    C.custom_number as customerNumber, " +
        "    C.name as name, " +
        "    C.birth as birth, " +
        "    C.location as location, " + 
        "    N.name as chargerName, " +
        "    N.user_id as chargerId " +
        "FROM customers C LEFT JOIN nurses N " +
        "ON C.charger = N.user_id " +
        "ORDER BY C.custom_number DESC ",
    nativeQuery=true
    )
    List<GetCustomersResultSet> getCustomers();

    @Query(
    value=
        "SELECT " +
        "C.custom_number as customerNumber, " +
        "C.profile_image as profileImage, " +
        "C.name as name,  " +
        "C.birth as birth, " + 
        "N.name as chargerName, " +
        "N.user_id as chargerId, " +
        "C.address as address  " +
    "FROM customers C LEFT JOIN nurses N  " +
    "ON C.charger = N.user_id  " +
    "WHERE C.custom_number = :customerNumber  ",
    nativeQuery = true
    )
    GetCustomerResultSet getCustomer(@Param("customerNumber") Integer customerNumber);

}