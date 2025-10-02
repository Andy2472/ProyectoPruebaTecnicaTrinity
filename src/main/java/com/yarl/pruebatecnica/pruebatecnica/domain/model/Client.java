package com.yarl.pruebatecnica.pruebatecnica.domain.model;


import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Client {
    private Long id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    private List<Account> productos;

    public Client(Long id, String tipoIdentificacion, String numeroIdentificacio, String nombres, String apellidos, String correo, LocalDate fechaNacimiento, LocalDate fechaCreacion, LocalDate fechaModificacion, List<Account> productos) {

        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("El cliente debe ser mayor de edad");
        }

        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacio;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.productos = productos;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<Account> getProductos() {
        return productos;
    }

    public void setProductos(List<Account> productos) {
        this.productos = productos;
    }

    public void actualizarDatos(String nombres, String apellidos, String correo, String tipoIdentificacion, String numeroIdentificacion) {
        if (nombres.length() < 2 || apellidos.length() < 2) {
            throw new IllegalArgumentException("Nombre y apellido deben tener al menos 2 caracteres");
        }
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.fechaModificacion = LocalDate.now();
    }
}
