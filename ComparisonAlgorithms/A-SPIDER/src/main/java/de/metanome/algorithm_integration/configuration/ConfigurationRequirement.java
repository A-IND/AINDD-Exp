/*     */ package de.metanome.algorithm_integration.configuration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonSubTypes;
/*     */ import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
/*     */ import com.fasterxml.jackson.annotation.JsonTypeInfo;
/*     */ import de.metanome.algorithm_integration.AlgorithmConfigurationException;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.Serializable;
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
/*     */ @JsonSubTypes({@Type(value = ConfigurationRequirementBoolean.class, name = "ConfigurationRequirementBoolean"), @Type(value = ConfigurationRequirementDatabaseConnection.class, name = "ConfigurationRequirementDatabaseConnection"), @Type(value = ConfigurationRequirementFileInput.class, name = "ConfigurationRequirementFileInput"), @Type(value = ConfigurationRequirementInteger.class, name = "ConfigurationRequirementInteger"), @Type(value = ConfigurationRequirementListBox.class, name = "ConfigurationRequirementListBox"), @Type(value = ConfigurationRequirementCheckBox.class, name = "ConfigurationRequirementCheckBox"), @Type(value = ConfigurationRequirementRelationalInput.class, name = "ConfigurationRequirementRelationalInput"), @Type(value = ConfigurationRequirementString.class, name = "ConfigurationRequirementString"), @Type(value = ConfigurationRequirementTableInput.class, name = "ConfigurationRequirementTableInput")})
/*     */ public abstract class ConfigurationRequirement<T extends ConfigurationSetting>
/*     */   implements Serializable
/*     */ {
/*     */   public static final int ARBITRARY_NUMBER_OF_VALUES = -1;
/*     */   private static final long serialVersionUID = -821916342930792349L;
/*     */   protected String identifier;
/*     */   protected boolean required;
/*     */   protected int numberOfSettings;
/*     */   protected int minNumberOfSettings;
/*     */   protected int maxNumberOfSettings;
/*     */   public T[] settings;
/*     */   
/*     */   public ConfigurationRequirement() {}
/*     */   
/*     */   public ConfigurationRequirement(String identifier) {
/*  78 */     this(identifier, 1, 1);
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
/*     */   
/*     */   public ConfigurationRequirement(String identifier, int numberOfSettings) {
/*  91 */     this(identifier, numberOfSettings, numberOfSettings);
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
/*     */ 
/*     */   
/*     */   public ConfigurationRequirement(String identifier, int minNumberOfSettings, int maxNumberOfSettings) {
/* 105 */     this.identifier = identifier;
/* 106 */     this.minNumberOfSettings = minNumberOfSettings;
/* 107 */     this.maxNumberOfSettings = maxNumberOfSettings;
/* 108 */     this.numberOfSettings = maxNumberOfSettings;
/* 109 */     this.required = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIdentifier() {
/* 116 */     return this.identifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isFixNumberOfSettings() {
/* 123 */     return Boolean.valueOf((this.maxNumberOfSettings == this.minNumberOfSettings));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMinNumberOfSettings() {
/* 130 */     return this.minNumberOfSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxNumberOfSettings() {
/* 137 */     return this.maxNumberOfSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfSettings() {
/* 144 */     return this.numberOfSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T[] getSettings() {
/* 151 */     return this.settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSettings(T... settings) {
/* 160 */     this.settings = settings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRequired() {
/* 167 */     return this.required;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRequired(boolean required) {
/* 177 */     this.required = required;
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
/*     */   
/*     */   @XmlTransient
/*     */   protected void checkNumberOfSettings(int number) throws AlgorithmConfigurationException {
/* 191 */     if (this.required && this.numberOfSettings != -1 && number != this.numberOfSettings && (number < this.minNumberOfSettings || number > this.maxNumberOfSettings))
/*     */     {
/*     */       
/* 194 */       throw new AlgorithmConfigurationException("The number of settings does not match the expected number!");
/*     */     }
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
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public final void checkAndSetSettings(T... settings) throws AlgorithmConfigurationException {
/* 211 */     checkNumberOfSettings(settings.length);
/* 212 */     this.settings = settings;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   public abstract ConfigurationValue build(ConfigurationFactory paramConfigurationFactory) throws AlgorithmConfigurationException, FileNotFoundException;
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationRequirement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */