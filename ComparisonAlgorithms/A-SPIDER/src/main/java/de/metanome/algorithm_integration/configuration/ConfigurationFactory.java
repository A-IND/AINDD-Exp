package de.metanome.algorithm_integration.configuration;

import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import java.io.FileNotFoundException;

public interface ConfigurationFactory {
  ConfigurationValue build(ConfigurationRequirementBoolean paramConfigurationRequirementBoolean) throws AlgorithmConfigurationException, FileNotFoundException;
  
  ConfigurationValue build(ConfigurationRequirementRelationalInput paramConfigurationRequirementRelationalInput) throws AlgorithmConfigurationException;
  
  ConfigurationValue build(ConfigurationRequirementDatabaseConnection paramConfigurationRequirementDatabaseConnection) throws AlgorithmConfigurationException, FileNotFoundException;
  
  ConfigurationValue build(ConfigurationRequirementFileInput paramConfigurationRequirementFileInput) throws AlgorithmConfigurationException, FileNotFoundException;
  
  ConfigurationValue build(ConfigurationRequirementInteger paramConfigurationRequirementInteger) throws AlgorithmConfigurationException, FileNotFoundException;
  
  ConfigurationValue build(ConfigurationRequirementListBox paramConfigurationRequirementListBox) throws AlgorithmConfigurationException, FileNotFoundException;
  
  ConfigurationValue build(ConfigurationRequirementCheckBox paramConfigurationRequirementCheckBox) throws AlgorithmConfigurationException, FileNotFoundException;
  
  ConfigurationValue build(ConfigurationRequirementString paramConfigurationRequirementString) throws AlgorithmConfigurationException, FileNotFoundException;
  
  ConfigurationValue build(ConfigurationRequirementTableInput paramConfigurationRequirementTableInput) throws AlgorithmConfigurationException, FileNotFoundException;
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */