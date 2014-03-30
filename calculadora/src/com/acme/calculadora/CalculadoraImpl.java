package com.acme.calculadora;

import android.os.RemoteException;

/**
 * 
 * @author diogo.moreira
 *
 */
public class CalculadoraImpl extends ICalculadora.Stub {

	@Override
	public double soma(double numero1, double numero2) throws RemoteException {
		return numero1 + numero2;
	}

	@Override
	public double multiplicar(double numero1, double numero2)
			throws RemoteException {
		return numero1 * numero2;
	}

}
