import java.util.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        /*
        Problema 1: Diseñar una función que reciba una lista de números enteros y devuelva el número que más se
        repite en la lista. Si hay más de un número con la misma frecuencia máxima, devolver el menor de ellos.
        Si la lista está vacía, devolver -1.
        Ejemplos:
        Entrada: [1, 2, 3, 4, 5]

        Salida: 1

        Entrada: [3, 3, 2, 2, 4, 4]

        Salida: 2

        Entrada: []

        Salida: -1*/

        List<Integer> list1 = new ArrayList<>();
        list1.add(3);
        list1.add(3);
        list1.add(2);
        list1.add(2);
        list1.add(5);
        list1.add(1);
        System.out.println("Resultado problema 1: " + problem1(list1));

        /*
        Problema 2: Manipulación de Cadenas Escribe una función en el lenguaje de programación de tu elección que tome
        una cadena de palabras separadas por espacios y devuelva la misma cadena pero con las palabras en orden inverso.
        Por ejemplo, si la entrada es "hola mundo", la salida debería ser "mundo hola".
         */

        String aux = "hola soy andres que tal estas jeje";
        System.out.println("Resultado problema 2: " + problem2(aux));

        /*
        Problema 3: Dada una lista de números, encuentra el par de números cuya suma sea más cercana a un número objetivo.
        Ejemplo: [2,4,6,8,10], suma de 11
         */

        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(4);
        integerList.add(6);
        integerList.add(8);
        integerList.add(10);
        integerList.add(12);
        integerList.add(14);
        System.out.println("Resultado problema 3v1: " + problem3(integerList,11));
        System.out.println("Resultado problema 3v2: " + problem3v2(integerList,11));

        /*
        Problema 4: Dada una matriz cuadrada de tamaño n x n, implementa una función en un lenguaje de programación
        de tu elección que rote la matriz 90 grados en el sentido de las agujas del reloj.
         */

        int[][] matriz = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println("Resultado problema 4: " + Arrays.deepToString(problem4(matriz)));

        /*
        Problema 5: Necesitamos que el programa nos devuelva el número de veces que aparece cada palabra en el mensaje,
        independientemente de si está en mayúsculas o minúsculas.
         */

        String frase = "gato perro perro coche Gato peRRo sol";
        System.out.println("Resultado problema 5: " + problem5(frase));
    }

    public static Integer problem1(List<Integer> listaInt){
        if(listaInt.isEmpty()){
            return -1;
        }
        Map<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0;i<listaInt.size();i++){
            if(hashMap.containsKey(listaInt.get(i))){
                int valorAsociado = hashMap.get(listaInt.get(i)) + 1;
                hashMap.put(listaInt.get(i),valorAsociado);
            }else{
                hashMap.put(listaInt.get(i),1);
            }
        }
        Integer num = null;
        Integer maxRepeat = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if(num == null){
                num = entry.getKey();
                maxRepeat = entry.getValue();
            }
            else if(entry.getValue() > maxRepeat){
                maxRepeat = entry.getValue();
                num = entry.getKey();
            }
            else if(entry.getValue() == maxRepeat && entry.getKey() < num){
                num = entry.getKey();
            }
        }
        return num;
    }

    public static String problem2(String cadena){
        String[] aux = cadena.split(" ");
        String result = "";
        for(int i=0;i<aux.length;i++){
            result += aux[aux.length-i-1] + " ";
        }
        return result;
    }

    public static List<Integer> problem3(List<Integer> list,int objective){
        int result = 999;
        List<Integer> aux = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.size();j++){
                if(i!=j && Math.abs(objective-list.get(i)-list.get(j))<Math.abs(objective-result)){
                    aux.clear();
                    result = list.get(i)+list.get(j);
                    aux.add(list.get(i));
                    aux.add(list.get(j));
                }
            }
        }
        return aux;
    }

    public static List<Integer> problem3v2(List<Integer> list, int objective) {
        // Ordena la lista de números.
        //list.sort();

        // Inicializa el par de números más cercano como los dos primeros números.
        int closestPairSum = list.get(0) + list.get(1);
        int closestPairIndex1 = 0;
        int closestPairIndex2 = 1;

        // Itera sobre los números en la lista.
        for (int i = 2; i < list.size(); i++) {
            // Calcula la suma del par de números actual.
            int currentPairSum = list.get(i) + list.get(0);

            // Si la suma del par de números actual es más cercana al objetivo que la suma del par de números más cercano,
            // actualiza el par de números más cercano.
            if (Math.abs(currentPairSum - objective) < Math.abs(closestPairSum - objective)) {
                closestPairSum = currentPairSum;
                closestPairIndex1 = 0;
                closestPairIndex2 = i;
            } else if (Math.abs(currentPairSum - objective) == Math.abs(closestPairSum - objective)) {
                // Si la suma del par de números actual es igual a la suma del par de números más cercano,
                // actualiza el par de números más cercano si el índice del primer número es menor.
                if (list.get(i) < list.get(closestPairIndex1)) {
                    closestPairSum = currentPairSum;
                    closestPairIndex1 = i;
                    closestPairIndex2 = 0;
                }
            }
        }

        // Devuelve el par de números más cercano.
        List<Integer> result = new ArrayList<>();
        result.add(list.get(closestPairIndex1));
        result.add(list.get(closestPairIndex2));
        return result;
    }

    public static int[][] problem4(int[][] matriz){
        int dimension = matriz.length;
        int[][] result = new int[dimension][dimension];
        dimension = matriz.length-1;
        for(int fila=0;fila<=dimension;fila++){
            for(int columna=0;columna<=dimension;columna++){
                result[columna][dimension-fila]=matriz[fila][columna];
            }
        }
        return result;
    }

    public static String problem5(String frase){
        StringBuilder result = new StringBuilder();
        String[] aux = frase.split(" ");
        Map<String,Integer> map = new LinkedHashMap<>();
        for (String s : aux) {
            s = s.toLowerCase();
            map.put(s,map.getOrDefault(s,0)+1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.append(entry.getKey()).append(entry.getValue());
        }
        return result.toString();
    }
}

























