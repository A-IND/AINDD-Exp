/*    */ package de.uni_potsdam.hpi.utils;
/*    */ 
/*    */ import java.util.logging.Handler;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ 
/*    */ 
/*    */ public class LoggingUtils
/*    */ {
/*    */   public static void disableLogging() {
/* 11 */     Logger root = Logger.getLogger("");
/* 12 */     Handler[] handlers = root.getHandlers();
/* 13 */     for (Handler handler : handlers)
/* 14 */       handler.setLevel(Level.OFF); 
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\d\\uni_potsdam\hp\\utils\LoggingUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */