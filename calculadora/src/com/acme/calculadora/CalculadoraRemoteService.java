package com.acme.calculadora;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * 
 * @author diogo.moreira
 *
 */
public class CalculadoraRemoteService extends Service {
	
	@Override
	public IBinder onBind(Intent intent) {
		return new CalculadoraImpl();
	}

}
