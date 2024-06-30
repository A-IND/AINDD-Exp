/*    */ package de.metanome.algorithm_integration.configuration;
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
/*    */ public enum DbSystem
/*    */ {
/* 24 */   DB2, MySQL, PostgreSQL, HANA, Oracle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String[] names() {
/* 32 */     DbSystem[] systems = values();
/* 33 */     String[] names = new String[systems.length];
/*    */     
/* 35 */     for (int i = 0; i < systems.length; i++) {
/* 36 */       names[i] = systems[i].name();
/*    */     }
/*    */     
/* 39 */     return names;
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\configuration\DbSystem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */