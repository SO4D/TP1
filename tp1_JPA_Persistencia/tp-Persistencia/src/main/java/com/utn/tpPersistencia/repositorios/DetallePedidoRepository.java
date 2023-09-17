package com.utn.tpPersistencia.repositorios;

import com.utn.tpPersistencia.entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Long> {
}
