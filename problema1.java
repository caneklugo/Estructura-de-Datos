package Actividad_3;

public class problema1 {
    public static int fibonacci(int n) {
        if (n <= 1) {
            return n; // Caso base
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // Caso recursivo
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci de " + n + " es: " + fibonacci(n));
    }
}

