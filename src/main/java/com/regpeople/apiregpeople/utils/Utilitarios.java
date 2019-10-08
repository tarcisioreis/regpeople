package com.regpeople.apiregpeople.utils;

import javax.swing.text.MaskFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitarios {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Utilitarios() { super(); }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isValidCPF(String cpf) {
        if ((cpf==null) || (cpf.length()!=11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    public static String DateFormatToString(Date receivedDate) {
        String strDate = null;

        try {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            strDate = df.format(receivedDate).toString();
        }catch (Exception e) {
            return null;
        }

        return strDate;
    }

    public static String StringToDateFormat(String receivedDate) {
        String strDate = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(receivedDate);

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            strDate = df.format(date).toString();
        }catch (Exception e) {
            return null;
        }

        return strDate;
    }

    public static Date StringToDate(String receivedDate) {
        Date date = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.parse(receivedDate);
        }catch (Exception e) {
            return date;
        }

        return date;
    }

    public static String StringToDateByDataBank(String receivedDate) {
        String novaData = null;

        try {
            String data = receivedDate.replaceAll("/", "-");
            String[] str = data.split("-");
            novaData = str[2]+"-"+str[1]+"-"+str[0];
        }catch (Exception e) {
            return novaData;
        }

        return novaData;
    }

    public static String somenteDigitos(String cpf) {
        return cpf.replaceAll( "\\D*", "" );
    }

    public static String formatarString(String texto, String mascara) throws ParseException {
        MaskFormatter mf = new MaskFormatter(mascara);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(texto);
    }

    public static boolean isValidEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

}
