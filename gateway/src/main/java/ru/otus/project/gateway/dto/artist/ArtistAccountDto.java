package ru.otus.project.gateway.dto.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.otus.project.gateway.dto.security.TokenResponseDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistAccountDto {
    private long id;
    // access data
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String expires_in;
    private String scope;
    // personal data
    private String name;
    private String genre;
    private String phone;
    private String email;

    public static ArtistAccountDto fromTokenAndPersonalData(TokenResponseDto tokenData, ArtistDto personalData) {
       return
           new ArtistAccountDto(
               personalData.getId(),
               tokenData.getAccess_token(),
               tokenData.getToken_type(),
               tokenData.getRefresh_token(),
               tokenData.getExpires_in(),
               tokenData.getScope(),
               personalData.getName(),
               personalData.getGenre(),
               personalData.getEmail(),
               personalData.getPhone()
           );
    }
}
