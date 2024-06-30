/*     */ package de.metanome.algorithm_integration.configuration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
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
/*     */ @JsonTypeName("ConfigurationSettingDatabaseConnection")
/*     */ public class ConfigurationSettingDatabaseConnection
/*     */   extends ConfigurationSettingDataSource
/*     */ {
/*     */   private static final long serialVersionUID = -7220041878087963L;
/*     */   private String dbUrl;
/*     */   private String username;
/*     */   private String password;
/*     */   private DbSystem system;
/*  40 */   public String type = "ConfigurationSettingDatabaseConnection";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigurationSettingDatabaseConnection() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ConfigurationSettingDatabaseConnection(String dbUrl, String username, String password, DbSystem system) {
/*  50 */     this.dbUrl = dbUrl;
/*  51 */     this.username = username;
/*  52 */     this.password = password;
/*  53 */     this.system = system;
/*     */   }
/*     */   
/*     */   public String getDbUrl() {
/*  57 */     return this.dbUrl;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingDatabaseConnection setDbUrl(String dbUrl) {
/*  61 */     this.dbUrl = dbUrl;
/*  62 */     return this;
/*     */   }
/*     */   
/*     */   public String getUsername() {
/*  66 */     return this.username;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingDatabaseConnection setUsername(String username) {
/*  70 */     this.username = username;
/*  71 */     return this;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/*  75 */     return this.password;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingDatabaseConnection setPassword(String password) {
/*  79 */     this.password = password;
/*  80 */     return this;
/*     */   }
/*     */   
/*     */   public DbSystem getSystem() {
/*  84 */     return this.system;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingDatabaseConnection setSystem(DbSystem system) {
/*  88 */     this.system = system;
/*  89 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   @JsonIgnore
/*     */   public String getValueAsString() {
/*  96 */     return getIdentifier(this.dbUrl, this.username, this.system);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 101 */     if (this == o) {
/* 102 */       return true;
/*     */     }
/* 104 */     if (o == null || getClass() != o.getClass()) {
/* 105 */       return false;
/*     */     }
/*     */     
/* 108 */     ConfigurationSettingDatabaseConnection that = (ConfigurationSettingDatabaseConnection)o;
/*     */     
/* 110 */     if (!this.dbUrl.equals(that.dbUrl)) {
/* 111 */       return false;
/*     */     }
/* 113 */     if (!this.password.equals(that.password)) {
/* 114 */       return false;
/*     */     }
/* 116 */     if (this.system != that.system) {
/* 117 */       return false;
/*     */     }
/* 119 */     if (!this.username.equals(that.username)) {
/* 120 */       return false;
/*     */     }
/*     */     
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 128 */     int result = this.dbUrl.hashCode();
/* 129 */     result = 31 * result + this.username.hashCode();
/* 130 */     result = 31 * result + this.password.hashCode();
/* 131 */     result = 31 * result + this.system.hashCode();
/* 132 */     return result;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   @JsonIgnore
/*     */   public static String getIdentifier(String url, String username, DbSystem system) {
/* 138 */     return String.format("%s; %s; %s", new Object[] { url, username, system.name() });
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingDatabaseConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */