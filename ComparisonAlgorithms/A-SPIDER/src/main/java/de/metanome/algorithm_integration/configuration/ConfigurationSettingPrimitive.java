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
/*    */ @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
/*    */ @JsonSubTypes({@Type(value = ConfigurationSettingBoolean.class, name = "ConfigurationSettingBoolean"), @Type(value = ConfigurationSettingInteger.class, name = "ConfigurationSettingInteger"), @Type(value = ConfigurationSettingListBox.class, name = "ConfigurationSettingListBox"), @Type(value = ConfigurationSettingString.class, name = "ConfigurationSettingString"), @Type(value = ConfigurationSettingCheckBox.class, name = "ConfigurationSettingCheckBox")})
/*    */ public abstract class ConfigurationSettingPrimitive<T>
/*    */   extends ConfigurationSetting
/*    */ {
/*    */   private static final long serialVersionUID = 6622779246769767913L;
/*    */   public T value;
/*    */   
/*    */   public ConfigurationSettingPrimitive() {}
/*    */   
/*    */   public ConfigurationSettingPrimitive(T value) {
/* 46 */     this.value = value;
/*    */   }
/*    */   
/*    */   public T getValue() {
/* 50 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(T value) {
/* 54 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingPrimitive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */