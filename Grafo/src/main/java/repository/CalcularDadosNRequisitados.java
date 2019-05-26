package repository;

import java.io.IOException;

public class CalcularDadosNRequisitados {

	public static void main(String[] args) throws IOException {
		GerarDataSet dataset = new GerarDataSet(6);
		
		dataset.gerarDataSetNRequisitados();
	}

}
