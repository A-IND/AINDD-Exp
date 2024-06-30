package de.metanome.algorithm_integration.configuration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.metanome.algorithm_integration.AlgorithmConfigurationException;
import de.metanome.algorithm_integration.input.RelationalInputGeneratorInitializer;
import javax.xml.bind.annotation.XmlTransient;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = ConfigurationSettingFileInput.class, name = "ConfigurationSettingFileInput"), @Type(value = ConfigurationSettingTableInput.class, name = "ConfigurationSettingTableInput")})
public abstract class ConfigurationSettingRelationalInput extends ConfigurationSettingDataSource {
  private static final long serialVersionUID = 1594413104605417301L;
  
  @XmlTransient
  public abstract void generate(RelationalInputGeneratorInitializer paramRelationalInputGeneratorInitializer) throws AlgorithmConfigurationException;
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingRelationalInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */