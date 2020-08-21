package projeto.pratico.security.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class Token {

    private String accessToken;

    private String tokenType;

    private String refreshToken;

    private Integer experieIn;

    private String scope;
}
