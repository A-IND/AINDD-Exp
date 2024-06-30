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
/*    */ 
/*    */ @JsonTypeName("ConfigurationRequirementCheckBox")
/*    */ public class ConfigurationRequirementCheckBox
/*    */   extends ConfigurationRequirementDefaultValue<String[], ConfigurationSettingCheckBox>
/*    */ {
/*    */   private static final long serialVersionUID = -4281413599644981292L;
/* 40 */   public String type = "ConfigurationRequirementCheckBox";
/*    */   
/*    */   private String[] values;
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementCheckBox() {}
/*    */   
/*    */   public ConfigurationRequirementCheckBox(String identifier, String[] values) {
/* 48 */     super(identifier);
/* 49 */     this.values = values;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementCheckBox(String identifier, String[] values, int numberOfSettings) {
/* 54 */     super(identifier, numberOfSettings);
/* 55 */     this.values = values;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementCheckBox(String identifier, String[] values, int minNumberOfSetting, int maxNumberOfSetting) {
/* 60 */     super(identifier, minNumberOfSetting, maxNumberOfSetting);
/* 61 */     this.values = values;
/*    */   }
/*    */   
/*    */   public String[] getValues() {
/* 65 */     return this.values;
/*    */   }
/*    */   
/*    */   public void setValues(String[] values) {
/* 69 */     this.values = values;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @XmlTransient
/*    */   public ConfigurationValue build(ConfigurationFactory factory) throws AlgorithmConfigurationException, FileNotFoundException {
/* 79 */     return factory.build(this);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationRequirementCheckBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */