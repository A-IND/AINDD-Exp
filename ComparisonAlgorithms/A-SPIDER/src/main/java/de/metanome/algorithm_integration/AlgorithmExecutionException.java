/*    */ package de.metanome.algorithm_integration;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AlgorithmExecutionException
/*    */   extends Exception
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 7685236041703421431L;
/*    */   
/*    */   protected AlgorithmExecutionException() {}
/*    */   
/*    */   public AlgorithmExecutionException(String message) {
/* 34 */     super(message);
/*    */   }
/*    */   
/*    */   public AlgorithmExecutionException(String message, Throwable cause) {
/* 38 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\AlgorithmExecutionException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */