package ru.otus.project.gateway.dto.phone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCodeDto {
    private String phone;
    private String code;
}
