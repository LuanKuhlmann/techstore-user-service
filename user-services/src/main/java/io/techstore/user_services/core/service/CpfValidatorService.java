package io.techstore.user_services.core.service;

import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CpfValidatorService {

    public boolean isValid(String cpf) {
        if (StringUtils.isBlank(cpf)) {
            return false;
        }

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        // Cálculo do primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit >= 10) {
            firstDigit = 0;
        }

        // Cálculo do segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += (11 - i) * Character.getNumericValue(cpf.charAt(i));
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit >= 10) {
            secondDigit = 0;
        }

        return Character.getNumericValue(cpf.charAt(9)) == firstDigit &&
                Character.getNumericValue(cpf.charAt(10)) == secondDigit;
    }
}
