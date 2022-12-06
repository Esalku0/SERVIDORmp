package MiniProyecto;


import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException, ClassNotFoundException {


		InetSocketAddress direccion = new InetSocketAddress("localhost", 5555);
		Socket socket = new Socket();
		socket.connect(direccion);
		/*
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Error");
			return;
		}
			Socket conexion = socketEscucha.accept();
*/
	
		
		
		System.out.println("CLIENTE >>> RECIBE OBJETO CONTRSEÑA VACIO");

		ObjectInputStream inObjeto = new ObjectInputStream(socket.getInputStream());
		
		Contrasenya p = (Contrasenya) inObjeto.readObject(); 
		System.out.println("CLIENTE >> Recibo del servidor: "+ p.getTextoPlano() + " - " + 
		p.getContrasenyaEncriptada()); 
		
		

		System.out.println("PREPARAMOS EL ENVIO DEL OBJETO CONTRASEÑA CON LA CONTRASEÑA CARGADA");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Dime tu contraseña manin: ");
		String contrString = scanner.next();
		p.setTextoPlano(contrString);
		
		ObjectOutputStream outObjeto = new ObjectOutputStream(socket.getOutputStream());
		outObjeto.writeObject(p); 
		System.out.println("Cliente >> Envio a Servidor: " + p.getTextoPlano() + " - " + p.getContrasenyaEncriptada()); 

		System.out.println("RECIBIMOS LA CONTRASEÑA ENCRIPTADA");
		Contrasenya cont2 = (Contrasenya) inObjeto.readObject();
		System.err.println(
				"SERVIDOR >> Recibo de cliente: " + cont2.getTextoPlano() + " - " 
		+ cont2.getContrasenyaEncriptada());

		System.out.println("CLIENTE >>> Recibe resultado: ");
		
		scanner.close();
		inObjeto.close(); 
		outObjeto.close(); 
		socket.close(); 
		
		

		

	}
}
