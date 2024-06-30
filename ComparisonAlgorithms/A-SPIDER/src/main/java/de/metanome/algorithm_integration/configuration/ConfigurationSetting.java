package de.metanome.algorithm_integration.configuration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = ConfigurationSettingFileInput.class, name = "ConfigurationSettingFileInput"), @Type(value = ConfigurationSettingTableInput.class, name = "ConfigurationSettingTableInput"), @Type(value = ConfigurationSettingDatabaseConnection.class, name = "ConfigurationSettingDatabaseConnection"), @Type(value = ConfigurationSettingBoolean.class, name = "ConfigurationSettingBoolean"), @Type(value = ConfigurationSettingInteger.class, name = "ConfigurationSettingInteger"), @Type(value = ConfigurationSettingListBox.class, name = "ConfigurationSettingListBox"), @Type(value = ConfigurationSettingCheckBox.class, name = "ConfigurationSettingCheckBox"), @Type(value = ConfigurationSettingString.class, name = "ConfigurationSettingString")})
public abstract class ConfigurationSetting implements Serializable {
  private static final long serialVersionUID = 8033533258688078511L;
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSetting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */