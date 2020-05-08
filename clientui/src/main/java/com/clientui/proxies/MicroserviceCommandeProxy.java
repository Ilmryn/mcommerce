package com.clientui.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.clientui.beans.CommandeBean;

@FeignClient(name = "microservice-commandes")
@RibbonClient(name = "microservice-commandes")
public interface MicroserviceCommandeProxy {

    @PostMapping(value = "/microservice-paiement/commandes")
    CommandeBean ajouterCommande(@RequestBody CommandeBean commande);
}
