/*    */ package de.metanome.algorithm_integration.configuration;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*    */ import de.metanome.algorithm_integration.AlgorithmConfigurationException;
/*    */ import java.io.FileNotFoundException;
/*    */ import javax.xml.bind.annotation.XmlTransient;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonTypeName("ConfigurationRequirementString")
/*    */ public class ConfigurationRequirementString
/*    */   extends ConfigurationRequirementDefaultValue<String, ConfigurationSettingString>
/*    */ {
/*    */   private static final long serialVersionUID = -858291415372126776L;
/* 38 */   public String type = "ConfigurationRequirementString";
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementString() {}
/*    */   
/*    */   public ConfigurationRequirementString(String identifier) {
/* 44 */     super(identifier);
/*    */   }
/*    */   
/*    */   public ConfigurationRequirementString(String identifier, int numberOfSettings) {
/* 48 */     super(identifier, numberOfSettings);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementString(String identifier, int minNumberOfSetting, int maxNumberOfSetting) {
/* 53 */     super(identifier, minNumberOfSetting, maxNumberOfSetting);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @XmlTransient
/*    */   public ConfigurationValue build(ConfigurationFactory factory) throws AlgorithmConfigurationException, FileNotFoundException {
/* 63 */     return factory.build(this);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationRequirementString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */