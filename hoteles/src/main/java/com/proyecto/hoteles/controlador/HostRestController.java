package com.proyecto.hoteles.controlador;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.hoteles.entidades.Huesped;
import com.proyecto.hoteles.repositorios.HostRepository;

@RestController
@RequestMapping("/huesped")
public class HostRestController {
      @Autowired
    HostRepository hostRepository;

    @GetMapping()
    public List<Huesped> findAll() {
        return hostRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<Huesped> host = hostRepository.findById(id);
        if(host.isPresent()){
            return new ResponseEntity<>(host.get(), HttpStatus.OK); 
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Huesped input) {
        Optional<Huesped> optionalHuesped = hostRepository.findById(id);
        if(optionalHuesped.isPresent()){
            Huesped newHuesped = optionalHuesped.get();
            newHuesped.setNombre(input.getNombre());
            newHuesped.setApellido(input.getApellido());
            newHuesped.setDniPasaporte(input.getDniPasaporte());
            newHuesped.setFechaCheckin(input.getFechaCheckin());
            newHuesped.setFechaCheckout(input.getFechaCheckout());
            Huesped save = hostRepository.save(newHuesped);
            return new ResponseEntity<>(save, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> post(@RequestBody Huesped input) {
        Huesped save = hostRepository.save(input);
        return ResponseEntity.ok(save);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        hostRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
