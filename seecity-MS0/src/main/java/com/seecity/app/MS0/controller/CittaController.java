package com.seecity.app.MS0.controller;

import com.seecity.app.MS0.model.Citta;
import com.seecity.app.MS0.repository.CittaRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CittaController {
    //@Autowired
    //CittaService service;

    @Autowired
    CittaRepository cittaRepository;

    @GetMapping(path="/ping")
    @ResponseStatus(HttpStatus.OK)
    String ping() {
        return "pong";
    }

    @GetMapping("/")
    public Flux<Citta> getCitta() {
        return cittaRepository.findAll();
    }

    @GetMapping("/{nome}")
    public Mono<ResponseEntity<Citta>> getCity(@PathVariable("nome") String nome) {
        Mono<ResponseEntity<Citta>> responseEntityMono = cittaRepository
                .findByNome(nome)
                .map(x -> new ResponseEntity<>(x, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        return responseEntityMono;
    }

    @GetMapping("/{nome}/exists")
    public @ResponseBody ResponseEntity<Mono<Boolean>> exists(@PathVariable("nome") String nome) {
        Mono<Boolean> ret = cittaRepository.existsByNome(nome);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }

    @DeleteMapping("/{nome}")
    public @ResponseBody ResponseEntity<Boolean> deleteCitta(@PathVariable("nome") String nome) {

        boolean exists = exists(nome).getBody().block();

        if (exists) {
            cittaRepository.deleteByNome(nome);
        } else
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(
                true, HttpStatus.OK
        );
    }

    @PostMapping(path="/", consumes={"application/JSON"}, produces="application/json")
    public Mono<Citta> createCitta(@RequestBody Citta c) {
        return cittaRepository.save(c);
    }

    /*
    @GetMapping(path="/ping")
    @ResponseStatus(HttpStatus.OK)
    String ping() {
        return "pong";
    }

    @GetMapping(path="/citta")
    public @ResponseBody
    Iterable<Citta> getCitta() {
        return cittaRepository.findAll();
    }

    //Post passaggio parametri tramite URL
    @PostMapping(path="/createCitta/nome={nome}&provincia={provincia}&cap={cap}&paese={paese}&descrizione={descrizione}")
    public @ResponseBody
    String createCitta(@PathVariable String nome, @PathVariable String provincia,
                                          @PathVariable String cap, @PathVariable String paese,
                                          @PathVariable String descrizione){
        Citta c = new Citta();
        c.setNome(nome);
        c.setProvincia(provincia);
        c.setCap(cap);
        c.setPaese(paese);
        c.setDescrizione(descrizione);
        cittaRepository.save(c);
        System.out.println(nome + " " + provincia + " " + cap + " " + paese + " " + descrizione + " ");
        return "E' stata inserita la città di: " + nome + " (" + provincia + ")";
    }

    //Post passaggio parametri tramite JSON
    @PostMapping("citta/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    String addCitta(@RequestBody Citta newCitta){
        Citta c = new Citta();
        c.setNome(newCitta.getNome());
        c.setProvincia(newCitta.getProvincia());
        c.setCap(newCitta.getCap());
        c.setPaese(newCitta.getPaese());
        c.setDescrizione(newCitta.getDescrizione());
        cittaRepository.save(c);
        return "E' stata inserita la città di: " + newCitta.getNome() + " (" + newCitta.getProvincia() + ")";
    }
     */

}
