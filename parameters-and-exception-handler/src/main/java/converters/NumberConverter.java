package converters;

public class NumberConverter {

    public static Double convertToDouble(String strNumber) {
        if (strNumber == null){
            return 0D;
        }
        String number = strNumber.replaceAll(",",".");
        if (isNumeric(number)){
            return Double.parseDouble(number);
        }
        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null){
            return false;
        }
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    // Explicando o regex: [-+]? Verifica se o numero é positivo ou negativo
    // [0-9]* : Corresponde a zero ou mais digitos de 0-9
    // \\.? : '\\.' Corresponde ao numero decimal e a '?' torna o ponto opcional
    // [0-9]+ : Corresponde a um ou mais digitos do 0-9, onde o '+' significa q tem q ter
    // pelo menos um número
}
