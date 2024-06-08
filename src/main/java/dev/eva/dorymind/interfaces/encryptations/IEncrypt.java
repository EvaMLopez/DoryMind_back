package dev.eva.dorymind.interfaces.encryptations;

public interface IEncrypt { 

    String encode(String type, String data);
    String decode(String type, String data);
    
}
