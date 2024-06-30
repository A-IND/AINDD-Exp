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
/*    */ @JsonTypeName("ConfigurationSettingInteger")
/*    */ public class ConfigurationSettingInteger
/*    */   extends ConfigurationSettingPrimitive<Integer>
/*    */ {
/*    */   private static final long serialVersionUID = 2660565657446501400L;
/* 29 */   public String type = "ConfigurationSettingInteger";
/*    */ 
/*    */   
/*    */   public ConfigurationSettingInteger() {}
/*    */   
/*    */   public ConfigurationSettingInteger(Integer value) {
/* 35 */     super(value);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */