package org.matsim.utils;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.api.core.v01.population.PlanElement;
import org.matsim.api.core.v01.population.Population;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;
import org.matsim.core.population.io.PopulationReader;
import org.matsim.core.population.io.PopulationWriter;

public class ChangeTaxiToCar {

    public static void main(String[] args) {
        // 1. Define paths to your population files
        String inputPopulationFile = "scenarios/SEQ/merged_1pct.xml.gz";
        String outputPopulationFile = "scenarios/SEQ/merged_1pct_xtaxi.xml.gz";

        // 2. Setup the scenario and load the population data
        Scenario scenario = ScenarioUtils.createScenario(ConfigUtils.createConfig());
        Population population = scenario.getPopulation();

        System.out.println("Reading population file...");
        new PopulationReader(scenario).readFile(inputPopulationFile);

        System.out.println("Modifying leg modes from 'taxi' to 'car'...");
        long modifiedCount = 0;

        // 3. Loop through every person in the population
        for (Person person : population.getPersons().values()) {
            // Loop through all plans associated with the person
            for (Plan plan : person.getPlans()) {
                // Loop through every element (Activity or Leg) in the plan sequential order
                for (PlanElement element : plan.getPlanElements()) {

                    // Check if the current plan element is an instance of a Leg
                    if (element instanceof Leg) {
                        Leg leg = (Leg) element;

                        // If the mode is taxi, switch it to car
                        if ("taxi".equalsIgnoreCase(leg.getMode())) {
                            leg.setMode("car");
                            modifiedCount++;
                        }
                    }
                }
            }
        }

        // 4. Write the modified population back out to XML
        System.out.println("Writing updated population. Total legs modified: " + modifiedCount);
        new PopulationWriter(population).write(outputPopulationFile);
        System.out.println("Done!");
    }
}