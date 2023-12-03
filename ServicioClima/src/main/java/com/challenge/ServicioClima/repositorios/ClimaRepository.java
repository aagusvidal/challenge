package com.challenge.ServicioClima.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.ServicioClima.entidades.Clima;

@Repository
public interface ClimaRepository extends JpaRepository <Clima, Integer>{

    
}