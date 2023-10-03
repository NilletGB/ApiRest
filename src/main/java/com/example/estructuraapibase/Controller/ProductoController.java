package com.example.estructuraapibase.Controller;

import com.example.estructuraapibase.Modelo.Producto;
import com.example.estructuraapibase.Repository.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Este es un controllador basico para hacer rapidamente un ApiRest
@RestController
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoRepositorio productoRepositorio;

    /**
     * Obtenemos todos los productos
     *
     * @return
     */
    @GetMapping("/producto")
    public List<Producto> obtenerTodos() {
        // Vamos a modificar este código
        return productoRepositorio.findAll();
    }

    /**
     * Obtenemos un producto en base a su ID
     *
     * @param id
     * @return Null si no encuentra el producto
     */
    @GetMapping("/producto/{id}")
    public Producto obtenerUno(@PathVariable Long id) {
        // Pedimos un producto por su ID, en el caso que no existiera nos devuelve un NULL
        return productoRepositorio.findById(id).orElse(null);
    }

    /**
     * Insertamos un nuevo producto
     *
     * @param nuevo
     * @return producto insertado
     */
    @PostMapping("/producto")
    public Producto nuevoProducto(@RequestBody Producto nuevo) {
        // Vamos a modificar este código
        return productoRepositorio.save(nuevo);
    }

    /**
     *
     * @param editar
     * @param id
     * @return
     */
    @PutMapping("/producto/{id}")
    public Producto editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        // Vamos a modificar este código
        if(productoRepositorio.existsById(id)){
            editar.setId(id);
            return productoRepositorio.save(editar);
        }else{
            return null;
        }
    }

    /**
     * Borra un producto del catálogo en base a su id
     * @param id
     * @return
     */
    @DeleteMapping("/producto/{id}")
    public Producto borrarProducto(@PathVariable Long id) {
        // Vamos a modificar este código
        if(productoRepositorio.existsById(id)){
            Producto producto = productoRepositorio.findById(id).get();
            productoRepositorio.deleteById(id);
            return producto;
        }else{
            return null;
        }
    }
}
