package br.com.jornadamilhas.viagens.infra;

public class StringValidation {
    public static boolean isNullEmptyOrBlank(String string)
    {
        if(string == null || string.isEmpty() || string.isBlank())
            return true;
        else
            return false;
    }
}
