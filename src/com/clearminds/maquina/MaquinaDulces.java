package com.clearminds.maquina;

import java.util.ArrayList;

import com.clearminds.componentes.Celda;
import com.clearminds.componentes.Producto;

public class MaquinaDulces {

	private ArrayList<Celda> celdas;
	private double saldo;

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public MaquinaDulces() {
		celdas = new ArrayList<>();
		saldo = 0.0;
	}

	public void agregarCelda(Celda celda) {
		celdas.add(celda);
	}

	public void mostrarConfiguracion() {
		System.out.println("Configuración actual de la máquina:");
		for (Celda celda : celdas) {
			System.out.println("Celda " + celda.getCodigo());
		}
	}

	public Celda buscarCelda(String codigo) {
		for (Celda celda : celdas) {
			if (celda.getCodigo().equals(codigo)) {
				return celda;
			}
		}
		return null;
	}

	public void cargarProducto(Producto producto, String codigoCelda, int cantidad) {
		Celda celda = buscarCelda(codigoCelda);

		if (celda != null) {
			for (int i = 0; i < cantidad; i++) {
				celda.ingresarProducto(producto, i);
			}
		} else {
			System.out.println("Error: No se pudo cargar el producto. Celda no encontrada.");
		}
	}

	public void mostrarProductos() {
		for (Celda celda : celdas) {
			System.out.println("Código de Celda: " + celda.getCodigo());
			System.out.println("Stock: " + celda.getStock());

			Producto producto = celda.getProducto();
			if (producto != null) {
				System.out.println("Nombre del Producto: " + producto.getNombre());
				System.out.println("Precio del Producto: " + producto.getPrecio());
			} else {
				System.out.println("La celda no contiene un producto.");
			}

			System.out.println("--------------------------");
		}
	}

	public Producto buscarProductoEnCelda(String codigoCelda) {
		Celda celdaEncontrada = buscarCelda(codigoCelda);
		return (celdaEncontrada != null) ? celdaEncontrada.getProducto() : null;
	}

	public double consultarPrecio(String codigoCelda) {
		Celda celda = buscarCelda(codigoCelda);
		return (celda != null) ? celda.getProducto().getPrecio() : -1;
	}

	public Celda buscarCeldaProducto(String codigoProducto) {
		for (Celda celda : celdas) {
			Producto producto = celda.getProducto();
			if (producto != null && producto.getCodigo().equals(codigoProducto)) {
				return celda;
			}
		}
		return null;
	}

	public void incrementarProductos(String codigoProducto, int cantidadIncrementar) {
		Celda celdaEncontrada = buscarCeldaProducto(codigoProducto);
		if (celdaEncontrada != null) {
			celdaEncontrada.incrementarStock(cantidadIncrementar);
		}
	}

	public void vender(String codigoCelda) {
		Celda celda = buscarCelda(codigoCelda);
		if (celda != null && celda.getStock() > 0) {
			double precio = celda.getProducto().getPrecio();
			saldo += precio;
			celda.disminuirStock();
			mostrarProductos();
			System.out.println("Saldo actual: $" + saldo);
		} else {
			System.out.println("Error: Producto no disponible en la celda.");
		}
	}

	public double venderConCambio(String codigoCelda, double valorIngresado) {
		Celda celda = buscarCelda(codigoCelda);
		if (celda != null && celda.getStock() > 0) {
			double precio = celda.getProducto().getPrecio();
			if (valorIngresado >= precio) {
				double cambio = valorIngresado - precio;
				saldo += precio;
				celda.disminuirStock();
				mostrarProductos();
				System.out.println("Saldo actual: $" + saldo);
				return cambio;
			} else {
				System.out.println("Error: Dinero insuficiente.");
			}
		} else {
			System.out.println("Error: Producto no disponible en la celda.");
		}
		return -1;
	}

	public ArrayList<Producto> buscarMenores(double limite) {
		ArrayList<Producto> productosMenores = new ArrayList<>();
		for (Celda celda : celdas) {
			Producto producto = celda.getProducto();
			if (producto != null && producto.getPrecio() <= limite) {
				productosMenores.add(producto);
			}
		}
		return productosMenores;
	}
}
