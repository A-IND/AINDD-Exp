/*    */ package de.metanome.algorithm_integration.results;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*    */ import de.metanome.algorithm_integration.Predicate;
/*    */ import de.metanome.algorithm_integration.result_receiver.ColumnNameMismatchException;
/*    */ import de.metanome.algorithm_integration.result_receiver.CouldNotReceiveResultException;
/*    */ import de.metanome.algorithm_integration.result_receiver.OmniscientResultReceiver;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DenialConstraint
/*    */   implements Result
/*    */ {
/*    */   private static final long serialVersionUID = -3940142594679991666L;
/*    */   public static final String NOT = "¬";
/*    */   public static final String AND = "^";
/*    */   private final Set<Predicate> predicates;
/*    */   
/*    */   public DenialConstraint() {
/* 39 */     this.predicates = new HashSet<>();
/*    */   }
/*    */   
/*    */   public DenialConstraint(Predicate... predicates) {
/* 43 */     this.predicates = new HashSet<>(Arrays.asList(predicates));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void sendResultTo(OmniscientResultReceiver resultReceiver) throws CouldNotReceiveResultException, ColumnNameMismatchException {
/* 49 */     resultReceiver.receiveResult(this);
/*    */   }
/*    */   
/*    */   public Collection<Predicate> getPredicates() {
/* 53 */     return Collections.unmodifiableCollection(this.predicates);
/*    */   }
/*    */   
/*    */   @JsonIgnore
/*    */   public int getPredicateCount() {
/* 58 */     return this.predicates.size();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     return "¬(" + (String)this.predicates
/* 65 */       .stream().map(p -> p.toString()).sorted().collect(Collectors.joining("^")) + ")";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 71 */     int prime = 31;
/* 72 */     int result = 1;
/* 73 */     result = 31 * result + ((this.predicates == null) ? 0 : this.predicates.hashCode());
/* 74 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 79 */     if (this == obj)
/* 80 */       return true; 
/* 81 */     if (obj == null)
/* 82 */       return false; 
/* 83 */     if (getClass() != obj.getClass())
/* 84 */       return false; 
/* 85 */     DenialConstraint other = (DenialConstraint)obj;
/* 86 */     if (this.predicates == null) {
/* 87 */       if (other.predicates != null)
/* 88 */         return false; 
/* 89 */     } else if (!this.predicates.equals(other.predicates)) {
/* 90 */       return false;
/* 91 */     }  return true;
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\DenialConstraint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */