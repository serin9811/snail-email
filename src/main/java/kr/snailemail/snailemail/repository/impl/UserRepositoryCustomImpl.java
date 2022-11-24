package kr.snailemail.snailemail.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.snailemail.snailemail.model.entity.QUser;
import kr.snailemail.snailemail.model.entity.User;
import kr.snailemail.snailemail.repository.UserRepositoryCustom;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    UserRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    QUser qUser = QUser.user;

    @Override
    public User findByEmailAddr(String emailAddr) {
        return queryFactory.selectFrom(qUser)
                .where(qUser.emailAddr.eq(emailAddr))
                .fetchOne();
    }
}
