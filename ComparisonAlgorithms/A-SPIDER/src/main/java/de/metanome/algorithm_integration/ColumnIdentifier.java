/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public class ColumnIdentifier
/*     */   implements Comparable<ColumnIdentifier>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3199299021265706919L;
/*     */   public static final String TABLE_COLUMN_CONCATENATOR = ".";
/*     */   public static final String TABLE_COLUMN_CONCATENATOR_ESC = "\\.";
/*     */   protected String tableIdentifier;
/*     */   protected String columnIdentifier;
/*     */   
/*     */   public ColumnIdentifier() {
/*  37 */     this.tableIdentifier = "";
/*  38 */     this.columnIdentifier = "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnIdentifier(String tableIdentifier, String columnIdentifier) {
/*  46 */     this.tableIdentifier = tableIdentifier;
/*  47 */     this.columnIdentifier = columnIdentifier;
/*     */   }
/*     */   
/*     */   public String getTableIdentifier() {
/*  51 */     return this.tableIdentifier;
/*     */   }
/*     */   
/*     */   public void setTableIdentifier(String tableIdentifier) {
/*  55 */     this.tableIdentifier = tableIdentifier;
/*     */   }
/*     */   
/*     */   public String getColumnIdentifier() {
/*  59 */     return this.columnIdentifier;
/*     */   }
/*     */   
/*     */   public void setColumnIdentifier(String columnIdentifier) {
/*  63 */     this.columnIdentifier = columnIdentifier;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  68 */     if (this.tableIdentifier.isEmpty() && this.columnIdentifier.isEmpty())
/*  69 */       return ""; 
/*  70 */     return this.tableIdentifier + "." + this.columnIdentifier;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString(Map<String, String> tableMapping, Map<String, String> columnMapping) {
/*  81 */     String tableValue = tableMapping.get(this.tableIdentifier);
/*  82 */     String columnStr = tableValue + "." + this.columnIdentifier;
/*  83 */     return columnMapping.get(columnStr);
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
/*     */   public static ColumnIdentifier fromString(Map<String, String> tableMapping, Map<String, String> columnMapping, String str) throws NullPointerException, IndexOutOfBoundsException {
/*  95 */     if (str.isEmpty()) {
/*  96 */       return new ColumnIdentifier();
/*     */     }
/*     */     
/*  99 */     String[] parts = ((String)columnMapping.get(str)).split("\\.", 2);
/* 100 */     String tableKey = parts[0];
/* 101 */     String columnName = parts[1];
/* 102 */     String tableName = tableMapping.get(tableKey);
/*     */     
/* 104 */     return new ColumnIdentifier(tableName, columnName);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 109 */     int prime = 31;
/* 110 */     int result = 1;
/*     */ 
/*     */     
/* 113 */     result = 31 * result + ((this.columnIdentifier == null) ? 0 : this.columnIdentifier.hashCode());
/*     */     
/* 115 */     result = 31 * result + ((this.tableIdentifier == null) ? 0 : this.tableIdentifier.hashCode());
/* 116 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 121 */     if (this == obj) {
/* 122 */       return true;
/*     */     }
/* 124 */     if (obj == null) {
/* 125 */       return false;
/*     */     }
/* 127 */     if (getClass() != obj.getClass()) {
/* 128 */       return false;
/*     */     }
/* 130 */     ColumnIdentifier other = (ColumnIdentifier)obj;
/* 131 */     if (this.columnIdentifier == null) {
/* 132 */       if (other.columnIdentifier != null) {
/* 133 */         return false;
/*     */       }
/* 135 */     } else if (!this.columnIdentifier.equals(other.columnIdentifier)) {
/* 136 */       return false;
/*     */     } 
/* 138 */     if (this.tableIdentifier == null) {
/* 139 */       if (other.tableIdentifier != null) {
/* 140 */         return false;
/*     */       }
/* 142 */     } else if (!this.tableIdentifier.equals(other.tableIdentifier)) {
/* 143 */       return false;
/*     */     } 
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ColumnIdentifier other) {
/*     */     int tableIdentifierComparison;
/* 151 */     if (this.tableIdentifier == null) {
/* 152 */       if (other.tableIdentifier == null)
/* 153 */       { tableIdentifierComparison = 0; }
/*     */       else
/* 155 */       { tableIdentifierComparison = 1; } 
/* 156 */     } else if (other.tableIdentifier == null) {
/* 157 */       tableIdentifierComparison = -1;
/*     */     } else {
/* 159 */       tableIdentifierComparison = this.tableIdentifier.compareTo(other.tableIdentifier);
/*     */     } 
/* 161 */     if (0 != tableIdentifierComparison) {
/* 162 */       return tableIdentifierComparison;
/*     */     }
/* 164 */     return this.columnIdentifier.compareTo(other.columnIdentifier);
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\ColumnIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */