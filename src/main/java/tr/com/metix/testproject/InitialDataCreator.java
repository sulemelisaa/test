package tr.com.metix.testproject;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tr.com.metix.testproject.domain.Authority;
import tr.com.metix.testproject.domain.User;
import tr.com.metix.testproject.repository.AuthorityRepository;
import tr.com.metix.testproject.repository.UserRepository;

@Component
public class InitialDataCreator {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    public InitialDataCreator(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        if(!authorityRepository.existsById("ROLE_ADMIN")) {
            Authority roleAdmin = new Authority();
            roleAdmin.setName("ROLE_ADMIN");

            authorityRepository.save(roleAdmin);
        }

        if(!authorityRepository.existsById("ROLE_USER")) {
            Authority roleUser = new Authority();
            roleUser.setName("ROLE_USER");

            authorityRepository.save(roleUser);
        }

        if(!userRepository.findOneByLogin("system").isPresent()) {
            User user = new User();

            user.setLogin("system");
            user.setPassword("$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG");
            user.setFirstName("System");
            user.setLastName("System");
            user.setEmail("system@localhost");
            user.setImageUrl("");
            user.setActivated(true);
            user.setLangKey("en");
            user.setCreatedBy("system");
            user.setLastModifiedBy("system");

            userRepository.save(user);
        }

        if(!userRepository.findOneByLogin("anonymoususer").isPresent()) {
            User user = new User();

            user.setLogin("anonymoususer");
            user.setPassword("$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO");
            user.setFirstName("Anonymous");
            user.setLastName("User");
            user.setEmail("anonymous@localhost");
            user.setImageUrl("");
            user.setActivated(true);
            user.setLangKey("en");
            user.setCreatedBy("system");
            user.setLastModifiedBy("system");

            userRepository.save(user);
        }

        if(!userRepository.findOneByLogin("admin").isPresent()) {
            User user = new User();

            user.setLogin("admin");
            user.setPassword("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC");
            user.setFirstName("Administrator");
            user.setLastName("Administrator");
            user.setEmail("admin@localhost");
            user.setImageUrl("");
            user.setActivated(true);
            user.setLangKey("en");
            user.setCreatedBy("system");
            user.setLastModifiedBy("system");

            user.getAuthorities().add(authorityRepository.findById("ROLE_ADMIN").get());
            user.getAuthorities().add(authorityRepository.findById("ROLE_USER").get());

            userRepository.save(user);
        }

        if(!userRepository.findOneByLogin("user").isPresent()) {
            User user = new User();

            user.setLogin("user");
            user.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");
            user.setFirstName("User");
            user.setLastName("User");
            user.setEmail("user@localhost");
            user.setImageUrl("");
            user.setActivated(true);
            user.setLangKey("en");
            user.setCreatedBy("system");
            user.setLastModifiedBy("system");

            user.getAuthorities().add(authorityRepository.findById("ROLE_USER").get());

            userRepository.save(user);
        }
    }
}
