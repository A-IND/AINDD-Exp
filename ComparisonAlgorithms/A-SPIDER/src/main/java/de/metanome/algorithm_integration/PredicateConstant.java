/*     */ package de.metanome.algorithm_integration;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonCreator;
/*     */ import com.fasterxml.jackson.annotation.JsonProperty;
/*     */ import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
/*     */ 
/*     */ public class PredicateConstant<T extends Comparable<T>>
/*     */   implements Predicate
/*     */ {
/*     */   private ColumnIdentifier column1;
/*     */   private int index1;
/*     */   private Operator op;
/*     */   @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
/*     */   private T constant;
/*     */   
/*     */   @JsonCreator
/*     */   public PredicateConstant(@JsonProperty("column1") ColumnIdentifier column1, @JsonProperty("index1") int index1, @JsonProperty("op") Operator op, @JsonProperty("constant") T constant) {
/*  40 */     this.column1 = column1;
/*  41 */     this.index1 = index1;
/*  42 */     this.op = op;
/*  43 */     this.constant = constant;
/*     */   }
/*     */   
/*     */   public ColumnIdentifier getColumn1() {
/*  47 */     return this.column1;
/*     */   }
/*     */   
/*     */   public int getIndex1() {
/*  51 */     return this.index1;
/*     */   }
/*     */   
/*     */   public Operator getOp() {
/*  55 */     return this.op;
/*     */   }
/*     */   
/*     */   public T getConstant() {
/*  59 */     return this.constant;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<ColumnIdentifier> getColumnIdentifiers() {
/*  64 */     return Arrays.asList(new ColumnIdentifier[] { this.column1 });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  70 */     return "t" + this.index1 + "." + this.column1 + this.op.getShortString() + this.constant;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  75 */     int prime = 31;
/*  76 */     int result = 1;
/*  77 */     result = 31 * result + ((this.column1 == null) ? 0 : this.column1.hashCode());
/*  78 */     result = 31 * result + ((this.constant == null) ? 0 : this.constant.hashCode());
/*  79 */     result = 31 * result + this.index1;
/*  80 */     result = 31 * result + ((this.op == null) ? 0 : this.op.hashCode());
/*  81 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  87 */     if (this == obj)
/*  88 */       return true; 
/*  89 */     if (obj == null)
/*  90 */       return false; 
/*  91 */     if (getClass() != obj.getClass())
/*  92 */       return false; 
/*  93 */     PredicateConstant other = (PredicateConstant)obj;
/*  94 */     if (this.column1 == null) {
/*  95 */       if (other.column1 != null)
/*  96 */         return false; 
/*  97 */     } else if (!this.column1.equals(other.column1)) {
/*  98 */       return false;
/*  99 */     }  if (this.constant == null) {
/* 100 */       if (other.constant != null)
/* 101 */         return false; 
/* 102 */     } else if (!this.constant.equals(other.constant)) {
/* 103 */       return false;
/* 104 */     }  if (this.index1 != other.index1)
/* 105 */       return false; 
/* 106 */     if (this.op != other.op)
/* 107 */       return false; 
/* 108 */     return true;
/*     */   }
/*     */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\PredicateConstant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */