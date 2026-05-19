package com.pagos.microservicio.service;

import com.pagos.microservicio.model.Pago;
import com.pagos.microservicio.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    //Método para procesar y simular que se realiza el pago
    public Pago procesarPago(Pago pago) {
        //Simulamos que el banco procesa la tarjeta de forma exitosa
        pago.setEstado("APROBADO");

        //Guardamos el registro en la base de datos
        return pagoRepository.save(pago);
    }

    //Método para ver el historial de pagos realizados
    public List<Pago> obtenerTodosLosPagos() {
        return pagoRepository.findAll();
    }
}