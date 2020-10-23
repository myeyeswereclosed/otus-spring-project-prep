package ru.otus.project.prep.repository.sms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.project.prep.domain.sms.SmsCode;

public interface SmsCodeRepository extends JpaRepository<SmsCode, Long> {

    @Modifying
    @Query("update SmsCode code set code.actual = false where code.phone = :number and code.actual = true")
    int invalidateFor(@Param("number") String number);
}
