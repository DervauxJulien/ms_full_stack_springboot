package com.rodez.com.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubmitDemande {

    @PostMapping("submitDemande")
    public String confirmdemande (){

        // check some critère to submit demande before redirection to confimation page
        // générer le code de suivi de la demande et l'injecter dans la page de confirmation
        return "demande_confirmation";
    }
}
