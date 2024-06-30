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
/*    */ 
/*    */ @JsonTypeName("ConfigurationRequirementInteger")
/*    */ public class ConfigurationRequirementInteger
/*    */   extends ConfigurationRequirementDefaultValue<Integer, ConfigurationSettingInteger>
/*    */ {
/*    */   private static final long serialVersionUID = -7619198060224933585L;
/* 39 */   public String type = "ConfigurationRequirementInteger";
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementInteger() {}
/*    */   
/*    */   public ConfigurationRequirementInteger(String identifier) {
/* 45 */     super(identifier);
/*    */   }
/*    */   
/*    */   public ConfigurationRequirementInteger(String identifier, int numberOfSettings) {
/* 49 */     super(identifier, numberOfSettings);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementInteger(String identifier, int minNumberOfSetting, int maxNumberOfSetting) {
/* 54 */     super(identifier, minNumberOfSetting, maxNumberOfSetting);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @XmlTransient
/*    */   public ConfigurationValue build(ConfigurationFactory factory) throws AlgorithmConfigurationException, FileNotFoundException {
/* 64 */     return factory.build(this);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationRequirementInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */