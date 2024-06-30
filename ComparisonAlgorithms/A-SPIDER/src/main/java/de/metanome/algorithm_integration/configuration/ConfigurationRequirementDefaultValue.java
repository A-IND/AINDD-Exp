/*     */ package de.metanome.algorithm_integration.configuration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonSubTypes;
/*     */ import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
/*     */ import com.fasterxml.jackson.annotation.JsonTypeInfo;
/*     */ import de.metanome.algorithm_integration.AlgorithmConfigurationException;
/*     */ import javax.xml.bind.annotation.XmlTransient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
/*     */ @JsonSubTypes({@Type(value = ConfigurationRequirementBoolean.class, name = "ConfigurationRequirementBoolean"), @Type(value = ConfigurationRequirementInteger.class, name = "ConfigurationRequirementInteger"), @Type(value = ConfigurationRequirementListBox.class, name = "ConfigurationRequirementListBox"), @Type(value = ConfigurationRequirementString.class, name = "ConfigurationRequirementString"), @Type(value = ConfigurationRequirementCheckBox.class, name = "ConfigurationRequirementCheckBox")})
/*     */ public abstract class ConfigurationRequirementDefaultValue<T, S extends ConfigurationSettingPrimitive<T>>
/*     */   extends ConfigurationRequirement<S>
/*     */ {
/*     */   private static final long serialVersionUID = -9157771032477423545L;
/*     */   public T[] defaultValues;
/*     */   
/*     */   public ConfigurationRequirementDefaultValue() {}
/*     */   
/*     */   public ConfigurationRequirementDefaultValue(String identifier) {
/*  52 */     super(identifier);
/*     */   }
/*     */   
/*     */   public ConfigurationRequirementDefaultValue(String identifier, int numberOfSettings) {
/*  56 */     super(identifier, numberOfSettings);
/*     */   }
/*     */ 
/*     */   
/*     */   public ConfigurationRequirementDefaultValue(String identifier, int minNumberOfSetting, int maxNumberOfSetting) {
/*  61 */     super(identifier, minNumberOfSetting, maxNumberOfSetting);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public final void checkAndSetDefaultValues(T... values) throws AlgorithmConfigurationException {
/*     */     try {
/*  75 */       checkNumberOfSettings(values.length);
/*  76 */     } catch (AlgorithmConfigurationException e) {
/*  77 */       throw new AlgorithmConfigurationException("The number of default values does not match the number of settings.");
/*     */     } 
/*     */ 
/*     */     
/*  81 */     this.defaultValues = values;
/*  82 */     applyDefaultValues();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public T getDefaultValue(int index) {
/*  91 */     if (this.defaultValues != null && this.defaultValues.length > index) {
/*  92 */       return this.defaultValues[index];
/*     */     }
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void applyDefaultValues() {
/* 102 */     if (this.defaultValues == null || this.settings == null || this.defaultValues.length != ((ConfigurationSettingPrimitive[])this.settings).length) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 107 */     for (int i = 0; i < ((ConfigurationSettingPrimitive[])this.settings).length; i++) {
/* 108 */       ((ConfigurationSettingPrimitive[])this.settings)[i].setValue(this.defaultValues[i]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T[] getDefaultValues() {
/* 118 */     return this.defaultValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultValues(T[] defaultValues) {
/* 127 */     this.defaultValues = defaultValues;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public S[] getSettings() {
/* 135 */     return this.settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSettings(S... settings) {
/* 143 */     this.settings = settings;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationRequirementDefaultValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */