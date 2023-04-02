package main.java.com.pdpc.clayton.testeB;

import java.util.ArrayList;

class Consumidor extends Thread {
    private ArrayList<Integer> estoque;
    private Object condicao;

    public Consumidor(ArrayList<Integer> estoque, Object condicao) {
        this.estoque = estoque;
        this.condicao = condicao;
    }

    public void run() {
        while (true) {
            synchronized (condicao) {
                while (estoque.size() == 0) { // esperar até que um item esteja disponível no estoque
                    try {
                        condicao.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int quantidade = (int) (Math.random() * estoque.size()) + 1; // consumir uma quantidade aleatória de itens
                System.out.println("Consumindo " + quantidade + " itens");
                for (int i = 0; i < quantidade; i++) {
                    int item = estoque.remove(0);
                    System.out.println("Consumido " + item);
                }
                condicao.notify(); // notificar que itens foram consumidos
            }
            try {
                sleep((int) (Math.random() * 200)); // esperar um tempo aleatório antes de consumir novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}