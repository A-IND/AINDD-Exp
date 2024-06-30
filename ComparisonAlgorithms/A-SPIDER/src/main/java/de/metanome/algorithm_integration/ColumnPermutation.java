/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import com.google.common.base.Joiner;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class ColumnPermutation
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8843040353094470475L;
/*     */   protected List<ColumnIdentifier> columnIdentifiers;
/*     */   
/*     */   public ColumnPermutation() {
/*  41 */     this.columnIdentifiers = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnPermutation(ColumnIdentifier... columnIdentifier) {
/*  50 */     this.columnIdentifiers = Arrays.asList(columnIdentifier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ColumnIdentifier> getColumnIdentifiers() {
/*  59 */     return this.columnIdentifiers;
/*     */   }
/*     */   
/*     */   public void setColumnIdentifiers(List<ColumnIdentifier> identifiers) {
/*  63 */     this.columnIdentifiers = identifiers;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  68 */     return this.columnIdentifiers.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) throws NullPointerException {
/*  78 */     List<String> cis = new ArrayList<>();
/*  79 */     for (ColumnIdentifier ci : this.columnIdentifiers) {
/*  80 */       cis.add(ci.toString(tableMapping, columnMapping));
/*     */     }
/*  82 */     return Joiner.on(",").join(cis);
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
/*     */   public static ColumnPermutation fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/*  94 */     String[] parts = str.split(",");
/*     */     
/*  96 */     ColumnIdentifier[] identifiers = new ColumnIdentifier[parts.length];
/*  97 */     for (int i = 0; i < parts.length; i++) {
/*  98 */       identifiers[i] = ColumnIdentifier.fromString(tableMapping, columnMapping, parts[i].trim());
/*     */     }
/* 100 */     return new ColumnPermutation(identifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 105 */     int prime = 31;
/* 106 */     int result = 1;
/*     */ 
/*     */ 
/*     */     
/* 110 */     result = 31 * result + ((this.columnIdentifiers == null) ? 0 : this.columnIdentifiers.hashCode());
/* 111 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 116 */     if (this == obj) {
/* 117 */       return true;
/*     */     }
/* 119 */     if (obj == null) {
/* 120 */       return false;
/*     */     }
/* 122 */     if (getClass() != obj.getClass()) {
/* 123 */       return false;
/*     */     }
/* 125 */     ColumnPermutation other = (ColumnPermutation)obj;
/* 126 */     if (this.columnIdentifiers == null) {
/* 127 */       if (other.columnIdentifiers != null) {
/* 128 */         return false;
/*     */       }
/* 130 */     } else if (!this.columnIdentifiers.equals(other.columnIdentifiers)) {
/* 131 */       return false;
/*     */     } 
/* 133 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\ColumnPermutation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */