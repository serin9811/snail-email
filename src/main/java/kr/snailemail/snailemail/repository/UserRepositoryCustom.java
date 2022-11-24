package kr.snailemail.snailemail.repository;

import kr.snailemail.snailemail.model.entity.User;

public interface UserRepositoryCustom {

    User findByEmailAddr(String emailAddr);

}
