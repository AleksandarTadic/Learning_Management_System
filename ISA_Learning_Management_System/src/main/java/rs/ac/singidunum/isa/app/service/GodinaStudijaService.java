package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.GodinaStudija;
import rs.ac.singidunum.isa.app.repository.GodinaStudijaRepository;

import java.util.Optional;

@Service
public class GodinaStudijaService {
    @Autowired
    private GodinaStudijaRepository godinaStudijaRepository;

    public GodinaStudijaService() {
    }

    public GodinaStudijaService(GodinaStudijaRepository godinaStudijaRepository) {
        this.godinaStudijaRepository = godinaStudijaRepository;
    }

    public GodinaStudijaRepository getGodinaStudijaRepository() {
        return godinaStudijaRepository;
    }

    public void setGodinaStudijaRepository(GodinaStudijaRepository godinaStudijaRepository) {
        this.godinaStudijaRepository = godinaStudijaRepository;
    }

    public Iterable<GodinaStudija> findAll() {
        return this.godinaStudijaRepository.findAll();
    }

    public Optional<GodinaStudija> findOne(Long id) {
        return this.godinaStudijaRepository.findById(id);
    }

    public GodinaStudija save(GodinaStudija godinaStudija) {
        return this.godinaStudijaRepository.save(godinaStudija);
    }

    public void delete(Long id) {
        this.godinaStudijaRepository.deleteById(id);
    }

    public void delete(GodinaStudija godinaStudija) {
        this.godinaStudijaRepository.delete(godinaStudija);
    }
}
