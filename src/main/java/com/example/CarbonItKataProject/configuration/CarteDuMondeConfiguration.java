package com.example.CarbonItKataProject.configuration;

import com.example.CarbonItKataProject.hexagon.domain.port.primary.Animation;
import com.example.CarbonItKataProject.hexagon.domain.port.primary.TraductionDonnees;
import com.example.CarbonItKataProject.hexagon.domain.usecases.AnimationHandler;
import com.example.CarbonItKataProject.hexagon.domain.usecases.TraductionDonneesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarteDuMondeConfiguration {

    @Bean
    public Animation animation() {return new AnimationHandler(); }

    @Bean
    public TraductionDonnees traductionDonnees() {return new TraductionDonneesHandler(); }

}
