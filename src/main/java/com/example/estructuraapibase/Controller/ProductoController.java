package com.example.estructuraapibase.Controller;

import com.example.estructuraapibase.Modelo.Producto;
import com.example.estructuraapibase.Repository.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Este es un controllador basico para hacer rapidamente un ApiRest
 */

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
     * //@PathVariable es una anotación utilizada en Spring MVC para capturar valores
     * de variables presentes en la URL de una solicitud HTTP y utilizarlos en los métodos
     * de controlador. Ejemplo: En este ejemplo, la anotación @PathVariable se utiliza para
     * capturar el valor de la variable "id" presente en la URL "/producto/{id}". El valor
     *capturado se asigna a la variable "id" del método del controlador, que luego se puede
     *utilizar para realizar alguna lógica específica, como obtener los detalles del usuario
     * con ese ID.
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
    /**
     *
     * @RequestBody es una anotación utilizada en Spring MVC para indicar que un parámetro de un método
     * de controlador debe ser vinculado al cuerpo de una solicitud HTTP. Esto facilita la recepción y
     * deserialización automática de datos enviados en el cuerpo de la solicitud en objetos Java correspondientes.
     */
    /**
     *
     * En este ejemplo, la anotación @RequestBody se utiliza para indicar que el parámetro "usuario" del
     * método de controlador debe ser vinculado al cuerpo de la solicitud HTTP. Spring MVC se encargará de
     * deserializar automáticamente los datos del cuerpo de la solicitud en un objeto de tipo "Usuario",
     * utilizando un convertidor adecuado (por ejemplo, un convertidor JSON si los datos están en formato JSON).
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
