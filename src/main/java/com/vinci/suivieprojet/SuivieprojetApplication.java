package com.vinci.suivieprojet;



import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vinci.suivieprojet.models.Document;
import com.vinci.suivieprojet.models.Organisme;
import com.vinci.suivieprojet.models.Phase;
import com.vinci.suivieprojet.models.Profil;
import com.vinci.suivieprojet.models.Project;
import com.vinci.suivieprojet.models.User;
import com.vinci.suivieprojet.models.UserDemande;
import com.vinci.suivieprojet.repository.DocumentRepository;
import com.vinci.suivieprojet.repository.OrganismeRepository;
import com.vinci.suivieprojet.repository.PhaseRepository;
import com.vinci.suivieprojet.repository.ProjectRepository;
import com.vinci.suivieprojet.repository.UserDemandeRepository;
import com.vinci.suivieprojet.repository.UserRepository;


import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SuivieprojetApplication {

	@Autowired
	UserDemandeRepository userdemanderepo;
	
	@Autowired
	PhaseRepository phaserepo;
	
	@Autowired
	ProjectRepository projectrepo;
	
	@Autowired
	DocumentRepository documentrepo;
	
	@Autowired
	UserRepository userrepo;
	
	@Autowired
	OrganismeRepository organismerepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	private static final Logger log = LoggerFactory.getLogger(SuivieprojetApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SuivieprojetApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner initialCreate() {
		return (args) -> {

//			User user = new User((long) 1,"houssam","bouajjioune","0666666666",
//					"houssam@email.com","houssam",encoder.encode("houssam"),Profil.ADMIN
//);
//
//			User user1 = new User((long) 2,"kaoutar","mez","067777777",
//					"mez@email.com","kaoutar",encoder.encode("kaoutar"),Profil.DIRECTEUR
//);
//
//			User admin = new User((long) 3,"karim","barmou","0688888888",
//					"barmou@email.com","karim",encoder.encode("karim"),Profil.ETUDIANT);
//
//			log.info("Preloading " +userrepo.save(admin));
//			log.info("Preloading " +userrepo.save(user1));
//			log.info("Preloading " +userrepo.save(user));
//			
//			
//			UserDemande userdemande1 = new UserDemande("houssam","houssam@email.com",encoder.encode("houssam"));
//			UserDemande userdemande2= new UserDemande("kaoutar","mez@email.com",encoder.encode("kaoutar"));
//			UserDemande userdemande3 = new UserDemande("karim","barmou@email.com",encoder.encode("karim"));
//			
//			log.info("Preloading " +userdemanderepo.save(userdemande1));
//			log.info("Preloading " +userdemanderepo.save(userdemande2));
//			log.info("Preloading " +userdemanderepo.save(userdemande3));
			
//			User user1 = new User((long) 10,"asmae","asmae","06 45 45 34 23",
//			"asmae@email.com","asmae",encoder.encode("asmae"),Profil.ROLE_ETUDIANT);
//			
//			User user2 = new User((long) 11,"hassan","hassan","07 99 45 87 09",
//			"hassan@email.com","hassan",encoder.encode("hassan"),Profil.ROLE_ETUDIANT);
			
//			User user3 = new User((long) 6,"ghita","ghita","ghita",
//			"ghita@email.com","ghita",encoder.encode("ghita"),Profil.ROLE_ETUDIANT);
//			
//			User user4 = new User((long) 7,"khawla","khawla","khawla",
//			"khawla@email.com","khawla",encoder.encode("khawla"),Profil.ROLE_ETUDIANT);
//			
//			User user5 = new User((long) 8,"IALA","Imad","06 56 45 12 85",
//			"ialaimad@email.com","ialaimad@email.com",encoder.encode("alaimad"),Profil.ROLE_ENCADERENT);
//			
//			User user6 = new User((long) 9,"ELYAHYAOUI","Soufiane","05 65 65 41 23",
//			"elyahyaoui@email.com","elyahyaoui@email.com",encoder.encode("elyahyaoui"),Profil.ROLE_ENCADERENT);
//			
//			User user7 = new User((long) 10,"surveillant","surveillant","surveillant",
//			"surveillant@email.com","surveillant",encoder.encode("surveillant"),Profil.ROLE_SURVEILLANT);
//			
//			userrepo.save(user1);
//			userrepo.save(user2);
//			userrepo.save(user3);
//			userrepo.save(user4);
//			userrepo.save(user5);
//			userrepo.save(user6);
//			userrepo.save(user7);
//			
//			Organisme org1=new Organisme("organisme1", "organisme1", "organisme1", "organisme1", "organisme1", "organisme1");
//			Organisme org2=new Organisme("organisme2", "organisme2", "organisme2", "organisme2", "organisme2", "organisme2");
//			
//			organismerepo.save(org1);
//			organismerepo.save(org2);
			
//			Project p1=new Project("project1", "project1", LocalDate.of(2020, Month.JANUARY, 10), LocalDate.of(2020, Month.MARCH, 10));
//			List<Document> dt1 =new ArrayList<>();
//			dt1.add(new Document("adresse1"));
//			dt1.add(new Document("adresse2"));
////			p1.setDocument_technique(dt1);
//			p1.setEtudiant1(userrepo.findById((long) 4).get());
//			p1.setEtudiant2(userrepo.findById((long) 5).get());
////			p1.setOrganisme(organismerepo.findById((long) 5).get());
//			p1.setEncaderent(userrepo.findById((long) 8).get());
//			
//			projectrepo.save(p1);
			
//			Project p2=new Project("project2", "project2", LocalDate.of(2021, Month.JANUARY, 10), LocalDate.of(2021, Month.MARCH, 10));
//			List<Document> dt1 =new ArrayList<>();
//			dt1.add(new Document("adresse1"));
//			dt1.add(new Document("adresse2"));
////			p1.setDocument_technique(dt1);
//			p2.setEtudiant1(userrepo.findById((long) 4).get());
//			p2.setEtudiant2(userrepo.findById((long) 5).get());
////			p2.setOrganisme(organismerepo.findById((long) 6).get());
//			p2.setEncaderent(userrepo.findById((long) 9).get());
//			
//			projectrepo.save(p2);
			
//			Project p2= projectrepo.findById((long)69).get();
//			Project p2=new Project("project3", "project3", LocalDate.of(2021, Month.JANUARY, 10), LocalDate.of(2021, Month.MARCH, 10));
//			Organisme org1=organismerepo.findById((long)7).get();
//			p2.setOrganisme(org1);
//			p2=projectrepo.save(p2);
//			p2= projectrepo.findById(p2.getId()).get();
//			List<Document> dt1 =new ArrayList<>();
//			Document d1=documentrepo.save(new Document("adresse6"));
//			Document d2=documentrepo.save(new Document("adresse6"));
//			dt1.add(d1);
//			dt1.add(d2);
//			p2.setDocument_technique(dt1);
//			p2=projectrepo.save(p2);
//			Organisme org1=organismerepo.findById((long)7).get();
//			p2.setOrganisme(org1);
//			p2=projectrepo.save(p2);
//			**********************************
//			Project p2=new Project("project3", "project3", LocalDate.of(2021, Month.JANUARY, 10), LocalDate.of(2021, Month.MARCH, 10));
//			p2.setEtudiant1(userrepo.findById((long) 4).get());
//			p2.setEtudiant2(userrepo.findById((long) 5).get());
//			p2.setEncaderent(userrepo.findById((long) 9).get());
//			p2=projectrepo.save(p2);
//			p2= projectrepo.findById(p2.getId()).get();
//			List<Document> dt1 =new ArrayList<>();
//			Document d1=documentrepo.save(new Document("adresse7"));
//			Document d2=documentrepo.save(new Document("adresse8"));
//			dt1.add(d1);
//			dt1.add(d2);
//			p2.setDocument_technique(dt1);
//			Organisme org1=organismerepo.findById((long)7).get();
//			p2.setOrganisme(org1);
//			p2=projectrepo.save(p2);
			
//			
//			Project p2= projectrepo.findById((long)69).get();
//			Phase ph1 = new Phase("phase3", "phase3",  LocalDate.of(2021, Month.JANUARY, 10), LocalDate.of(2021, Month.MARCH, 10));
//			ph1.setEtudiantrealise(userrepo.findById((long) 4).get());
//			ph1.setProject(p2);
//			Phase ph2 = new Phase("phase4", "phase4",  LocalDate.of(2021, Month.JANUARY, 10), LocalDate.of(2021, Month.MARCH, 10));
//			ph2.setEtudiantrealise(userrepo.findById((long) 5).get());
//			ph2.setProject(p2);
//			phaserepo.save(ph1);
//			phaserepo.save(ph2);
//			p2.getPhases().add(ph1);
//			p2.getPhases().add(ph2);
//			projectrepo.save(p2);
		};
		
		
	}
	
	
}
