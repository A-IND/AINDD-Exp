/*    */ package de.metanome.algorithm_integration.configuration;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*    */ import de.metanome.algorithm_integration.AlgorithmConfigurationException;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.util.List;
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
/*    */ @JsonTypeName("ConfigurationRequirementDatabaseConnection")
/*    */ public class ConfigurationRequirementDatabaseConnection
/*    */   extends ConfigurationRequirement<ConfigurationSettingDatabaseConnection>
/*    */ {
/*    */   private static final long serialVersionUID = 1540216264474337285L;
/* 38 */   public String type = "ConfigurationRequirementDatabaseConnection";
/*    */   
/*    */   private List<String> acceptedDBSystems;
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementDatabaseConnection() {}
/*    */   
/*    */   public ConfigurationRequirementDatabaseConnection(String identifier) {
/* 46 */     super(identifier);
/*    */   }
/*    */   
/*    */   public ConfigurationRequirementDatabaseConnection(String identifier, int numberOfSettings) {
/* 50 */     super(identifier, numberOfSettings);
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementDatabaseConnection(String identifier, int minNumberOfSetting, int maxNumberOfSetting) {
/* 55 */     super(identifier, minNumberOfSetting, maxNumberOfSetting);
/*    */   }
/*    */   
/*    */   public List<String> getAcceptedDBSystems() {
/* 59 */     return this.acceptedDBSystems;
/*    */   }
/*    */   
/*    */   public void setAcceptedDBSystems(List<String> acceptedDBSystems) {
/* 63 */     this.acceptedDBSystems = acceptedDBSystems;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @XmlTransient
/*    */   public ConfigurationValue build(ConfigurationFactory factory) throws AlgorithmConfigurationException, FileNotFoundException {
/* 73 */     return factory.build(this);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationRequirementDatabaseConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */