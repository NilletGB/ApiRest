package com.example.estructuraapibase.Repository;

import com.example.estructuraapibase.Modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}
