package com.sparta.matchingservice.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.matchingservice.user.dto.ResponseCustomersAdmin;
import com.sparta.matchingservice.user.dto.ResponseSellerAdmin;
import com.sparta.matchingservice.user.dto.SearchCustomersAdmin;
import com.sparta.matchingservice.user.dto.SearchSellersAdmin;
import com.sparta.matchingservice.user.entity.User;
import com.sparta.matchingservice.user.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.sparta.matchingservice.user.entity.QUser.user;

@Repository
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public UserCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression searchByUserName(String userName) {

        if(!ObjectUtils.isEmpty(userName)) {
            return user.userName.contains(userName);
        }
        return null;
    }

    private BooleanExpression searchByNickName(String nickName) {

        if(!ObjectUtils.isEmpty(nickName)) {
            return user.profile.nickName.contains(nickName);
        }
        return null;
    }

    private BooleanExpression searchByIntroduce(String introduce) {

        if(!ObjectUtils.isEmpty(introduce)) {
            return user.profile.introduce.contains(introduce);
        }
        return null;
    }

    private BooleanExpression searchByUserRole(UserRole userRole) {

        if(!ObjectUtils.isEmpty(userRole)) {
            return user.userRole.eq(userRole);
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ResponseCustomersAdmin> getCustomersByCondition(Pageable pageable, SearchCustomersAdmin searchCustomersAdmin) {
        List<User> findUsers = jpaQueryFactory.selectFrom(user)
                .where(
                        searchByUserName(searchCustomersAdmin.getUserName()),
                        searchByNickName(searchCustomersAdmin.getNickName()),
                        searchByUserRole(UserRole.USER)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(ResponseCustomersAdmin.of(findUsers), pageable, findUsers.size());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ResponseSellerAdmin> getSellersByCondition(Pageable pageable, SearchSellersAdmin searchCustomersAdmin) {
        List<User> findUsers = jpaQueryFactory.selectFrom(user)
                .where(
                        searchByUserName(searchCustomersAdmin.getUserName()),
                        searchByNickName(searchCustomersAdmin.getNickName()),
                        searchByIntroduce(searchCustomersAdmin.getIntroduce()),
                        searchByUserRole(UserRole.SELLER)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(ResponseSellerAdmin.of(findUsers), pageable, findUsers.size());
    }

}
