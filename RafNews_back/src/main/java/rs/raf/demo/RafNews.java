package rs.raf.demo;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.demo.repositories.kategorije.KategorijaRepository;
import rs.raf.demo.repositories.kategorije.MySqlKategorijaRepository;
import rs.raf.demo.repositories.korisnici.KorisnikRepository;
import rs.raf.demo.repositories.korisnici.MySqlKorisniciRepository;
import rs.raf.demo.repositories.vesti.MySqlVestiRepository;
import rs.raf.demo.repositories.vesti.VestiRepository;
import rs.raf.demo.services.KategorijeService;
import rs.raf.demo.services.KorisniciService;
import rs.raf.demo.services.VestiService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/cms")
public class RafNews extends ResourceConfig {

    public RafNews() {

        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {

                this.bind(MySqlKorisniciRepository.class).to(KorisnikRepository.class).in(Singleton.class);
                this.bindAsContract(KorisniciService.class);

                this.bind(MySqlKategorijaRepository.class).to(KategorijaRepository.class).in(Singleton.class);
                this.bindAsContract(KategorijeService.class);

                this.bind(MySqlVestiRepository.class).to(VestiRepository.class).in(Singleton.class);
                this.bindAsContract(VestiService.class);


            }
        };
        register(binder);
        packages("rs.raf.demo");
    }

}
