package main.java.com.pdpc.testeC;

import java.util.ArrayList;

class Consumidor extends Thread {
    private ArrayList<Integer> estoque;
    private Object condicao;

    public Consumidor(ArrayList<Integer> estoque, Object condicao) {
        this.estoque = estoque;
        this.condicao = condicao;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) { // consumir a lista de 10 itens na mesma ordem da produção
            synchronized (condicao) {
                while (estoque.size() == 0 || estoque.get(0) != i) { // esperar até que o item esteja disponível no estoque
                    try {
                        condicao.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int item = estoque.remove(0);
                System.out.println("Consumido " + item);
                condicao.notify(); // notificar que um item foi consumido
            }
        }
    }
}