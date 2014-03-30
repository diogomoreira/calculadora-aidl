package com.acme.calculadora.cliente;

import android.app.Activity;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.acme.calculadora.ICalculadora;

/**
 * 
 * @author diogo.moreira
 *
 */
public class CalculadoraActivity extends Activity {
	
	private ICalculadora calculadora;
	private ServiceConnection servico;
	
	public class CalculadoraServiceConnection implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			calculadora = ICalculadora.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			calculadora = null;
		}
	}
	
	private EditText primeiroTermo;
	private EditText segundoTermo;
	private TextView resultado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculadora);
		recuperarComponentes();
		conectar();
	}

	private void recuperarComponentes() {
		this.primeiroTermo = (EditText) findViewById(R.id.primeiroTermoEditText);
		this.segundoTermo = (EditText) findViewById(R.id.segundoTermoEditText);
		this.resultado = (TextView) findViewById(R.id.resultadoTextView);
	}
	
	public void botaoSomarClickHandler(View view) {
		if (primeiroTermo.getText().toString().isEmpty()
				|| segundoTermo.getText().toString().isEmpty()) {
			Toast.makeText(CalculadoraActivity.this, "Insira dois números antes de somar!",
					Toast.LENGTH_LONG).show();
			return;
		}
		
		double primeiroValor = Float.valueOf(primeiroTermo.getText().toString());
		double segundoValor = Float.valueOf(segundoTermo.getText().toString());
		
		
		
		try {
			double valorResultado = calculadora.soma(11d, 10d);
		} catch(RemoteException e) {
			// Problema ao chamar método do serviço
		}
		
		
		
		
	}

	private void conectar() {
		servico = new CalculadoraServiceConnection();
		bindService(new Intent("com.acme.calculadora.CALCULADORA"), servico,
				Context.BIND_AUTO_CREATE);
	}
	
	private void desconectar() {
		unbindService(servico);
		servico = null;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		desconectar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.calculadora, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_calculadora,
					container, false);
			return rootView;
		}
	}

}
