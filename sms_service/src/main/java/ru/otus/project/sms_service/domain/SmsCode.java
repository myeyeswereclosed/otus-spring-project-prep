package ru.otus.project.sms_service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sms_code")
public class SmsCode {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "value")
    private String value;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "actual")
    private boolean actual = true;

    public SmsCode(String phone, String value) {
        this.phone = phone;
        this.value = value;
    }
}
