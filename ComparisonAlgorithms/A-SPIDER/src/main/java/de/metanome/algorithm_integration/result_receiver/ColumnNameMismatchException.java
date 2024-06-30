/*    */ package de.metanome.algorithm_integration.result_receiver;
/*    */ 
/*    */ import de.metanome.algorithm_integration.AlgorithmExecutionException;
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
/*    */ public class ColumnNameMismatchException
/*    */   extends AlgorithmExecutionException
/*    */ {
/*    */   private static final long serialVersionUID = -8252109115553025346L;
/*    */   
/*    */   public ColumnNameMismatchException(String message) {
/* 26 */     super(message);
/*    */   }
/*    */   
/*    */   public ColumnNameMismatchException(String message, Throwable cause) {
/* 30 */     super(message, cause);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\result_receiver\ColumnNameMismatchException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */