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
/*    */ 
/*    */ @JsonTypeName("ConfigurationRequirementListBox")
/*    */ public class ConfigurationRequirementListBox
/*    */   extends ConfigurationRequirementDefaultValue<String, ConfigurationSettingListBox>
/*    */ {
/*    */   private static final long serialVersionUID = -4281413599644981292L;
/* 39 */   public String type = "ConfigurationRequirementListBox";
/*    */   
/*    */   private List<String> values;
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementListBox() {}
/*    */   
/*    */   public ConfigurationRequirementListBox(String identifier, List<String> values) {
/* 47 */     super(identifier);
/* 48 */     this.values = values;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementListBox(String identifier, List<String> values, int numberOfSettings) {
/* 53 */     super(identifier, numberOfSettings);
/* 54 */     this.values = values;
/*    */   }
/*    */ 
/*    */   
/*    */   public ConfigurationRequirementListBox(String identifier, List<String> values, int minNumberOfSetting, int maxNumberOfSetting) {
/* 59 */     super(identifier, minNumberOfSetting, maxNumberOfSetting);
/* 60 */     this.values = values;
/*    */   }
/*    */   
/*    */   public List<String> getValues() {
/* 64 */     return this.values;
/*    */   }
/*    */   
/*    */   public void setValues(List<String> values) {
/* 68 */     this.values = values;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @XmlTransient
/*    */   public ConfigurationValue build(ConfigurationFactory factory) throws AlgorithmConfigurationException, FileNotFoundException {
/* 78 */     return factory.build(this);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationRequirementListBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */