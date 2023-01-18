package com.sparta.matchingservice.user.repository;

import com.sparta.matchingservice.user.dto.ResponseCustomersAdmin;
import com.sparta.matchingservice.user.dto.ResponseSellerAdmin;
import com.sparta.matchingservice.user.dto.SearchCustomersAdmin;
import com.sparta.matchingservice.user.dto.SearchSellersAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {

    Page<ResponseCustomersAdmin> getCustomersByCondition(Pageable pageable, SearchCustomersAdmin searchCustomersAdmin);

    Page<ResponseSellerAdmin> getSellersByCondition(Pageable pageable, SearchSellersAdmin searchSellersAdmin);

}
