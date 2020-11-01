package ru.otus.project.sms_service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sms_code")
@Accessors(chain = true)
public class SmsCode {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phone")
    private @NonNull String phone;

    @Column(name = "value")
    private @NonNull String value;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "actual")
    private boolean actual = true;

    public SmsCode(String phone, String value) {
        this.phone = phone;
        this.value = value;
    }

    public SmsCode(String phone, String value, LocalDateTime expiresAt) {
        this(phone, value);
        this.expiresAt = expiresAt;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt);
    }
}
