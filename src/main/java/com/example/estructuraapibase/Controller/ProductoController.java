package com.example.estructuraapibase.Controller;

import com.example.estructuraapibase.Modelo.Producto;
import com.example.estructuraapibase.Repository.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    /**
     *
     * @ResponseEntity es una clase en el framework de Spring que representa una respuesta HTTP.
     * Proporciona métodos para configurar el código de estado, las cabeceras y el cuerpo de la respuesta.
     * ResponseEntity se utiliza comúnmente en aplicaciones web para devolver una respuesta personalizada
     * al cliente.
     * En este ejemplo, el método obtenerTodos() devuelve un objeto ResponseEntity que contiene un mensaje de
     * saludo y el código de estado HTTP 200 (OK). Esto indica que la solicitud fue exitosa. El tipo de
     * cuerpo de la respuesta se establece como String, pero podría ser cualquier otro tipo de objeto, como
     * un objeto JSON o XML.
     */
    @GetMapping("/producto")
    public ResponseEntity<?> obtenerTodos() {
        // Vamos a modificar este código
        List<Producto> result= productoRepositorio.findAll();
        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(result);
        }
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
    public ResponseEntity<?> obtenerUno(@PathVariable Long id) {
        Producto result = productoRepositorio.findById(id).orElse(null);
        if(result==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(result);
        }
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
    public ResponseEntity<Producto> nuevoProducto(@RequestBody Producto nuevo) {
        Producto saved= productoRepositorio.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     *
     * @param editar
     * @param id
     * @return
     */
    @PutMapping("/producto/{id}")
    public ResponseEntity<?> editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
        return productoRepositorio.findById(id).map(p->{
            p.setNombre(editar.getNombre());
            p.setPrecio(editar.getPrecio());
            return ResponseEntity.ok(productoRepositorio.save(p));
        }).orElseGet(()-> {
            return ResponseEntity.notFound().build();
        });
    }

    /**
     * Borra un producto del catálogo en base a su id
     * @param id
     * @return
     */
    @DeleteMapping("/producto/{id}")
    public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
        productoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
