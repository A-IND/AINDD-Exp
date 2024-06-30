/*    */ package de.metanome.algorithm_integration.configuration;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonTypeName;
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
/*    */ @JsonTypeName("ConfigurationSettingCheckBox")
/*    */ public class ConfigurationSettingCheckBox
/*    */   extends ConfigurationSettingPrimitive<String[]>
/*    */ {
/*    */   private static final long serialVersionUID = 4421968099033550676L;
/* 32 */   public String type = "ConfigurationSettingCheckBox";
/*    */ 
/*    */   
/*    */   public ConfigurationSettingCheckBox() {}
/*    */   
/*    */   public ConfigurationSettingCheckBox(String[] value) {
/* 38 */     super(value);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingCheckBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */