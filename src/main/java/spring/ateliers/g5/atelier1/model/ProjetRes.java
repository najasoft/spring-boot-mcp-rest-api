package spring.ateliers.g5.atelier1.model;

import jakarta.persistence.Entity;

@Entity
public class ProjetRes extends Projet{
    private double budget;
    public double getBudget() {
        return budget;
    }
    public void setBudget(double budget) {
        this.budget = budget;
    }
}
