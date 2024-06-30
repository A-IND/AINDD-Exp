/*    */ package de.uni_potsdam.hpi.utils;
/*    */ 
/*    */ public class MeasurementUtils
/*    */ {
/*    */   public static long sizeOf32(String s) {
/*  6 */     long bytes = (40 + 2 * s.length());
/*    */     
/*  8 */     bytes = (long)(8.0D * Math.ceil((bytes / 8L)));
/*    */     
/* 10 */     return bytes;
/*    */   }
/*    */   
/*    */   public static long sizeOf64(String s) {
/* 14 */     long bytes = (64 + 2 * s.length());
/*    */     
/* 16 */     bytes = (long)(8.0D * Math.ceil((bytes / 8L)));
/*    */     
/* 18 */     return bytes;
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\d\\uni_potsdam\hp\\utils\MeasurementUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */