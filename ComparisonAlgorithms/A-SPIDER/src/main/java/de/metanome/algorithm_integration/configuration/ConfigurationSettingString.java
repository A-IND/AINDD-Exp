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
/*    */ @JsonTypeName("ConfigurationSettingString")
/*    */ public class ConfigurationSettingString
/*    */   extends ConfigurationSettingPrimitive<String>
/*    */ {
/*    */   private static final long serialVersionUID = 123147403621547737L;
/* 31 */   public String type = "ConfigurationSettingString";
/*    */ 
/*    */   
/*    */   public ConfigurationSettingString() {}
/*    */   
/*    */   public ConfigurationSettingString(String value) {
/* 37 */     super(value);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */