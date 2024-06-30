/*    */ package de.metanome.algorithm_integration.results;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonAutoDetect;
/*    */ import com.fasterxml.jackson.annotation.PropertyAccessor;
/*    */ import com.fasterxml.jackson.core.JsonProcessingException;
/*    */ import com.fasterxml.jackson.databind.ObjectMapper;
/*    */ import java.io.IOException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ 
/*    */ 
/*    */ public class JsonConverter<T>
/*    */ {
/* 35 */   ObjectMapper mapper = (new ObjectMapper())
/* 36 */     .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toJsonString(T type) throws JsonProcessingException {
/* 46 */     return this.mapper.writeValueAsString(type);
/*    */   }
/*    */   
/*    */   public List<String> toJsonStrings(List<T> tList) {
/* 50 */     List<String> result = new ArrayList<>();
/* 51 */     for (T t : tList) {
/*    */       try {
/* 53 */         result.add(this.mapper.writeValueAsString(t));
/* 54 */       } catch (JsonProcessingException e) {
/* 55 */         e.printStackTrace();
/*    */       } 
/*    */     } 
/* 58 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T fromJsonString(String json, Class<T> clazz) throws IOException {
/* 70 */     return (T)this.mapper.readValue(json, clazz);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void addMixIn(Class<?> target, Class<?> mixIn) {
/* 82 */     this.mapper.addMixInAnnotations(target, mixIn);
/*    */   }
/*    */ }


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\results\JsonConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */