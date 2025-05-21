package com.postech.fastfood.core.utils;

public class FormatCpf {
    public static String formatCpfToEntity(String cpf) {
        if (cpf == null) {
            return null;
        }
         
        return cpf.replaceAll("[^\\d]", "");
    }

    public static String formatCpfToResponse(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return cpf;
        }
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

}
