package de.metanome.algorithm_integration;

import de.metanome.algorithm_integration.configuration.ConfigurationRequirement;
import java.util.ArrayList;

public interface Algorithm {
  ArrayList<ConfigurationRequirement<?>> getConfigurationRequirements();
  
  void execute() throws AlgorithmExecutionException;
  
  String getAuthors();
  
  String getDescription();
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\Algorithm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */