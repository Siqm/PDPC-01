package main.java.com.pdpc.clayton.testeA;

public class Estoque {
	private int conteudo;
	private boolean disponivel = false;

	public synchronized void get(String cons) {
    	while(!disponivel) {
    		try {
    			wait();
    		} catch (InterruptedException e) {}
    	}
    	
        disponivel = false;
        System.out.format("%s consumiu: %d%n", cons, conteudo);
        notifyAll();
    }

	public synchronized void put(String prod, int valor) {
		while(disponivel) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		conteudo = valor;
		disponivel = true;
		System.out.format("%s produziu: %d%n", prod, conteudo);
		notifyAll();
	}
}
