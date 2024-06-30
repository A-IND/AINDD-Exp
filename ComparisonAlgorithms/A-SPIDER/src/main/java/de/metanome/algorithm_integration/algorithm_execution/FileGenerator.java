package de.metanome.algorithm_integration.algorithm_execution;

import java.io.Closeable;
import java.io.File;

public interface FileGenerator extends Closeable {
  File getTemporaryFile() throws FileCreationException;
}


/* Location:              E:\EdgeDownload\deployment-1.2-SNAPSHOT-package_with_tomcat (1)\backend\WEB-INF\classes\algorithms\SPIDER-1.2-SNAPSHOT.jar!\de\metanome\algorithm_integration\algorithm_execution\FileGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */