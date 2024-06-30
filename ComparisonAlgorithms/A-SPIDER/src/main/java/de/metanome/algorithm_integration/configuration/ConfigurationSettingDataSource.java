/*    */ package de.metanome.algorithm_integration.configuration;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonSubTypes;
/*    */ import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
/*    */ import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
/*    */ @JsonSubTypes({@Type(value = ConfigurationSettingFileInput.class, name = "ConfigurationSettingFileInput"), @Type(value = ConfigurationSettingTableInput.class, name = "ConfigurationSettingTableInput"), @Type(value = ConfigurationSettingDatabaseConnection.class, name = "ConfigurationSettingDatabaseConnection")})
/*    */ public abstract class ConfigurationSettingDataSource
/*    */   extends ConfigurationSetting
/*    */ {
/*    */   private static final long serialVersionUID = -52970308592095415L;
/*    */   protected long id;
/*    */   
/*    */   public long getId() {
/* 47 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(long id) {
/* 51 */     this.id = id;
/*    */   }
/*    */   
/*    */   public abstract String getValueAsString();
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingDataSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */