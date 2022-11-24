package kr.snailemail.snailemail.repository;

import kr.snailemail.snailemail.model.entity.Email;
import java.util.List;

public interface EmailRepositoryCustom {
    List<Email> findByUserEmail(String userEmail);
}
