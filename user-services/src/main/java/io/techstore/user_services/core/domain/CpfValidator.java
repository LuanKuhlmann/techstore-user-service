package io.techstore.user_services.core.domain;

import java.util.InputMismatchException;

public class CpfValidator {

    public static boolean validate(String cpf) {
        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF tem 11 dígitos ou se é uma sequência de dígitos repetidos
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito > 9) {
                primeiroDigito = 0;
            }

            // Calcula o segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito > 9) {
                segundoDigito = 0;
            }

            // Verifica se os dígitos calculados conferem com os informados
            return (Character.getNumericValue(cpf.charAt(9)) == primeiroDigito &&
                    Character.getNumericValue(cpf.charAt(10)) == segundoDigito);

        } catch (InputMismatchException erro) {
            return false;
        }
    }

    public static String formatar(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
    }
}
