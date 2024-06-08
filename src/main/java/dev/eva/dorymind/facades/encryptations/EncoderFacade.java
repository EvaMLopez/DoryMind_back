package dev.eva.dorymind.facades.encryptations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import dev.eva.dorymind.interfaces.encryptations.IEncrypt;

@Component
public class EncoderFacade implements IEncrypt {

    BCryptPasswordEncoder bCryptPasswordEncoder;

    public EncoderFacade() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    public String encode(String type, String data) {
        String dataEncrypted = "";
        if (type == "bcrypt") dataEncrypted = new BcryptEncoderFacade(bCryptPasswordEncoder).encode(data);
        if (type == "base64") dataEncrypted = new Base64EncoderFacade().encode(data);
        return dataEncrypted;
    }
    @Override
    public String decode(String type, String data) {
        String dataDecoded = "";
        if (type == "base64") dataDecoded = new Base64EncoderFacade().decode(data);
        return dataDecoded;
    }

}
    
