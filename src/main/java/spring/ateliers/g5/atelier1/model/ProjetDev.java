package spring.ateliers.g5.atelier1.model;

import jakarta.persistence.Entity;

@Entity
public class ProjetDev extends Projet{
    private String langage;

   public String getLangage() {
       return langage;
   }

    public void setLangage(String langage) {
        this.langage = langage;
    }
}
