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
/*    */ @JsonTypeName("ConfigurationSettingBoolean")
/*    */ public class ConfigurationSettingBoolean
/*    */   extends ConfigurationSettingPrimitive<Boolean>
/*    */ {
/*    */   private static final long serialVersionUID = 2262344977257573858L;
/* 31 */   public String type = "ConfigurationSettingBoolean";
/*    */ 
/*    */   
/*    */   public ConfigurationSettingBoolean() {}
/*    */   
/*    */   public ConfigurationSettingBoolean(Boolean value) {
/* 37 */     super(value);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */