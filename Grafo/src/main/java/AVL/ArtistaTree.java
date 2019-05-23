package AVL;

import repository.Artista;

public class ArtistaTree extends Tree<Artista> {
	private int qtd;
	
	@Override
	public void inserir(Artista data) {
		super.inserir(data);
		qtd++;
	}
	
    boolean buscar(String artista, Node<Artista> raiz){
        int comp=artista.compareTo(raiz.data.name);
        if(comp==0){
            return true;
        }else if(comp<0){
            if(raiz.left==null){
                return false;
            }
            return buscar(artista, raiz.left);
        }else{
            if(raiz.right==null){
                return false;
            }
            return buscar(artista, raiz.right);
        }
    }
    public boolean busca(String artista) {
    	if(this.raiz!=null) {
    		return this.buscar(artista, this.raiz);
    	}else {
    		return false;
    	}
    }
    public int getQtdArtistas() {
    	return qtd;
    }
}
