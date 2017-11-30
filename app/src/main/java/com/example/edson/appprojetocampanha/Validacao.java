package com.example.edson.appprojetocampanha;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.InputMismatchException;

/**
 * Created by Edson on 07/11/2017.
 */

public class Validacao {

    public static boolean isCPF(String CPF) {
// considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;


        try {
        // Calculo do 1 Digito Verificador
            sm = 0;
            peso = 10;
            // converte string em int
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2 Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

    public static boolean validaTelefone(String numeroTelefone) {
        /*return (!validaCampoVazio(numeroTelefone) && numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}")
                || numeroTelefone.matches(".((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}"));*/

        return !validaCampoVazio(numeroTelefone);
    }

    public static boolean validaCampoVazio(String valor) {
        return (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
    }

    public static boolean validaEmail(String email){
        return (!validaCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean validaCPF(String CPF){
        return (!validaCampoVazio(CPF) && isCPF(CPF));
    }

    public static boolean validaSenha(String senha){
        return (!validaCampoVazio(senha) && senha.length() >= 6);
    }
}
