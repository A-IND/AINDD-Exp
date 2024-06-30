/*     */ package de.metanome.algorithm_integration.configuration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
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
/*     */ @JsonTypeName("ConfigurationSettingFileInput")
/*     */ public class ConfigurationSettingFileInput
/*     */   extends ConfigurationSettingRelationalInput
/*     */   implements Comparable<Object>
/*     */ {
/*     */   public static final char DEFAULT_SEPARATOR = ',';
/*     */   public static final char DEFAULT_QUOTE = '"';
/*     */   public static final char DEFAULT_ESCAPE = '\\';
/*     */   public static final boolean DEFAULT_STRICTQUOTES = false;
/*     */   public static final boolean DEFAULT_IGNORELEADINGWHITESPACE = true;
/*     */   public static final int DEFAULT_SKIPLINES = 0;
/*     */   public static final boolean DEFAULT_HEADER = true;
/*     */   public static final boolean DEFAULT_SKIPDIFFERINGLINES = false;
/*     */   public static final String DEFAULT_NULL_VALUE = "";
/*     */   private static final long serialVersionUID = -8315546806138520520L;
/*  47 */   public String type = "ConfigurationSettingFileInput";
/*     */   
/*     */   private String fileName;
/*     */   
/*     */   private boolean advanced;
/*     */   
/*     */   private String separatorChar;
/*     */   
/*     */   private String quoteChar;
/*     */   
/*     */   private String escapeChar;
/*     */   
/*     */   private boolean strictQuotes;
/*     */   
/*     */   private boolean ignoreLeadingWhiteSpace;
/*     */   
/*     */   private Integer skipLines;
/*     */   
/*     */   private boolean header;
/*     */   private boolean skipDifferingLines;
/*     */   private String nullValue;
/*     */   
/*     */   public ConfigurationSettingFileInput() {}
/*     */   
/*     */   public ConfigurationSettingFileInput(String fileName) {
/*  72 */     this(fileName, false, ',', '"', '\\', false, true, 0, true, false, "");
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
/*     */   public ConfigurationSettingFileInput(String fileName, boolean advanced, char separator, char quote, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace, int line, boolean header, boolean skipDifferingLines, String nullValue) {
/* 105 */     this.fileName = fileName;
/* 106 */     this.advanced = advanced;
/* 107 */     this.separatorChar = String.valueOf(separator);
/* 108 */     this.quoteChar = String.valueOf(quote);
/* 109 */     this.escapeChar = String.valueOf(escape);
/* 110 */     this.strictQuotes = strictQuotes;
/* 111 */     this.ignoreLeadingWhiteSpace = ignoreLeadingWhiteSpace;
/* 112 */     this.skipLines = Integer.valueOf(line);
/* 113 */     this.header = header;
/* 114 */     this.skipDifferingLines = skipDifferingLines;
/* 115 */     this.nullValue = nullValue;
/*     */   }
/*     */   
/*     */   public String getFileName() {
/* 119 */     return this.fileName;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setFileName(String value) {
/* 123 */     this.fileName = value;
/* 124 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isAdvanced() {
/* 128 */     return this.advanced;
/*     */   }
/*     */   
/*     */   public void setAdvanced(boolean value) {
/* 132 */     this.advanced = value;
/*     */   }
/*     */   
/*     */   public String getSeparatorChar() {
/* 136 */     return this.separatorChar;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setSeparatorChar(String value) {
/* 140 */     this.separatorChar = value;
/* 141 */     return this;
/*     */   }
/*     */   
/*     */   public String getQuoteChar() {
/* 145 */     return this.quoteChar;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setQuoteChar(String value) {
/* 149 */     this.quoteChar = value;
/* 150 */     return this;
/*     */   }
/*     */   
/*     */   public String getEscapeChar() {
/* 154 */     return this.escapeChar;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setEscapeChar(String value) {
/* 158 */     this.escapeChar = value;
/* 159 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isStrictQuotes() {
/* 163 */     return this.strictQuotes;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setStrictQuotes(boolean value) {
/* 167 */     this.strictQuotes = value;
/* 168 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isIgnoreLeadingWhiteSpace() {
/* 172 */     return this.ignoreLeadingWhiteSpace;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setIgnoreLeadingWhiteSpace(boolean value) {
/* 176 */     this.ignoreLeadingWhiteSpace = value;
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   public Integer getSkipLines() {
/* 181 */     return this.skipLines;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setSkipLines(int value) {
/* 185 */     this.skipLines = Integer.valueOf(value);
/* 186 */     return this;
/*     */   }
/*     */   
/*     */   public boolean hasHeader() {
/* 190 */     return this.header;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setHeader(boolean header) {
/* 194 */     this.header = header;
/* 195 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isSkipDifferingLines() {
/* 199 */     return this.skipDifferingLines;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setSkipDifferingLines(boolean skipDifferingLines) {
/* 203 */     this.skipDifferingLines = skipDifferingLines;
/* 204 */     return this;
/*     */   }
/*     */   
/*     */   public String getNullValue() {
/* 208 */     return this.nullValue;
/*     */   }
/*     */   
/*     */   public ConfigurationSettingFileInput setNullValue(String nullValue) {
/* 212 */     this.nullValue = nullValue;
/* 213 */     return this;
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   @JsonIgnore
/*     */   public char getSeparatorAsChar() {
/* 219 */     return toChar(this.separatorChar);
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   @JsonIgnore
/*     */   public char getQuoteCharAsChar() {
/* 225 */     return toChar(this.quoteChar);
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   @JsonIgnore
/*     */   public char getEscapeCharAsChar() {
/* 231 */     return toChar(this.escapeChar);
/*     */   }
/*     */   
/*     */   @XmlTransient
/*     */   private char toChar(String str) {
/* 236 */     if (str.isEmpty()) {
/* 237 */       return Character.MIN_VALUE;
/*     */     }
/*     */     
/* 240 */     if (str.startsWith("\\")) {
/* 241 */       switch (str) {
/*     */         case "\\t":
/* 243 */           return '\t';
/*     */         case "\\0":
/* 245 */           return Character.MIN_VALUE;
/*     */         case "\\n":
/* 247 */           return '\n';
/*     */       } 
/*     */     
/*     */     }
/* 251 */     return str.charAt(0);
/*     */   }
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   @JsonIgnore
/*     */   public String getValueAsString() {
/* 258 */     return this.fileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlTransient
/*     */   public void generate(RelationalInputGeneratorInitializer initializer) throws AlgorithmConfigurationException {
/* 268 */     initializer.initialize(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 273 */     if (this == o) {
/* 274 */       return true;
/*     */     }
/* 276 */     if (o == null || getClass() != o.getClass()) {
/* 277 */       return false;
/*     */     }
/*     */     
/* 280 */     ConfigurationSettingFileInput that = (ConfigurationSettingFileInput)o;
/*     */     
/* 282 */     return (this.fileName.equals(that.fileName) && this.separatorChar
/* 283 */       .equals(that.separatorChar) && this.quoteChar
/* 284 */       .equals(that.quoteChar) && this.escapeChar
/* 285 */       .equals(that.escapeChar) && this.nullValue
/* 286 */       .equals(that.nullValue) && this.strictQuotes != that.strictQuotes && this.ignoreLeadingWhiteSpace != that.ignoreLeadingWhiteSpace && this.skipDifferingLines != that.skipDifferingLines && this.header != that.header && 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 291 */       !this.skipLines.equals(that.skipLines));
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object other) {
/* 296 */     if (other == null || getClass() != other.getClass()) {
/* 297 */       return 1;
/*     */     }
/*     */     
/* 300 */     if (equals(other)) {
/* 301 */       return 0;
/*     */     }
/* 303 */     ConfigurationSettingFileInput that = (ConfigurationSettingFileInput)other;
/* 304 */     return getFileName().compareTo(that.getFileName());
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 309 */     int result = this.fileName.hashCode();
/* 310 */     result = 31 * result + this.separatorChar.hashCode();
/* 311 */     result = 31 * result + this.quoteChar.hashCode();
/* 312 */     result = 31 * result + this.escapeChar.hashCode();
/* 313 */     result = 31 * result + this.nullValue.hashCode();
/* 314 */     result = 31 * result + hashCode(this.strictQuotes);
/* 315 */     result = 31 * result + hashCode(this.ignoreLeadingWhiteSpace);
/* 316 */     result = 31 * result + hashCode(this.skipDifferingLines);
/* 317 */     result = 31 * result + hashCode(this.header);
/* 318 */     result = 31 * result + this.skipLines.intValue();
/* 319 */     return result;
/*     */   }
/*     */   
/*     */   private int hashCode(boolean bool) {
/* 323 */     return bool ? 1231 : 1237;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\ConfigurationSettingFileInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */