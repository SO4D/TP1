package com.utn.tpPersistencia;

import com.utn.tpPersistencia.entidades.*;
import com.utn.tpPersistencia.enumeraciones.Estado;
import com.utn.tpPersistencia.enumeraciones.FormaPago;
import com.utn.tpPersistencia.enumeraciones.Tipo;
import com.utn.tpPersistencia.enumeraciones.TipoEnvio;
import com.utn.tpPersistencia.repositorios.ClienteRepository;
import com.utn.tpPersistencia.repositorios.ProductoRepository;
import com.utn.tpPersistencia.repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class TpPersistenciaApplication {

	@Autowired
	RubroRepository rubroRepository;
	@Autowired
	ClienteRepository clienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpPersistenciaApplication.class, args);
		System.out.println("Cargando");
	}

	@Bean
	CommandLineRunner init(RubroRepository rubroRepository1, ClienteRepository clienteRepository1){
		return args -> {
			System.out.println("----------------Cargando---------------------");
			Rubro rubro1 = Rubro.builder()
					.denominacion("Hamburguesas")
					.build();
			Producto producto1 = Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("Hamburguesa con Papas")
					.precioVta(2000)
					.precioCompra(1200)
					.stockActual(50)
					.stockMin(3)
					.unidadMedida("unidad1")
					.receta("receta1")
					.tipo(Tipo.Insumo)
					.build();
			Producto producto2 = Producto.builder()
					.tiempoEstimadoCocina(60)
					.denominacion("Hamburguesa con Queso")
					.precioVta(2200)
					.precioCompra(1500)
					.stockActual(32)
					.stockMin(4)
					.unidadMedida("unidad2")
					.receta("receta2")
					.tipo(Tipo.Insumo)
					.build();
			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);
			rubroRepository.save(rubro1);
			Cliente cliente1 = Cliente.builder()
					.nombre("Gaspar")
					.apellido("Coria")
					.mail("@mail")
					.telefono("telefono1")
					.build();
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Ozamis")
					.numero(1582)
					.localidad("Maipu")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Maza")
					.numero(3433)
					.localidad("Gutierrez")
					.build();
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(4000)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2200)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(6000)
					.build();
			SimpleDateFormat formatoFecha = new SimpleDateFormat ("yyyy-MM-dd");
			String fechaString = "2023-09-16";
			Date fecha = formatoFecha.parse(fechaString);
			Pedido pedido1 = Pedido.builder()
					.fecha(fecha)
					.total(6200)
					.estado(Estado.Entregado)
					.tipoEnvio(TipoEnvio.Delivery)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha(fecha)
					.total(6000)
					.estado(Estado.Entregado)
					.tipoEnvio(TipoEnvio.Delivery)
					.build();
			//Crear instancias de factura
			Factura factura1 = Factura.builder()
					.fecha(fecha)
					.total(5800)
					.numero(1)
					.dto(400)
					.formaPago(FormaPago.Efectivo)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(fecha)
					.total(5400)
					.numero(2)
					.dto(600)
					.formaPago(FormaPago.Efectivo)
					.build();
			pedido1.agregarDetallePedido(detallePedido1);
			pedido1.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);
			cliente1.agregarPedido(pedido1);
			cliente1.agregarPedido(pedido2);
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);
			clienteRepository.save(cliente1);
			Rubro rubroRecuperado = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubroRecuperado != null){
				System.out.println("Denominacion: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProductos();
			}
			Cliente clienteRecuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (clienteRecuperado != null){
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Mail: " + clienteRecuperado.getMail());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("----------------------------------------");
				clienteRecuperado.mostrarDomicilios();
				clienteRecuperado.mostrarPedidos();

			}
		};
	}

}
