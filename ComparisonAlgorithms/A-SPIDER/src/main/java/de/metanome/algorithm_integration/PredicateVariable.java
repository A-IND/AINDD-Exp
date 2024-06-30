/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonCreator;
/*     */ import com.fasterxml.jackson.annotation.JsonProperty;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
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
/*     */ public class PredicateVariable
/*     */   implements Predicate
/*     */ {
/*     */   private ColumnIdentifier column1;
/*     */   private int index1;
/*     */   private Operator op;
/*     */   private ColumnIdentifier column2;
/*     */   private int index2;
/*     */   
/*     */   @JsonCreator
/*     */   public PredicateVariable(@JsonProperty("column1") ColumnIdentifier column1, @JsonProperty("index1") int index1, @JsonProperty("op") Operator op, @JsonProperty("column2") ColumnIdentifier column2, @JsonProperty("index2") int index2) {
/*  38 */     this.column1 = column1;
/*  39 */     this.index1 = index1;
/*  40 */     this.op = op;
/*  41 */     this.column2 = column2;
/*  42 */     this.index2 = index2;
/*     */   }
/*     */   
/*     */   public ColumnIdentifier getColumn1() {
/*  46 */     return this.column1;
/*     */   }
/*     */   
/*     */   public int getIndex1() {
/*  50 */     return this.index1;
/*     */   }
/*     */   
/*     */   public Operator getOp() {
/*  54 */     return this.op;
/*     */   }
/*     */   
/*     */   public ColumnIdentifier getColumn2() {
/*  58 */     return this.column2;
/*     */   }
/*     */   
/*     */   public int getIndex2() {
/*  62 */     return this.index2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<ColumnIdentifier> getColumnIdentifiers() {
/*  67 */     return Arrays.asList(new ColumnIdentifier[] { this.column1, this.column2 });
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  72 */     return "t" + this.index1 + "." + this.column1 + this.op.getShortString() + "t" + this.index2 + "." + this.column2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  77 */     int prime = 31;
/*  78 */     int result = 1;
/*  79 */     result = 31 * result + ((this.column1 == null) ? 0 : this.column1.hashCode());
/*  80 */     result = 31 * result + ((this.column2 == null) ? 0 : this.column2.hashCode());
/*  81 */     result = 31 * result + this.index1;
/*  82 */     result = 31 * result + this.index2;
/*  83 */     result = 31 * result + ((this.op == null) ? 0 : this.op.hashCode());
/*  84 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  89 */     if (this == obj)
/*  90 */       return true; 
/*  91 */     if (obj == null)
/*  92 */       return false; 
/*  93 */     if (getClass() != obj.getClass())
/*  94 */       return false; 
/*  95 */     PredicateVariable other = (PredicateVariable)obj;
/*  96 */     if (this.column1 == null) {
/*  97 */       if (other.column1 != null)
/*  98 */         return false; 
/*  99 */     } else if (!this.column1.equals(other.column1)) {
/* 100 */       return false;
/* 101 */     }  if (this.column2 == null) {
/* 102 */       if (other.column2 != null)
/* 103 */         return false; 
/* 104 */     } else if (!this.column2.equals(other.column2)) {
/* 105 */       return false;
/* 106 */     }  if (this.index1 != other.index1)
/* 107 */       return false; 
/* 108 */     if (this.index2 != other.index2)
/* 109 */       return false; 
/* 110 */     if (this.op != other.op)
/* 111 */       return false; 
/* 112 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\PredicateVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */