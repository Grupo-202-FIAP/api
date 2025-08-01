package com.postech.fastfood.core.utils;

public class FormatCpf {

    private static final int CPF_LENGTH = 11;
    private static final int PART1_END = 3;
    private static final int PART2_END = 6;
    private static final int PART3_END = 9;

    public static String formatCpfToEntity(String cpf) {
        if (cpf == null) {
            return null;
        }

        return cpf.replaceAll("[^\\d]", "");
    }

    public static String formatCpfToResponse(String cpf) {
        if (cpf == null || cpf.length() != CPF_LENGTH) {
            return cpf;
        }
        return cpf.substring(0, PART1_END)
                + "."
                + cpf.substring(PART1_END, PART2_END)
                + "."
                + cpf.substring(PART2_END, PART3_END)
                + "-"
                + cpf.substring(PART3_END, CPF_LENGTH);
    }
}
