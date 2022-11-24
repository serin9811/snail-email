package kr.snailemail.snailemail.repository;

import kr.snailemail.snailemail.model.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long>, EmailRepositoryCustom {

}
