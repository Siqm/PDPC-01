package main.java.com.pdpc.testeC;

public class Estoque {
	private int conteudo;
	private boolean disponivel = false;

	public synchronized void get(String cons, int consumido) {
    	while(!disponivel) {
    		try {
    			wait();
    		} catch (InterruptedException e) {}
    	}
    	
        if(this.conteudo == 0) {
        	disponivel = false;
        	return;
        }
        
        System.out.format("%s consumiu: %d%n", cons, consumido);
        this.conteudo -= consumido;
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

	public int getConteudo() {
		return conteudo;
	}
	
	
}
