package Application.Controleur;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import Application.IHM.IHM_usager;
import Application.IHM.IHM_oeuvre;
import Application.ObjetsMetier.Usager;
import Application.ObjetsMetier.Oeuvre;
import Application.ObjetsMetier.Emprunt;
import Application.ObjetsMetier.Exemplaire;
import Application.ObjetsMetier.Reservation;
import Application.Utilitaire.Etat;

import java.time.LocalDate;
import java.util.List;

@Controller
@ComponentScan
public class SpringController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/usager")
        @GetMapping("/")
        public String usager(Model model) {
            List<Usager> usagers = IHM_usager.getAllUsagers();
            model.addAttribute("usagers", usagers);
            return "usager";
        }
        @PostMapping("/usager/ajouter")
        public String ajouterUsager(@RequestParam(name = "nom") String nom,@RequestParam(name = "prenom") String prenom,@RequestParam(name = "mail") String mail) {
            Gestion_usager gu = new Gestion_usager();
            gu.ajouter(nom, prenom, mail);
            return "redirect:/usager";
        }

        @PostMapping("/usager/modifier")
        public String modifierUsager(@RequestParam(name = "nom") String nom, @RequestParam(name = "prenom") String prenom, @RequestParam(name = "mail") String mail) {
            Gestion_usager gu = new Gestion_usager();
            gu.maj(nom, prenom, mail);
            return "redirect:/usager";
        }

        @PostMapping("/usager/supprimer")
        public String supprimerUsager(@RequestParam(name = "nom") String nom) {
            Gestion_usager gu = new Gestion_usager();
            gu.supprimer(nom);
            return "redirect:/usager";
        }

    @RequestMapping("/oeuvres")
        @GetMapping("/")
        public String afficherOeuvres(Model model) {
            List<Oeuvre> oeuvres = IHM_oeuvre.getAllOeuvres();
            model.addAttribute("oeuvres", oeuvres);
            return "oeuvres";
        }
        @PostMapping("/oeuvres/Livre/ajouter")
        public String ajouterLivre(@RequestParam(name = "titre") String titre,@RequestParam(name = "datePublication") String date,@RequestParam(name = "auteur") String auteur) {
            Gestion_oeuvre go = new Gestion_oeuvre();
            go.creationOeuvreLivre(titre, LocalDate.parse(date),auteur);
            return "redirect:/oeuvres";
        }

        @PostMapping("/oeuvres/Magazine/ajouter")
        public String ajouterMagazine(@RequestParam(name = "titre") String titre,@RequestParam(name = "datePublication") String date,@RequestParam(name = "édition") String édition,@RequestParam(name= "numéro") String numéro ){
            Gestion_oeuvre go = new Gestion_oeuvre();
            go.creationOeuvreMagazine(titre, LocalDate.parse(date), Integer.parseInt(numéro),édition);
            return "redirect:/oeuvres";
        }

        @PostMapping("/oeuvres/supprimer")
        public String supprimerOeuvre(@RequestParam(name = "titre") String titre) {
            Gestion_oeuvre go = new Gestion_oeuvre();
            go.supprimer(titre);
            return "redirect:/oeuvres";
        }


    @RequestMapping("/exemplaires")
        @GetMapping("/")
        public String afficherExemplaires(Model model) {
            Gestion_exemplaire ge = new Gestion_exemplaire();
            Gestion_oeuvre go = new Gestion_oeuvre();
            List<Oeuvre> oeuvres = go.getAllOeuvres();
            List<Exemplaire> exemplaires = ge.getAllExemplaires();
            model.addAttribute("exemplaires", exemplaires);
            model.addAttribute("oeuvres", oeuvres);
            return "exemplaires";
        }

        @PostMapping("/exemplaires/ajouter")
        public String ajouterExemplaire(@RequestParam(name = "titre") String titre) {
            Gestion_exemplaire ge = new Gestion_exemplaire();
            ge.ajouter(titre);
            return "redirect:/exemplaires";
        }

        @PostMapping("/exemplaires/supprimer")
        public String supprimerExemplaire(@RequestParam(name = "id") int id) {
            Gestion_exemplaire ge = new Gestion_exemplaire();
            ge.supprimer(id);
            return "redirect:/exemplaires";
        }

        @PostMapping("/exemplaires/update")
        public String majExemplaire(@RequestParam(name = "id") int id, @RequestParam(name = "etat") String etat) {
            Gestion_exemplaire ge = new Gestion_exemplaire();
            ge.maj(id, Etat.valueOf(etat));
            return "redirect:/exemplaires";
        }


    @RequestMapping("/emprunts-reservations")
        @GetMapping("/")
        public String afficherEmpruntsReservations(Model model) {
            Gestion_exemplaire ge = new Gestion_exemplaire();
            Gestion_oeuvre go = new Gestion_oeuvre();
            Gestion_emprunt_res ger = new Gestion_emprunt_res();
            List<Emprunt> emprunts = ger.getAllEmprunts();
            List<Oeuvre> oeuvres = go.getAllOeuvres();
            List<Reservation> reservations = ger.getAllReservations();
            List<Exemplaire> exemplaires = ge.getAllExemplaires();
            model.addAttribute("oeuvres", oeuvres);
            model.addAttribute("exemplaires", exemplaires);
            model.addAttribute("emprunts", emprunts);
            model.addAttribute("reservations", reservations);
            return "emprunts-reservations";
        }

        @PostMapping("/reservations/ajouter")
        public String reserverOeuvre(@RequestParam(name = "nom") String nom, @RequestParam(name = "titre") String titre) {
            Gestion_emprunt_res ger = new Gestion_emprunt_res();
            ger.reserver(nom, titre);
            return "redirect:/emprunts-reservations";
        }

        @PostMapping("/reservations/annuler")
        public String annulerReservation(@RequestParam(name = "nom") String nom, @RequestParam(name = "titre") String titre) {
            Gestion_emprunt_res ger = new Gestion_emprunt_res();
            ger.annuler(nom, titre);
            return "redirect:/emprunts-reservations";
        }

        // Emprunter un exemplaire
        @PostMapping("/emprunts/ajouter")
        public String emprunterExemplaire(@RequestParam(name = "nom") String nom, @RequestParam(name = "titre") String titre) {
            Gestion_emprunt_res ger = new Gestion_emprunt_res();
            ger.emprunter(nom, titre);
            return "redirect:/emprunts-reservations";
        }

        // Rendre un exemplaire
        @PostMapping("/emprunts/rendre")
        public String rendreExemplaire(@RequestParam(name = "nom") String nom, @RequestParam(name = "idExemplaire") int idExemplaire) {
            Gestion_emprunt_res ger = new Gestion_emprunt_res();
            ger.rendre(nom, idExemplaire);
            return "redirect:/emprunts-reservations";
        }
}