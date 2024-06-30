/*     */ package de.metanome.algorithm_integration.configuration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import com.google.common.annotations.GwtIncompatible;
/*     */ import de.metanome.algorithm_integration.AlgorithmConfigurationException;
/*     */ import de.metanome.algorithm_integration.input.RelationalInputGeneratorInitializer;
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
/*     */ @JsonTypeName("ConfigurationSettingTableInput")
/*     */ public class ConfigurationSettingTableInput
/*     */   extends ConfigurationSettingRelationalInput
/*     */ {
/*     */   private static final long serialVersionUID = -6370969600042372223L;
/*     */   private String table;
/*     */   private ConfigurationSettingDatabaseConnection databaseConnection;
/*  41 */   public String type = "ConfigurationSettingTableInput";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigurationSettingTableInput() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigurationSettingTableInput(String table, ConfigurationSettingDatabaseConnection databaseConnection) {
/*  51 */     this.table = table;
/*  52 */     this.databaseConnection = databaseConnection;
/*     */   }
/*     */   
/*     */   public String getTable() {
/*  56 */     return this.table;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingTableInput setTable(String table) {
/*  60 */     this.table = table;
/*  61 */     return this;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingDatabaseConnection getDatabaseConnection() {
/*  65 */     return this.databaseConnection;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingTableInput setDatabaseConnection(ConfigurationSettingDatabaseConnection databaseConnection) {
/*  69 */     this.databaseConnection = databaseConnection;
/*  70 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public String getValueAsString() {
/*  76 */     return getIdentifier(this.table, this.databaseConnection.getDbUrl(), this.databaseConnection.getUsername(), this.databaseConnection.getSystem());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   @GwtIncompatible("Can only be called from backend.")
/*     */   public void generate(RelationalInputGeneratorInitializer initializer) throws AlgorithmConfigurationException {
/*  87 */     initializer.initialize(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  92 */     if (this == o) {
/*  93 */       return true;
/*     */     }
/*  95 */     if (o == null || getClass() != o.getClass()) {
/*  96 */       return false;
/*     */     }
/*     */     
/*  99 */     ConfigurationSettingTableInput that = (ConfigurationSettingTableInput)o;
/*     */     
/* 101 */     if (!this.table.equals(that.table)) {
/* 102 */       return false;
/*     */     }
/* 104 */     if (!this.databaseConnection.equals(that.databaseConnection)) {
/* 105 */       return false;
/*     */     }
/*     */     
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 113 */     int result = this.table.hashCode();
/* 114 */     result = 31 * result + this.databaseConnection.hashCode();
/* 115 */     return result;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   @JsonIgnore
/*     */   public static String getIdentifier(String table, String url, String username, DbSystem system) {
/* 121 */     return String.format("%s; %s", new Object[] { table, ConfigurationSettingDatabaseConnection.getIdentifier(url, username, system) });
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingTableInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */