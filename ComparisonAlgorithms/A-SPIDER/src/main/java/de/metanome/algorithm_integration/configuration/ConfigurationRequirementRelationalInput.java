/*    */ package de.metanome.algorithm_integration.configuration;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*    */ import de.metanome.algorithm_integration.AlgorithmConfigurationException;
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
/*    */ @JsonTypeName("ConfigurationRequirementRelationalInput")
/*    */ public class ConfigurationRequirementRelationalInput
/*    */   extends ConfigurationRequirement<ConfigurationSettingRelationalInput>
/*    */ {
/*    */   private static final long serialVersionUID = 6007734923218603744L;
/* 37 */   public String type = "ConfigurationRequirementRelationalInput";
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementRelationalInput() {}
/*    */   
/*    */   public ConfigurationRequirementRelationalInput(String identifier) {
/* 43 */     super(identifier);
/*    */   }
/*    */   
/*    */   public ConfigurationRequirementRelationalInput(String identifier, int numberOfSettings) {
/* 47 */     super(identifier, numberOfSettings);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementRelationalInput(String identifier, int minNumberOfSetting, int maxNumberOfSetting) {
/* 52 */     super(identifier, minNumberOfSetting, maxNumberOfSetting);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @XmlTransient
/*    */   public ConfigurationValue build(ConfigurationFactory factory) throws AlgorithmConfigurationException {
/* 62 */     return factory.build(this);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationRequirementRelationalInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */