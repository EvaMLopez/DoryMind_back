package dev.eva.dorymind.facades.encryptations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderFacade {

    BCryptPasswordEncoder encoder;

    public BcryptEncoderFacade(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }
    public String encode(String data) {
        String dataEncoded = encoder.encode(data);
        return dataEncoded;
    }
    
}
