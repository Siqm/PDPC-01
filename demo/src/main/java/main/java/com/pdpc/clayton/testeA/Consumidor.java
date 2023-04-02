package main.java.com.pdpc.clayton.testeA;

import java.util.ArrayList;

class Consumidor extends Thread {
    private ArrayList<String> estoque;
    private Object condicao;

    public Consumidor(ArrayList<String> estoque, Object condicao) {
        this.estoque = estoque;
        this.condicao = condicao;
    }

    public void run() {
        for (int i = 0; i < 5; i++) { // consumir 5 itens
            synchronized (condicao) {
                while (estoque.size() == 0) { // esperar até que haja um item no estoque
                    try {
                        condicao.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String item = estoque.remove(0);
                System.out.println("Consumido " + item);
            }
        }
    }
}