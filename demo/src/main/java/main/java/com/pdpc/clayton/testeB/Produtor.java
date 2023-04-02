package main.java.com.pdpc.clayton.testeB;

import java.util.ArrayList;

class Produtor extends Thread {
    private ArrayList<Integer> estoque;
    private Object condicao;

    public Produtor(ArrayList<Integer> estoque, Object condicao) {
        this.estoque = estoque;
        this.condicao = condicao;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) { // produzir 10 itens
            synchronized (condicao) {
                estoque.add(i);
                System.out.println("Produzido " + i);
                condicao.notify(); // notificar que há um novo item no estoque
            }
            try {
                sleep((int) (Math.random() * 200)); // esperar um tempo aleatório antes de produzir o próximo item
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}