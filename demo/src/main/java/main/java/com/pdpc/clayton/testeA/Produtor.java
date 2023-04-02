package main.java.com.pdpc.clayton.testeA;

import java.util.ArrayList;

class Produtor extends Thread {
    private ArrayList<String> estoque;
    private Object condicao;

    public Produtor(ArrayList<String> estoque, Object condicao) {
        this.estoque = estoque;
        this.condicao = condicao;
    }

    public void run() {
        for (int i = 0; i < 5; i++) { // produzir 5 itens
            String item = "Item " + i;
            synchronized (condicao) {
                estoque.add(item);
                System.out.println("Produzido " + item);
                condicao.notify(); // notificar que há um novo item no estoque
            }
        }
    }
}
