package ru.otus.project.sms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.project.sms_service.domain.SmsCode;

import java.util.Optional;

public interface SmsCodeRepository extends JpaRepository<SmsCode, Long> {
    @Modifying
    @Query("update SmsCode code set code.actual = false where code.phone = :number and code.actual = true")
    void invalidateActualFor(@Param("number") String number);

    Optional<SmsCode> findByPhoneAndValueAndActual(
        @Param("phone") String phone,
        @Param("value") String value,
        @Param("actual") boolean actual
    );
}
