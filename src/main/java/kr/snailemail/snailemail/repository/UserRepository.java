package kr.snailemail.snailemail.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import kr.snailemail.snailemail.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

}
