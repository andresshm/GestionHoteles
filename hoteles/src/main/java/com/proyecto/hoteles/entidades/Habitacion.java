package com.proyecto.hoteles.entidades;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proyecto.hoteles.utils.TipoHabitacion;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Habitacion
 */
@Data
@Entity
public class Habitacion {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private long idHotel;
    private String numero;
    private TipoHabitacion tipo;
    private float precioNoche;

    //Este atributo deberia estar aqui ??
    //private LinkedList<Huesped> huespedes;


    @JsonIgnore //Evita recursividad
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
    @JoinColumn(name = "hotelId", nullable = true) //En name no se puede poner idHotel porque da un error
    private Hotel hotel;
  
    
    
}
