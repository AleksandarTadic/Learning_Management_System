package rs.ac.singidunum.isa.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.isa.app.model.TipZvanja;
import rs.ac.singidunum.isa.app.repository.TipZvanjaRepository;

import java.util.Optional;

@Service
public class TipZvanjaService {
    @Autowired
    private TipZvanjaRepository tipZvanjaRepository;

    public TipZvanjaService() {
    }

    public TipZvanjaService(TipZvanjaRepository tipZvanjaRepository) {
        this.tipZvanjaRepository = tipZvanjaRepository;
    }

    public TipZvanjaRepository getTipZvanjaRepository() {
        return tipZvanjaRepository;
    }

    public void setTipZvanjaRepository(TipZvanjaRepository tipZvanjaRepository) {
        this.tipZvanjaRepository = tipZvanjaRepository;
    }

    public Iterable<TipZvanja> findAll() {
        return this.tipZvanjaRepository.findAll();
    }

    public Optional<TipZvanja> findOne(Long id) {
        return this.tipZvanjaRepository.findById(id);
    }

    public TipZvanja save(TipZvanja tipZvanja) {
        return this.tipZvanjaRepository.save(tipZvanja);
    }

    public void delete(Long id) {
        this.tipZvanjaRepository.deleteById(id);
    }

    public void delete(TipZvanja tipZvanja) {
        this.tipZvanjaRepository.delete(tipZvanja);
    }
}
