package main.java.com.pdpc.clayton.testeA;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> estoque = new ArrayList<>();
        Object condicao = new Object();

        Produtor produtor = new Produtor(estoque, condicao);
        Consumidor consumidor = new Consumidor(estoque, condicao);

        produtor.start();
        consumidor.start();
    }
}
