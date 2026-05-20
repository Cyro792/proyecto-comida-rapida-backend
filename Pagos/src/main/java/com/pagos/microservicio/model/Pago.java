package com.pagos.microservicio.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pagos")
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ordenId;       // Para saber qué orden de comida se está pagando
    private Double monto;       // El total de la el monto
    private String metodoPago;  // Efectivo, Tarjeta, Paypal
    private String estado;      // Pendiente, Aprobado, Rechazado
}