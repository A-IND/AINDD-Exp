/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import com.fasterxml.jackson.annotation.JsonTypeName;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
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
/*     */ @JsonTypeName("ColumnConditionValue")
/*     */ public class ColumnConditionValue
/*     */   implements ColumnCondition
/*     */ {
/*     */   private static final long serialVersionUID = -1479293662771420654L;
/*     */   protected ColumnIdentifier columnIdentifier;
/*     */   protected String columnValue;
/*     */   protected boolean isNegated;
/*  37 */   protected float coverage = 0.0F;
/*     */   
/*     */   public ColumnConditionValue() {
/*  40 */     this.columnIdentifier = new ColumnIdentifier();
/*  41 */     this.columnValue = "";
/*  42 */     this.isNegated = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColumnConditionValue(ColumnIdentifier columnIdentifier, String columnValue) {
/*  53 */     this.columnIdentifier = columnIdentifier;
/*  54 */     this.columnValue = columnValue;
/*  55 */     this.isNegated = false;
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
/*     */   public ColumnConditionValue(ColumnIdentifier columnIdentifier, String columnValue, boolean isNegated) {
/*  68 */     this(columnIdentifier, columnValue);
/*  69 */     this.isNegated = isNegated;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getCoverage() {
/*  74 */     return this.coverage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCoverage(float coverage) {
/*  79 */     this.coverage = coverage;
/*     */   }
/*     */   
/*     */   public ColumnIdentifier getColumnIdentifier() {
/*  83 */     return this.columnIdentifier;
/*     */   }
/*     */   
/*     */   public void setColumnIdentifier(ColumnIdentifier columnIdentifier) {
/*  87 */     this.columnIdentifier = columnIdentifier;
/*     */   }
/*     */   
/*     */   public String getColumnValue() {
/*  91 */     return this.columnValue;
/*     */   }
/*     */   
/*     */   public void setColumnValue(String columnValue) {
/*  95 */     this.columnValue = columnValue;
/*     */   }
/*     */   
/*     */   public boolean isNegated() {
/*  99 */     return this.isNegated;
/*     */   }
/*     */   
/*     */   public void setNegated(boolean isNegated) {
/* 103 */     this.isNegated = isNegated;
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   public TreeSet<ColumnIdentifier> getContainedColumns() {
/* 109 */     TreeSet<ColumnIdentifier> result = new TreeSet<>();
/* 110 */     result.add(this.columnIdentifier);
/* 111 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   public List<Map<ColumnIdentifier, String>> getPatternConditions() {
/* 117 */     List<Map<ColumnIdentifier, String>> result = new LinkedList<>();
/* 118 */     Map<ColumnIdentifier, String> condition = new TreeMap<>();
/* 119 */     condition.put(this.columnIdentifier, this.columnValue);
/* 120 */     result.add(condition);
/* 121 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 126 */     StringBuilder builder = new StringBuilder();
/* 127 */     builder.append(this.columnIdentifier.toString());
/* 128 */     builder.append("= ");
/* 129 */     if (this.isNegated) {
/* 130 */       builder.append("¬");
/*     */     }
/* 132 */     builder.append(this.columnValue);
/* 133 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public ColumnCondition add(ColumnCondition value) {
/* 138 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 143 */     if (this == o) {
/* 144 */       return true;
/*     */     }
/* 146 */     if (o == null || getClass() != o.getClass()) {
/* 147 */       return false;
/*     */     }
/* 149 */     ColumnConditionValue that = (ColumnConditionValue)o;
/*     */     
/* 151 */     if (this.isNegated != that.isNegated) {
/* 152 */       return false;
/*     */     }
/* 154 */     if (!this.columnIdentifier.equals(that.columnIdentifier)) {
/* 155 */       return false;
/*     */     }
/* 157 */     if (!this.columnValue.equals(that.columnValue)) {
/* 158 */       return false;
/*     */     }
/* 160 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 165 */     int result = this.columnIdentifier.hashCode();
/* 166 */     result = 31 * result + this.columnValue.hashCode();
/* 167 */     result = 31 * result + (this.isNegated ? 1 : 0);
/* 168 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(ColumnCondition o) {
/* 173 */     if (o instanceof ColumnConditionValue) {
/* 174 */       ColumnConditionValue other = (ColumnConditionValue)o;
/* 175 */       if (other.isNegated == this.isNegated) {
/* 176 */         int columnComparison = this.columnIdentifier.compareTo(other.columnIdentifier);
/* 177 */         if (columnComparison != 0) {
/* 178 */           return columnComparison;
/*     */         }
/* 180 */         return this.columnValue.compareTo(other.columnValue);
/*     */       } 
/*     */       
/* 183 */       if (this.isNegated) {
/* 184 */         return 1;
/*     */       }
/* 186 */       return -1;
/*     */     } 
/*     */ 
/*     */     
/* 190 */     return -1;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\ColumnConditionValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */