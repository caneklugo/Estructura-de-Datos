package actividad2;

import java.util.Scanner;

class Nodo<T>{
    T d;
    Nodo<T> s;
    Nodo(T d){
        this.d=d;
    }
}
class Pila<T>{
    Nodo<T> c;
    void push(T d){
        Nodo<T> n=new Nodo<>(d);
        n.s=c;
        c=n;
    }T pop(){
        if(c==null)return null;
        T d=c.d;
        c=c.s;
        return d;
    }T peek(){
        return c==null?null:c.d;
    }
}
class Cola<T>{
    Nodo<T> f,e;
    void enqueue(T d){
        Nodo<T> n=new Nodo<>(d);
        if(e!=null)e.s=n;
        e=n;if(f==null)f=n;
    }T dequeue(){
        if(f==null)return null;
        T d=f.d;
        f=f.s;
        if(f==null)e=null;
        return d;
    }T peek(){
        return f==null?null:f.d;
    }
}

public class Main{
    public static void main(String[]a){
        Scanner sc=new Scanner(System.in);
        Pila<String> pila=new Pila<>();
        Cola<String> cola=new Cola<>();
        int o;
        do{
            System.out.print("\n1.Agregar paquete\n2.Entregar paquete\n3.Ver paquete\n4.Agregar canción\n5.Reproducir canción\n6.Ver canción\n0.Salir\nOpción: ");
            o=sc.nextInt();
            sc.nextLine();
            switch(o){
                case 1->pila.push(sc.nextLine());
                case 2->System.out.println("Entregado: "+(pila.pop()==null?"ninguno":pila.pop()));
                case 3->System.out.println("Ultimo: "+(pila.peek()==null?"ninguno":pila.peek()));
                case 4->cola.enqueue(sc.nextLine());
                case 5->System.out.println("Reproduciendo: "+(cola.dequeue()==null?"ninguna":cola.dequeue()));
                case 6->System.out.println("Primera: "+(cola.peek()==null?"ninguna":cola.peek()));
            }
        }while(o!=0);
    }
}
