package com.clearminds.test;

import java.util.ArrayList;

import com.clearminds.componentes.Celda;
import com.clearminds.componentes.Producto;
import com.clearminds.maquina.MaquinaDulces;

public class TestBuscarMenores {

	public static void main(String[] args) {
		MaquinaDulces maquina = new MaquinaDulces();
        maquina.agregarCelda(new Celda("A1", new Producto("P1", "Papitas", 0.85)));
        maquina.agregarCelda(new Celda("A2", new Producto("P2", "Doritos", 0.5)));
        maquina.agregarCelda(new Celda("B1", new Producto("P3", "JET", 0.25)));
        maquina.agregarCelda(new Celda("B2", new Producto("P4", "DeTodito", 0.60)));

        double limite = 1.00;
        ArrayList<Producto> productosEncontrados = maquina.buscarMenores(limite);

        System.out.println("Productos cuyo precio es menor o igual a $" + limite);
        for (Producto producto : productosEncontrados) {
            System.out.println(producto.getNombre() + " - Precio: $" + producto.getPrecio());
        }
    }

}
