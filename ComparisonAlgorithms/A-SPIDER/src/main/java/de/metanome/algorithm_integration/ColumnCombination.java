/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import com.google.common.base.Joiner;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
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
/*     */ public class ColumnCombination
/*     */   implements Serializable, Comparable<Object>
/*     */ {
/*     */   public static final String COLUMN_CONNECTOR = ",";
/*     */   private static final long serialVersionUID = -1675606730574675390L;
/*     */   protected Set<ColumnIdentifier> columnIdentifiers;
/*     */   
/*     */   public ColumnCombination() {
/*  39 */     this.columnIdentifiers = new TreeSet<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnCombination(ColumnIdentifier... columnIdentifier) {
/*  48 */     this.columnIdentifiers = new TreeSet<>(Arrays.asList(columnIdentifier));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<ColumnIdentifier> getColumnIdentifiers() {
/*  57 */     return this.columnIdentifiers;
/*     */   }
/*     */   
/*     */   public void setColumnIdentifiers(Set<ColumnIdentifier> identifiers) {
/*  61 */     this.columnIdentifiers = identifiers;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  66 */     return this.columnIdentifiers.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) throws NullPointerException {
/*  76 */     List<String> cis = new ArrayList<>();
/*  77 */     for (ColumnIdentifier ci : this.columnIdentifiers) {
/*  78 */       cis.add(ci.toString(tableMapping, columnMapping));
/*     */     }
/*  80 */     return Joiner.on(",").join(cis);
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
/*     */   public static ColumnCombination fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/*  92 */     String[] parts = str.split(",");
/*     */     
/*  94 */     ColumnIdentifier[] identifiers = new ColumnIdentifier[parts.length];
/*  95 */     for (int i = 0; i < parts.length; i++) {
/*  96 */       identifiers[i] = ColumnIdentifier.fromString(tableMapping, columnMapping, parts[i].trim());
/*     */     }
/*  98 */     return new ColumnCombination(identifiers);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 103 */     int prime = 31;
/* 104 */     int result = 1;
/*     */ 
/*     */ 
/*     */     
/* 108 */     result = 31 * result + ((this.columnIdentifiers == null) ? 0 : this.columnIdentifiers.hashCode());
/* 109 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(Object o) {
/* 114 */     if (o != null && o instanceof ColumnCombination) {
/* 115 */       ColumnCombination other = (ColumnCombination)o;
/*     */       
/* 117 */       int lengthComparison = this.columnIdentifiers.size() - other.columnIdentifiers.size();
/* 118 */       if (lengthComparison != 0) {
/* 119 */         return lengthComparison;
/*     */       }
/*     */       
/* 122 */       Iterator<ColumnIdentifier> otherIterator = other.columnIdentifiers.iterator();
/* 123 */       int equalCount = 0;
/* 124 */       int negativeCount = 0;
/* 125 */       int positiveCount = 0;
/*     */       
/* 127 */       while (otherIterator.hasNext()) {
/* 128 */         ColumnIdentifier currentOther = otherIterator.next();
/*     */ 
/*     */         
/* 131 */         for (ColumnIdentifier currentThis : this.columnIdentifiers) {
/* 132 */           int currentComparison = currentThis.compareTo(currentOther);
/* 133 */           if (currentComparison == 0) {
/* 134 */             equalCount++; continue;
/* 135 */           }  if (currentComparison > 0) {
/* 136 */             positiveCount++; continue;
/* 137 */           }  if (currentComparison < 0) {
/* 138 */             negativeCount++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 143 */       if (equalCount == this.columnIdentifiers.size())
/* 144 */         return 0; 
/* 145 */       if (positiveCount > negativeCount) {
/* 146 */         return 1;
/*     */       }
/* 148 */       return -1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 153 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 159 */     if (this == obj) {
/* 160 */       return true;
/*     */     }
/* 162 */     if (obj == null) {
/* 163 */       return false;
/*     */     }
/* 165 */     if (getClass() != obj.getClass()) {
/* 166 */       return false;
/*     */     }
/* 168 */     ColumnCombination other = (ColumnCombination)obj;
/* 169 */     if (this.columnIdentifiers == null) {
/* 170 */       if (other.columnIdentifiers != null) {
/* 171 */         return false;
/*     */       }
/* 173 */     } else if (!this.columnIdentifiers.equals(other.columnIdentifiers)) {
/* 174 */       return false;
/*     */     } 
/* 176 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\ColumnCombination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */