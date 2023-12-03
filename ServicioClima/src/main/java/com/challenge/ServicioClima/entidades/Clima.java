package com.challenge.ServicioClima.entidades;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "climas")
@Data
public class Clima {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreCiudad;

    private String descripcionGeneral;

    private String descripcionDia;

    private String descripcionNoche;

    private String temperaturaMaxima;

    private String temperaturaMinima;

	private Date fecha;
	
	public Clima(){
		
	}
	public Clima(String nombreCiudad, Date fecha, String descripcionGeneral, String descripcionDia, String descripcionNoche, String temperaturaMaxima,
			String temperaturaMinima) {
		super();
		this.fecha = fecha;
		this.nombreCiudad = nombreCiudad;
		this.descripcionGeneral = descripcionGeneral;
		this.descripcionDia = descripcionDia;
		this.descripcionNoche = descripcionNoche;
		this.temperaturaMaxima = temperaturaMaxima;
		this.temperaturaMinima = temperaturaMinima;
	}

     public Integer getId(){
        return id;
     }
     public String getNombreCiudad(){
        return nombreCiudad;
     }

     public String getDescripcionGeneral() {
		return descripcionGeneral;
	}

	public void setDescripcionGeneral(String descripcionGeneral) {
		this.descripcionGeneral = descripcionGeneral;
	}

	public String getDescripcionDia() {
		return descripcionDia;
	}

	public void setDescripcionDia(String descripcionDia) {
		this.descripcionDia = descripcionDia;
	}

	public String getDescripcionNoche() {
		return descripcionNoche;
	}

	public void setDescripcionNoche(String descripcionNoche) {
		this.descripcionNoche = descripcionNoche;
	}

	public String getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(String temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public String getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(String temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public Date getFecha(){
		return this.fecha;
	}

	public void setFecha(Date fecha){
		this.fecha = fecha;
	}
}
