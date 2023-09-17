package com.utn.tpPersistencia.entidades;

import com.utn.tpPersistencia.enumeraciones.Tipo;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto extends BaseEntidad{

    private int tiempoEstimadoCocina;
    private String denominacion;
    private double precioVta;
    private double precioCompra;
    private int stockActual;
    private int stockMin;
    private String unidadMedida;
    private String receta;
    private Tipo tipo;
}
