package Actividad_3;

public class problema2 {
    public static boolean subsetSum(int[] arr, int n, int target) {
        if (target == 0) return true; 
        }
        if (n == 0 && target != 0) return false; 
    }

        // Excluir o incluir el último elemento
        return subsetSum(arr, n - 1, target) || subsetSum(arr, n - 1, target - arr[n - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("¿Existe subconjunto con suma " + target + "? " + subsetSum(arr, arr.length, target));
    }
}

