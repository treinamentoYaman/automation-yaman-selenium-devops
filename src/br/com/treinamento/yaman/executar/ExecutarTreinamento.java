package br.com.treinamento.yaman.executar;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import br.com.treinamento.yaman.suits.SuiteDeTestes;

public class ExecutarTreinamento {

	public static void main(String[] args){
		
		Result resultadoDasExecuoes = JUnitCore.runClasses(SuiteDeTestes.class);
		
	}
	
}
