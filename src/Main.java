import java.util.*;

public class Main {

    public static void main(String[] args)  {

        Main calculate = new Main();
        /*Ввод с клавиатуры*/
        Scanner input = new Scanner(System.in);
        String algebraicExpression = input.nextLine();

        /*Вывод результата*/
        String result = calculate.calc(algebraicExpression); // Вызов калькулятора
        System.out.println(result); // Вывод результата
        input.close();

    }

    /*Логика калькулятора*/
    private static String calc(String input) {
        String expression = input;
        Romanian convertRomanian = new Romanian();
        int a = 0;
        int b = 0;
        String[] singsArr = {"+", "-", "/", "*"};
        /*Ввод данных с клавиатуры*/
        String[] numbers = expression.split(" ");
        /*Проверка на Неверное выражение */
        try {
            if (numbers.length > 3) throw new Exception ();
        } catch (Exception exception) {
            return "Неверное выражение";
        }
        try {
            if (!Arrays.asList(singsArr).contains(numbers[1])) throw new Exception();
        }catch (Exception exception) {
            return "Неверный арифметический знак";
        }
        /*Проверка на Числа из разных систем*/
        try{
            if (
                    convertRomanian.isRoman(numbers[0]) != convertRomanian.isRoman(numbers[2])
            ) throw new Exception();
        } catch (Exception exception) {
            return "Числа из разных систем";
        }
        /*Проверка на римские цифры*/
        if (convertRomanian.isRoman(numbers[0]) && convertRomanian.isRoman(numbers[2])){
            a = convertRomanian.romanInInt(numbers[0]);
            b = convertRomanian.romanInInt(numbers[2]);
            String sign = numbers[1];
            /*Проверка на Числа не из того диапазона*/
            try {
                if ((a < 1 | a > 10) | (b < 1 | b > 10)) throw new Exception();
            } catch (Exception exception) {
                return "Числа не из того диапазона";
            }
            if (sign.equals("+")) {
                String result = convertRomanian.intInRoma(a + b);
                return result;
            }
            if (sign.equals("-")) {
                try{
                    if ( a - b < 0) throw new Exception();
                } catch (Exception exception){
                    return "Римские числа не могут быть меньше нуля";
                }
                String result = convertRomanian.intInRoma(a - b);
                return result;
            }
            if (sign.equals("*")) {
                String result = convertRomanian.intInRoma(a * b);
                return result;
            }
            if (sign.equals("/")) {
                String result = convertRomanian.intInRoma(a / b);
                return result;
            }

        }else {
            a = Integer.parseInt(numbers[0]);
            b = Integer.parseInt(numbers[2]);
            String sign = numbers[1];
            /*Проверка на Числа не из того диапазона*/
            try {
                if ((a < 1 | a > 10) | (b < 1 | b > 10)) throw new Exception();
            } catch (Exception exception) {
                return "Числа не из того диапазона";
            }
            if (sign.equals("+")) {
                String result = Integer.toString(a + b);
                return result;
            }
            if (sign.equals("-")) {
                String result = Integer.toString(a - b);
                return result;
            }
            if (sign.equals("*")) {
                String result = Integer.toString(a * b);
                return result;
            }
            if (sign.equals("/")) {
                String result = Integer.toString(a / b);
                return result;
            }
        }

        return " ";
    }

}

class Romanian {
    TreeMap<Character, Integer> myMap = new TreeMap<>();
    {
        myMap.put('I', 1);
        myMap.put('V', 5);
        myMap.put('X', 10);
        myMap.put('L', 50);
        myMap.put('C', 100);
        myMap.put('D', 500);
        myMap.put('M', 1000);

    }
    TreeMap<Integer, Character> myMapRevers = new TreeMap<>();
    {
        myMapRevers.put(1, 'I');
        myMapRevers.put(5, 'V');
        myMapRevers.put(10, 'X');
        myMapRevers.put(50, 'L');
        myMapRevers.put(100, 'C');
        myMapRevers.put(500, 'D');
        myMapRevers.put(1000, 'M');
    }
    /*Арабские в римские*/
    public int romanInInt(String input){
        int result = 0;
        int previous = 0;
        for (int i = input.length() - 1;  i>=0; i--){
            int current = myMap.get(input.charAt(i));
            if (current < previous){
                result -= current;
            } else{
                result += current;
            }
            previous = current;
        }
        return result;
    }
    /*Римские в арабские*/
    public String intInRoma(Integer input){
        String result = "";
        int numberKey;
        do{
            numberKey = myMapRevers.floorKey(input);
            result += myMapRevers.get(numberKey);
            input -= numberKey;
        } while (input!=0);
        return result;
    }
    public boolean isRoman(String input){
        return myMap.containsKey(input.charAt(0));
    }
}





