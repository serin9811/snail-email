package kr.snailemail.snailemail.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.snailemail.snailemail.model.entity.Email;
import kr.snailemail.snailemail.model.entity.QEmail;
import kr.snailemail.snailemail.repository.EmailRepositoryCustom;

import java.util.List;

public class EmailRepositoryCustomImpl implements EmailRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    EmailRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    QEmail qEmail = QEmail.email;

    @Override
    public List<Email> findByUserEmail(String userEmail) {
        return queryFactory.select(qEmail)
                .where(qEmail.user.emailAddr.eq(userEmail))
                .fetch();
    }
}
